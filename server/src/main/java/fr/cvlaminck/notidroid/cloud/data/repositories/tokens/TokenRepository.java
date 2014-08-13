package fr.cvlaminck.notidroid.cloud.data.repositories.tokens;

import fr.cvlaminck.notidroid.cloud.data.entities.tokens.TokenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

/**
 *
 */
public interface TokenRepository extends MongoRepository<TokenEntity, String>, TokenRepositoryCustom {

    public TokenEntity findByValue(String value);

    public TokenEntity findByAuthenticationKey(String authKey);

    public Collection<TokenEntity> findByClientId(String clientId);

    public Collection<TokenEntity> findByClientIdAndUsername(String clientId, String username);

}
