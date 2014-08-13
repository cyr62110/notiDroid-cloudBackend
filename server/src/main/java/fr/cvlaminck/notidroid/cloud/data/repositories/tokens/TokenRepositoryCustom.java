package fr.cvlaminck.notidroid.cloud.data.repositories.tokens;

/**
 * Extension for the TokenRepository to add missing methods.
 */
public interface TokenRepositoryCustom {

    public void removeByValue(String tokenValue);

    public void removeAccessTokenWithRefreshTokenValue(String refreshTokenValue);

}
