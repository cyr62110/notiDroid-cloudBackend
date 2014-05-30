package fr.cvlaminck.notidroid.cloud.data.repositories.users;

import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to manipulate user and adminsitrator in the Neo4J
 * database. Provide a basic CRUD.
 */
@Repository
public interface UserRepository extends GraphRepository<UserEntity> {

    /**
     * Find an user/admin using its email address.
     *
     * @param email Email of the user where are looking after
     * @return An user or null if the user do not exists.
     */
    //public UserEntity findByEmail(String email);

    /**
     * Number of administrator already registered
     * in the database.
     */
    @Query("MATCH (n:AdministratorEntity) RETURN count(n)")
    public long getNumberOfAdministrator();

}
