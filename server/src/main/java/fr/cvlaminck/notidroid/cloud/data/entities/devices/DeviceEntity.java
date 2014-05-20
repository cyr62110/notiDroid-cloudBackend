package fr.cvlaminck.notidroid.cloud.data.entities.devices;

/**
 * Represents a device that is own by a client.
 * The device can be anything from a laptop or a desktop to a smartphone or a smartwatch.
 */
public abstract class DeviceEntity {

    /**
     * Identifier of the devices
     */
    private String id;

    /**
     * Id of the owner of this device.
     * The id must belong to an user.
     */
    private String ownerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
