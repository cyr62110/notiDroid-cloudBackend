package fr.cvlaminck.notidroid.activemq.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cvlaminck.notidroid.activemq.utils.UrlUtils;
import fr.cvlaminck.notidroid.cloud.prvt.api.oauth2.CheckAccessTokenResponse;
import org.apache.activemq.jaas.GroupPrincipal;
import org.apache.activemq.jaas.UserPrincipal;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.security.auth.RefreshFailedException;
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * User devices will connect directly to the message broker. This module will authenticate the device
 * using the OAuth2 protocol and the authorization server embedded in the cloudBackend.
 * <p>
 * The authentication uses a username/password couple :
 * - username : deviceIdPrefix + DEVICE_ID_SEPARATOR + deviceId.
 * - password : a valid access_token.
 * <p>
 * Authentication procedure :
 * - First, if the username is not prefixed with the deviceIdPrefix we ignore and let another module authenticate this user.
 * - Then, we check that the provided access token is valid using the /oauth/check_token endpoint of the cloudBackend.
 * - Finally, we check that the device is owned by the user authenticated by the access token using the cloudBackend private API.
 *
 * @since 0.2
 */
public class NotidroidDeviceLoginModule
        extends NotidroidAbstractLoginModule
        implements LoginModule {

    private final static String DEVICE_ID_SEPARATOR = "-";
    private final static String DEFAULT_DEVICE_ID_PREFIX = "device";

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private final static Base64 base64 = new Base64(true);

    private Subject subject = null;

    private String deviceIdPrefix = DEFAULT_DEVICE_ID_PREFIX;

    private CallbackHandler callbackHandler = null;

    private Long deviceId = null;

    private String accessToken = null;

    private String userEmailAddress = null;

    private Collection<String> userScopes = null;

    private Set<Principal> principals = new HashSet<>();

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        super.initialize(subject, callbackHandler, sharedState, options);
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        //We use the callbackHandler to retrieve the username and the password
        final NameCallback nameCallback = new NameCallback("Device-Id: ");
        final PasswordCallback passwordCallback = new PasswordCallback("Access-Token: ", false);
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
        this.accessToken = new String(passwordCallback.getPassword());

        //Then we ignore all username not starting with the proper prefix
        if (!username.startsWith(deviceIdPrefix + DEVICE_ID_SEPARATOR))
            return false;

        //We check the token using the authorization server
        checkAccessToken();

        //We retrieve the device id from the username
        try {
            this.deviceId = Long.parseLong(username.substring((deviceIdPrefix + DEVICE_ID_SEPARATOR).length()));
        } catch (NumberFormatException e) {
            throw new FailedLoginException("deviceId does not follow the format : Must be a number prefixed by '" + deviceIdPrefix +
                    DEVICE_ID_SEPARATOR + "'");
        }

        //Then we check that the user owning the token is the owner of the device
        //We use the cloudBackend private API to do so.
        checkIfUserOwnsDevice();

        return true;
    }

    @Override
    public boolean commit() throws LoginException {
        //We compute all the principals that must be given to the subject
        this.principals.add(new UserPrincipal(this.userEmailAddress));
        this.principals.add(Principals.USERS_GROUP); //We have a dedicated group for regular users

        //TODO : Do something for groups

        //And we add our principals to the Jaas subject
        this.subject.getPrincipals().addAll(this.principals);

        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        clear();
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        //We remove all principals added by this module to the subject.
        this.subject.getPrincipals().removeAll(principals);
        clear();
        return true;
    }

    private void clear() {
        this.subject = null;
        this.callbackHandler = null;
        this.deviceId = null;
        this.accessToken = null;
        this.userEmailAddress = null;
        this.userScopes = null;
        this.principals.clear();
    }

    private void checkAccessToken() throws LoginException {
        final String checkTokenEndpointUrl = UrlUtils.appendPathFragmentToUrl(authorizationServerUrl, "check_token?token=" + accessToken);
        HttpURLConnection httpURLConnection = null;
        InputStream httpInputStream = null;
        try {
            URL checkTokenEndpoint = new URL(checkTokenEndpointUrl);
            httpURLConnection = (HttpURLConnection) checkTokenEndpoint.openConnection();
            httpURLConnection.setRequestMethod("GET");
            apiCredentials.addAuthorizationHeaderWithBasicAuth(httpURLConnection);

            final int responseCode = httpURLConnection.getResponseCode();
            if((responseCode / 100) == 2) {
                httpInputStream = httpURLConnection.getInputStream();
                final CheckAccessTokenResponse tokenResponse = objectMapper.readValue(httpInputStream, CheckAccessTokenResponse.class);
                this.userEmailAddress = tokenResponse.getClientId();
                this.userScopes = tokenResponse.getScopes();
            } else if(responseCode == 400) {
                throw new FailedLoginException("Invalid token. You may need to refresh your access token before accessing this service.");
            } else if(responseCode / 100 == 5)
                throw new LoginException("Internal server error. Please retry later.");
        } catch (IOException e) {
            throw new LoginException(e.getMessage());
        } finally {
            if (httpInputStream != null)
                IOUtils.closeQuietly(httpInputStream);
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
        }
    }

    private void checkIfUserOwnsDevice() throws LoginException {
        final String base64EncodedEmailAddress = base64.encodeAsString(this.userEmailAddress.getBytes());
        final String checkIfUserOwnsDeviceEndpointUrl = UrlUtils.appendPathFragmentToUrl(apiRootUrl,
                String.format("user-devices/%d/isOwnedBy/%s", this.deviceId, base64EncodedEmailAddress));
        HttpURLConnection httpURLConnection = null;
        InputStream httpInputStream = null;
        try {
            URL checkIfUserOwnsDeviceEndpoint = new URL(checkIfUserOwnsDeviceEndpointUrl);
            httpURLConnection = (HttpURLConnection) checkIfUserOwnsDeviceEndpoint.openConnection();
            httpURLConnection.setRequestMethod("GET");
            apiCredentials.addAuthorizationHeaderWithBearerAuth(httpURLConnection);

            final int responseCode = httpURLConnection.getResponseCode();
            if((responseCode / 100) == 2) {
                httpInputStream = httpURLConnection.getInputStream();
                final String responseBody = IOUtils.toString(httpInputStream);
                if(!responseBody.equals(Boolean.TRUE.toString()))
                    throw new FailedLoginException("The device that you are trying to connect with is not registered on your account.");
            } else {
                throw new LoginException("Internal server error. Please retry later.");
            }
        } catch (RefreshFailedException | IOException e) {
            throw new LoginException(e.getMessage());
        } finally {
            if (httpInputStream != null)
                IOUtils.closeQuietly(httpInputStream);
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
        }
    }

}
