package fr.cvlaminck.notidroid.cloud.data.entities.users;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * An user of the notiDroid cloudbackend.
 * Administrator are also considered as user.
 */
@NodeEntity
public class UserEntity {

    @GraphId
    private Long id;

    private String email;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
