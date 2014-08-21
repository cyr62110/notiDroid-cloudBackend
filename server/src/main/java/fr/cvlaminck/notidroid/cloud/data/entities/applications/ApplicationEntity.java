package fr.cvlaminck.notidroid.cloud.data.entities.applications;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Representation of an application as a node.
 */
@NodeEntity
public class ApplicationEntity {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

}
