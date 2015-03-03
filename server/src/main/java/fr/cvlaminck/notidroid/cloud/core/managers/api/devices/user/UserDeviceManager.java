package fr.cvlaminck.notidroid.cloud.core.managers.api.devices.user;

import fr.cvlaminck.notidroid.cloud.client.api.devices.UserDeviceResource;
import fr.cvlaminck.notidroid.cloud.core.exceptions.devices.NoSpecificDeviceManagerForTypeException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.rest.InvalidResourceFormatException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.CrossUserOperationException;

/**
 * Manager that handles all operations on devices.
 */
public interface UserDeviceManager {

    /**
     * Add a new device to the collection of devices owns by the provided user.
     * This function does not try to check if the device was already registered.
     *
     * @param ownerUserRef   Reference of the user that owns this device.
     * @param deviceResource Device that we want to register
     * @return The device with its id set.
     */
    public UserDeviceResource registerDeviceOwnedByUser(String ownerUserRef, UserDeviceResource deviceResource) throws NoSpecificDeviceManagerForTypeException, InvalidResourceFormatException, CrossUserOperationException;

    /**
     * Check if the user associated with the provided email address owns the specified device.
     *
     * @param emailAddress Email of the user
     * @param userDeviceId User device id
     * @return true if the user owns the device, false otherwise
     */
    public boolean checkIfUserWithEmailAddressOwnsDevice(String emailAddress, long userDeviceId);

}
