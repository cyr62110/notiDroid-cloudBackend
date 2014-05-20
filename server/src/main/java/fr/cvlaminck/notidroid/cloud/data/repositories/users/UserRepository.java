package fr.cvlaminck.notidroid.cloud.data.repositories.users;

import fr.cvlaminck.neo4j.repository.impl.BaseNeo4JRepository;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Repository implementing CRUD operations for users
 */
@Repository
public class UserRepository extends BaseNeo4JRepository<UserEntity> {

    @Autowired
    public UserRepository(GraphDatabaseService graphDatabaseService) {
        super(UserEntity.class, graphDatabaseService);
    }
}
