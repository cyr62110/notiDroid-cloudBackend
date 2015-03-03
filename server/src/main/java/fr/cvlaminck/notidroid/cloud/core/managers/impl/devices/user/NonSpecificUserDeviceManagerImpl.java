package fr.cvlaminck.notidroid.cloud.core.managers.impl.devices.user;

import fr.cvlaminck.notidroid.cloud.client.api.devices.UserDeviceResource;
import fr.cvlaminck.notidroid.cloud.core.exceptions.devices.NoSpecificDeviceManagerForTypeException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.rest.InvalidResourceFormatException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.CrossUserOperationException;
import fr.cvlaminck.notidroid.cloud.core.managers.api.devices.user.UserDeviceManager;
import fr.cvlaminck.notidroid.cloud.data.repositories.devices.user.UserDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class implementing methods of the UserDeviceManager that are not specific to the type of the device.
 * Methods that are specific to a type of device are not implemented and throw a RuntimeException if called.
 * This class has been created to keep the code out of the delegating implementation that only delegates to this
 * implementation or to SpecificUserDeviceManagers.
 */
@Component(value = "non-specific")
public class NonSpecificUserDeviceManagerImpl
        implements UserDeviceManager {
    private static final String UNSUPPORTED_OPERATION_MESSAGE = "This operation is device-specific";

    @Autowired
    private UserDeviceRepository userDeviceRepository;

    @Override
    public UserDeviceResource registerDeviceOwnedByUser(String ownerUserRef, UserDeviceResource deviceResource) throws NoSpecificDeviceManagerForTypeException, InvalidResourceFormatException, CrossUserOperationException {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

    @Override
    public boolean checkIfUserWithEmailAddressOwnsDevice(String emailAddress, long userDeviceId) {
        try {
            return userDeviceRepository.checkIfUserWithEmailOwnsDeviceWithId(emailAddress, userDeviceId) > 0;
        } catch (RuntimeException e) {
            //If the node does not exists, the request throws a RuntimeException.
            //But we want to return false to the client because a non existing device is not owned by anyone.
            return false;
        }
    }
}
