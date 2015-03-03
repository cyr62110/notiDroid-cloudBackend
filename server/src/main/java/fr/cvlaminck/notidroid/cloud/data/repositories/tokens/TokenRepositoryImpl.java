package fr.cvlaminck.notidroid.cloud.data.repositories.tokens;

import fr.cvlaminck.notidroid.cloud.data.entities.tokens.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Implementation of custom methods of the repository described in
 * the TokenRepositoryCustom interface.
 * <p>
 * /!\ The class containing the implementation must be named according
 * to the repository interface not the extension interface. So the name
 * of the class must be : {Repository name} + Impl.
 */
public class TokenRepositoryImpl
        implements TokenRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void removeByValue(String tokenValue) {
        final Query query = new Query(Criteria.where("value").is(tokenValue));
        mongoOperations.remove(query, TokenEntity.class);
    }

    @Override
    public void removeAccessTokenWithRefreshTokenValue(String refreshTokenValue) {
        final Query query = new Query(Criteria.where("refreshTokenValue").is(refreshTokenValue));
        mongoOperations.remove(query, TokenEntity.class);
    }
}
