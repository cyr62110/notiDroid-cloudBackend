package fr.cvlaminck.notidroid.activemq.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cvlaminck.notidroid.activemq.utils.UrlUtils;
import fr.cvlaminck.notidroid.cloud.prvt.api.oauth2.OAuth2AccessToken;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.security.auth.RefreshFailedException;
import javax.security.auth.Refreshable;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Our ActiveMQ requires to be authenticated as an user on the cloud backend to access
 * the private API. This class will help to obtain/refresh an access_token.
 */
public class PrivateAPICredentials
        implements Refreshable {

    private final static Base64 base64 = new Base64();

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private URL tokenEndpoint = null;

    private String username = null;

    private String password = null;

    private String accessToken = null;

    private Date tokenExpirationDate = null;

    public PrivateAPICredentials(String authorizationServerUrl, String username, String password) throws MalformedURLException {
        this.username = username;
        this.password = password;
        this.tokenEndpoint = new URL(UrlUtils.appendPathFragmentToUrl(authorizationServerUrl, "token?grant_type=client_credentials"));
    }

    /**
     * Add the Authorization header to the connection so its can perform an
     * HTTP Basic Authentication with the server.
     *
     * @param httpURLConnection
     */
    public void addAuthorizationHeaderWithBasicAuth(HttpURLConnection httpURLConnection) {
        final byte[] usernamePasswordCouple = (username + ":" + password).getBytes();
        final String encodedUsernamePasswordCouple = base64.encodeAsString(usernamePasswordCouple);
        httpURLConnection.addRequestProperty("Authorization", "Basic " + encodedUsernamePasswordCouple);
    }

    /**
     * Add the Authorization header to the connection so its can perform an
     * OAuth2 Authentication with the server using the access token.
     *
     * @param httpURLConnection
     */
    public void addAuthorizationHeaderWithBearerAuth(HttpURLConnection httpURLConnection) throws RefreshFailedException {
        //If the current access token has expired, we refresh it.
        if (!isCurrent())
            refresh();
        //When we have a valid token, it is quite simple to add it to the request
        httpURLConnection.addRequestProperty("Authorization", "Bearer " + accessToken);
    }

    @Override
    public boolean isCurrent() {
        return (accessToken != null) && (tokenExpirationDate.after(new Date()));
    }

    @Override
    public void refresh() throws RefreshFailedException {
        final Date requestDate = new Date();
        HttpURLConnection httpURLConnection = null;
        InputStream httpInputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) tokenEndpoint.openConnection();
            httpURLConnection.setRequestMethod("GET");
            addAuthorizationHeaderWithBasicAuth(httpURLConnection);

            final int responseCode = httpURLConnection.getResponseCode();
            if ((responseCode / 100) == 2) {
                httpInputStream = httpURLConnection.getInputStream();
                final OAuth2AccessToken accessToken = objectMapper.readValue(httpInputStream, OAuth2AccessToken.class);
                this.accessToken = accessToken.getAccessToken();
                this.tokenExpirationDate = new Date(requestDate.getTime() + accessToken.getExpiresIn());
            } else if (responseCode == 403) {
                throw new RefreshFailedException("Bad credentials. Check the credentials configured in your backend and make sure they match the one configured in this plugin.");
            } else
                throw new RefreshFailedException("Server respond with unsupported http status code " + responseCode);
        } catch (Exception e) {
            throw new RefreshFailedException(e.getMessage());
        } finally {
            if (httpInputStream != null)
                IOUtils.closeQuietly(httpInputStream);
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
        }
    }

}
