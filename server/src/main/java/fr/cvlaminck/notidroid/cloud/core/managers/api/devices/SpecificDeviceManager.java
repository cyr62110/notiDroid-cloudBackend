package fr.cvlaminck.notidroid.cloud.core.managers.api.devices;

import fr.cvlaminck.notidroid.cloud.client.api.devices.UserDeviceResource;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;

/**
 * Manager that handles operations on a given type of device.
 * The default implementation of the DeviceManager delegates all operations
 * to SpecificDeviceManagers.
 */
public interface SpecificDeviceManager {

    /**
     * Does this manager support operations on the given device resource type ?
     */
    public boolean doesSupportResourceType(Class<? extends UserDeviceResource> deviceResourceType);

    /**
     * Does this manager support operations on the given device entity type ?
     * May be DeviceEntity or UserDeviceEntity subclasses.
     */
    public boolean doesSupportEntityType(Class deviceEntityType);

    public UserDeviceResource registerDeviceOwnedByUser(UserEntity owner, UserDeviceResource deviceResource);

}
