package fr.cvlaminck.notidroid.cloud.core.security.stores;

import fr.cvlaminck.notidroid.cloud.data.entities.tokens.AccessTokenEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.tokens.RefreshTokenEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.tokens.TokenEntity;
import fr.cvlaminck.notidroid.cloud.data.repositories.tokens.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A token store that will save tokens in the Mongo database using a repository.
 */
@Component
public class RepositoryBasedTokenStore
        implements TokenStore {

    @Autowired
    private TokenRepository tokenRepository;

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    public void setAuthenticationKeyGenerator(AuthenticationKeyGenerator authenticationKeyGenerator) {
        this.authenticationKeyGenerator = authenticationKeyGenerator;
    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        //Do we really need to enforce the token type ???
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        final TokenEntity tokenEntity = tokenRepository.findByValue(token);
        if (tokenEntity != null)
            return (OAuth2Authentication) SerializationUtils.deserialize(tokenEntity.getAuthentication());
        else
            return null;
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        final AccessTokenEntity tokenEntity = new AccessTokenEntity();
        tokenEntity.setValue(token.getValue());
        tokenEntity.setToken(SerializationUtils.serialize(token));
        tokenEntity.setAuthenticationKey(authenticationKeyGenerator.extractKey(authentication));
        tokenEntity.setClientId(authentication.getOAuth2Request().getClientId());
        tokenEntity.setUsername((!authentication.isClientOnly()) ? authentication.getName() : null);
        tokenEntity.setAuthentication(SerializationUtils.serialize(authentication));
        tokenEntity.setRefreshTokenValue((token.getRefreshToken() != null) ? token.getRefreshToken().getValue() : null);

        tokenRepository.save(tokenEntity);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        final TokenEntity tokenEntity = tokenRepository.findByValue(tokenValue);
        if (tokenEntity != null && tokenEntity instanceof AccessTokenEntity)
            return (OAuth2AccessToken) SerializationUtils.deserialize(tokenEntity.getToken());
        else
            return null;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        //Do we really need to enforce the token type ???
        tokenRepository.removeByValue(token.getValue());
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        final RefreshTokenEntity tokenEntity = new RefreshTokenEntity();
        tokenEntity.setValue(refreshToken.getValue());
        tokenEntity.setToken(SerializationUtils.serialize(refreshToken));
        tokenEntity.setAuthenticationKey(authenticationKeyGenerator.extractKey(authentication));
        tokenEntity.setClientId(authentication.getOAuth2Request().getClientId());
        tokenEntity.setUsername((!authentication.isClientOnly()) ? authentication.getName() : null);
        tokenEntity.setAuthentication(SerializationUtils.serialize(authentication));

        tokenRepository.save(tokenEntity);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        final TokenEntity tokenEntity = tokenRepository.findByValue(tokenValue);
        if (tokenEntity != null && tokenEntity instanceof RefreshTokenEntity)
            return (OAuth2RefreshToken) SerializationUtils.deserialize(tokenEntity.getToken());
        else
            return null;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        //Do we really need to enforce the token type ???
        return readAuthentication(token.getValue());
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        //Do we really need to enforce the token type ???
        tokenRepository.removeByValue(token.getValue());
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        tokenRepository.removeAccessTokenWithRefreshTokenValue(refreshToken.getValue());
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        final String authKey = authenticationKeyGenerator.extractKey(authentication);
        final TokenEntity tokenEntity = tokenRepository.findByAuthenticationKey(authKey);
        if (tokenEntity != null && tokenEntity instanceof AccessTokenEntity)
            return (OAuth2AccessToken) SerializationUtils.deserialize(tokenEntity.getToken());
        else
            return null;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        final Collection<OAuth2AccessToken> tokens = new ArrayList<>();
        for (TokenEntity tokenEntity : tokenRepository.findByClientIdAndUsername(clientId, userName)) {
            if (tokenEntity instanceof AccessTokenEntity) {
                final OAuth2AccessToken accessToken = (OAuth2AccessToken) SerializationUtils.deserialize(tokenEntity.getToken());
                tokens.add(accessToken);
            }
        }
        return tokens;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        final Collection<OAuth2AccessToken> tokens = new ArrayList<>();
        for (TokenEntity tokenEntity : tokenRepository.findByClientId(clientId)) {
            if (tokenEntity instanceof AccessTokenEntity) {
                final OAuth2AccessToken accessToken = (OAuth2AccessToken) SerializationUtils.deserialize(tokenEntity.getToken());
                tokens.add(accessToken);
            }
        }
        return tokens;
    }

}
