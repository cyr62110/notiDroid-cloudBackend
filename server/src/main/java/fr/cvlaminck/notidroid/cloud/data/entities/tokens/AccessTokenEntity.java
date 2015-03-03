package fr.cvlaminck.notidroid.cloud.data.entities.tokens;

import org.springframework.data.annotation.TypeAlias;

/**
 *
 */
@TypeAlias(value = "access_token")
public class AccessTokenEntity
        extends TokenEntity {

    public String refreshTokenValue;

    public String getRefreshTokenValue() {
        return refreshTokenValue;
    }

    public void setRefreshTokenValue(String refreshTokenValue) {
        this.refreshTokenValue = refreshTokenValue;
    }
}
