package fr.cvlaminck.notidroid.cloud.data.entities.users;

import fr.cvlaminck.neo4j.entities.Neo4JEntity;
import org.neo4j.graphdb.Node;

/**
 * An user of the notiDroid cloudbackend.
 * Administrator are also considered as user.
 */
public class UserEntity extends Neo4JEntity {

    private Node underlyingNode = null;

    //Constants to store the value in a node
    private final static String EMAIL = "email";

    public UserEntity(Node underlyingNode) {
        super(underlyingNode);
    }

    public Long getId() {
        return underlyingNode.getId();
    }

    public String getEmail() {
        return (String) underlyingNode.getProperty(EMAIL);
    }

    public void setEmail(String email) {
        underlyingNode.setProperty(EMAIL, email);
    }

    @Override
    public int hashCode() {
        return underlyingNode.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof UserEntity &&
                underlyingNode.equals(((UserEntity) o).underlyingNode);
    }

}
