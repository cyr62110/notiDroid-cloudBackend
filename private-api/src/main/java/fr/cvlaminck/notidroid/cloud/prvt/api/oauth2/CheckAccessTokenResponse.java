package fr.cvlaminck.notidroid.cloud.prvt.api.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;

/**
 * this or 400 Bad request
 * @since 0.2
 */
public class CheckAccessTokenResponse {

    /**
     * Identifier that the OAuth2 client as provided to the authorization server
     * to obtain this token.
     */
    @JsonProperty(value = "client_id")
    private String clientId;

    /**
     * OAuth2 scopes that the user belongs to
     */
    @JsonProperty(value = "scope")
    private Collection<String> scopes = new ArrayList<String>();

    /**
     * When the access token will expire
     */
    @JsonProperty(value = "exp")
    private long expirationTimestamp;

    public CheckAccessTokenResponse() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Collection<String> getScopes() {
        return scopes;
    }

    public void setScopes(Collection<String> scopes) {
        this.scopes = scopes;
    }

    public long getExpirationTimestamp() {
        return expirationTimestamp;
    }

    public void setExpirationTimestamp(long expirationTimestamp) {
        this.expirationTimestamp = expirationTimestamp;
    }
}
