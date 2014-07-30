package fr.cvlaminck.notidroid.cloud.data.entities.devices.user;

import fr.cvlaminck.notidroid.cloud.data.entities.devices.hardware.DeviceEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Contains information about a device owns by a user.
 * <p>
 * This class contains only information specific to the device owns by the user (os version, ...).
 * Other information about the hardware are store in a DeviceEntity.
 */
@NodeEntity
public abstract class UserDeviceEntity {

    @GraphId
    private Long id;

    /**
     * Name given by the user to this device.
     * This name is defined by the user using a notidroid client, the value does not come from the device.
     * May be null or empty.
     */
    private String name;

    /**
     * User that owns this device
     */
    @RelatedTo(type = "owns", direction = Direction.INCOMING)
    private UserEntity owner;

    /**
     * Information about the hardware of
     * this device.
     */
    @RelatedTo(type = "is", direction = Direction.OUTGOING)
    private DeviceEntity hardware;

    public Long getId() {
        return id;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public DeviceEntity getHardware() {
        return hardware;
    }

    public void setHardware(DeviceEntity hardware) {
        this.hardware = hardware;
    }
}
