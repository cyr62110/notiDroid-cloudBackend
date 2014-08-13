package fr.cvlaminck.notidroid.activemq.authentication;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

/**
 *
 * @since 0.2
 */
public class NotidroidCloudBackendLoginModule
    implements LoginModule {

    private final static String OPTION_KEY_USERNAME = "cloudBackendUsername";

    private final static String OPTION_KEY_PASSWORD = "cloudBackendPassword";

    private Subject subject = null;

    private CallbackHandler callbackHandler = null;

    /**
     * Username used by the cloud backend to connect to this message broker.
     */
    private String username;

    /**
     * Password used by the cloud backend to connect to this message broker.
     */
    private String password;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;

        //Credentials used by the cloudBackend to establish a connection with this message broker.
        final String username = (String) options.get(OPTION_KEY_USERNAME);
        final String password = (String) options.get(OPTION_KEY_PASSWORD);
        if(username == null || password == null || password.length() == 0 || username.length() == 0) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " misconfiguration : Keys '" + OPTION_KEY_USERNAME
                    + "' and '" + OPTION_KEY_PASSWORD + "'  must be configured in the option.");
        }
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean login() throws LoginException {
        //We use the callbackHandler to retrieve the username and the password
        final NameCallback nameCallback = new NameCallback("Username: ");
        final PasswordCallback passwordCallback = new PasswordCallback("Password: ", false);
        final Callback[] callbacks = new Callback[]{
                nameCallback,
                passwordCallback
        };
        try {
            callbackHandler.handle(callbacks);
        } catch (IOException e) {
            throw new LoginException(e.getMessage());
        } catch (UnsupportedCallbackException e) {
            throw new LoginException(e.getMessage() + " not available to obtain information from user");
        }

        final String username = nameCallback.getName();
        final String password = new String(passwordCallback.getPassword());

        //If the username does not match the one configured in the option, we ignore
        if(!this.username.equals(username))
            throw new FailedLoginException("Bad credentials");

        //We check if the password match
        if(!this.password.equals(password))
            throw new FailedLoginException("Bad credentials");

        return true;
    }

    @Override
    public boolean commit() throws LoginException {
        this.subject.getPrincipals().add(Principals.CLOUD_BACKEND_USER);
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        clear();
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        this.subject.getPrincipals().remove(Principals.CLOUD_BACKEND_USER);
        clear();
        return true;
    }

    private void clear() {
        this.subject = null;
        this.callbackHandler = null;
    }

}
