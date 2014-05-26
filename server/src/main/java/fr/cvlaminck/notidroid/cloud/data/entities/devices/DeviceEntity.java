package fr.cvlaminck.notidroid.cloud.data.entities.devices;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Represents a device that is own by a client.
 * The device can be anything from a laptop or a desktop to a smartphone or a smartwatch.
 */
@NodeEntity
public abstract class DeviceEntity {

    /**
     * Identifier of the devices
     */
    @GraphId
    private Long id;

    /**
     * Id of the owner of this device.
     * The id must belong to an user.
     */
    private String ownerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Name of this device when shown on the website or an the app.
     */
    public abstract String getDisplayName();

}
