package fr.cvlaminck.notidroid.activemq.authentication;

import org.apache.commons.validator.routines.UrlValidator;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.spi.LoginModule;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * Base class for all Jaas LoginModule communicating with a Notidroid cloudBackend to
 * authenticate an user on an Jaas enabled software like ActiveMQ.
 *
 * @since 0.2
 */
public abstract class NotidroidAbstractLoginModule
    implements LoginModule {
    private final static String OPTION_KEY_AUTHORIZATION_SERVER_URL = "authorizationServerUrl";

    private final static String OPTION_KEY_API_ROOT_URL = "apiRootUrl";

    private final static String OPTION_KEY_USERNAME = "apiUsername";

    private final static String OPTION_KEY_PASSWORD = "apiPassword";

    private final static String SHARE_STATE_KEY_API_CREDENTIALS = "apiCredentials";

    protected String authorizationServerUrl = null;

    protected String apiRootUrl = null;

    protected PrivateAPICredentials apiCredentials = null;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        final UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);

        //First we read the options to configure our LoginModule
        //URL of the authorization server used to validate the access_token
        this.authorizationServerUrl = (String) options.get(OPTION_KEY_AUTHORIZATION_SERVER_URL);
        if (authorizationServerUrl == null || !urlValidator.isValid(authorizationServerUrl))
            throw new IllegalStateException("NotidroidDeviceLoginModule misconfiguration : Key '" + OPTION_KEY_AUTHORIZATION_SERVER_URL
                    + "' must be configured in the option and must point to a valid URL.");

        //Root URL of the private API
        this.apiRootUrl = (String) options.get(OPTION_KEY_API_ROOT_URL);
        if (apiRootUrl == null || !urlValidator.isValid(apiRootUrl))
            throw new IllegalStateException("NotidroidDeviceLoginModule misconfiguration : Key '" + OPTION_KEY_API_ROOT_URL
                    + "' must be configured in the option and must point to a valid URL.");

        //Credentials to access the private API of the cloudBackend. May be provided by options or by the shareState.
        //TODO : May be use a singleton ? ? ?
        final String username = (String) options.get(OPTION_KEY_USERNAME);
        final String password = (String) options.get(OPTION_KEY_PASSWORD);
        if(username == null || password == null || password.length() == 0 || username.length() == 0) {
            throw new IllegalStateException("NotidroidDeviceLoginModule misconfiguration : Keys '" + OPTION_KEY_USERNAME
                    + "' and '" + OPTION_KEY_PASSWORD + "'  must be configured in the option.");
        }
        try {
            apiCredentials = new PrivateAPICredentials(authorizationServerUrl, username, password);
        } catch (MalformedURLException e) { throw new IllegalStateException(e); }
    }
}
