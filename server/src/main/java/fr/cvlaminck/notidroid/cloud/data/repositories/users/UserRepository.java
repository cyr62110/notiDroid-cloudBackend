package fr.cvlaminck.notidroid.cloud.data.repositories.users;

import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository implementing CRUD operations for users
 */
@Repository
public interface UserRepository extends GraphRepository<UserEntity> {

}
