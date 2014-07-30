package fr.cvlaminck.notidroid.cloud.core.managers.api.devices;

import fr.cvlaminck.notidroid.cloud.client.api.devices.UserDeviceResource;
import fr.cvlaminck.notidroid.cloud.core.exceptions.devices.NoSpecificDeviceManagerForTypeException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.rest.InvalidResourceFormatException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.CrossUserOperationException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.UserNotFoundException;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;

/**
 * Manager that handles all operations on devices.
 */
public interface DeviceManager {

    /**
     * Add a new device to the collection of devices owns by the provided user.
     * This function does not try to check if the device was already registered.
     *
     * @param ownerUserRef          Reference of the user that owns this device.
     * @param deviceResource Device that we want to register
     * @return The device with its id set.
     */
    public UserDeviceResource registerDeviceOwnedByUser(String ownerUserRef, UserDeviceResource deviceResource) throws NoSpecificDeviceManagerForTypeException, InvalidResourceFormatException, CrossUserOperationException;

}
