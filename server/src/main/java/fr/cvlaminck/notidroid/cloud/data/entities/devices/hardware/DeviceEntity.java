package fr.cvlaminck.notidroid.cloud.data.entities.devices.hardware;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Contains information about a given hardware. An hardware version of a commercial device
 * must only be represented by one instance of this class in the database.
 * <p>
 * This class contains only immutable and non-user specific information about a device.
 * Things like os version that can change are stored in an UserDevice entity.
 */
@NodeEntity
public abstract class DeviceEntity {

    /**
     * Identifier of the devices
     */
    @GraphId
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
