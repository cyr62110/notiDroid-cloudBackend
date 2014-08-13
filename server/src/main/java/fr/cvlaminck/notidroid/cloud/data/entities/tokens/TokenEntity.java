package fr.cvlaminck.notidroid.cloud.data.entities.tokens;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * All OAuth2 tokens are stored as entity in the notification database.
 * The way to store the token is the same as the JdbcTokenStore
 * does it by serializing the token and the authentication.
 */
@Document(collection = "tokens")
public abstract class TokenEntity {

    @Indexed
    private String value;

    private byte[] token;

    private String authenticationKey;

    @Indexed
    private String clientId;

    private String username;

    private byte[] authentication;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public String getAuthenticationKey() {
        return authenticationKey;
    }

    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }
}
