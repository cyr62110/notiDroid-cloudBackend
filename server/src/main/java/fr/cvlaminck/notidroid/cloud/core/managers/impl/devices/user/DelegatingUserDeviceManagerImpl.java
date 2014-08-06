package fr.cvlaminck.notidroid.cloud.core.managers.impl.devices.user;

import fr.cvlaminck.notidroid.cloud.client.api.devices.UserDeviceResource;
import fr.cvlaminck.notidroid.cloud.core.exceptions.devices.NoSpecificDeviceManagerForTypeException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.rest.InvalidResourceFormatException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.CrossUserOperationException;
import fr.cvlaminck.notidroid.cloud.core.managers.api.devices.user.SpecificUserDeviceManager;
import fr.cvlaminck.notidroid.cloud.core.managers.api.devices.user.UserDeviceManager;
import fr.cvlaminck.notidroid.cloud.core.utils.security.SecurityUtils;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.Set;

/**
 * Implementation of the device manager that delegate operations to the right
 * SpecificDeviceManager instance.
 */
@Component
@Primary
public class DelegatingUserDeviceManagerImpl
        implements UserDeviceManager {

    /**
     * Managers to which all device-specific operations will be delegated.
     */
    @Autowired
    private Collection<SpecificUserDeviceManager> specificUserDeviceManagers;

    /**
     * Manager to which all non-device-specific operations will be delegated.
     */
    @Autowired
    @Qualifier("non-specific")
    private UserDeviceManager nonSpecificUserDeviceManager;

    @Autowired
    private Validator validator;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    public UserDeviceResource registerDeviceOwnedByUser(String ownerUserRef, UserDeviceResource deviceResource) throws NoSpecificDeviceManagerForTypeException, InvalidResourceFormatException, CrossUserOperationException {
        //We check that the authenticated user does only this operation on its own account
        final UserEntity owner = securityUtils.checkIfAuthenticatedUserIsDesignedByRef(ownerUserRef);
        //Before delegating the operation, we validate the resource using the validation framework.
        final Set<ConstraintViolation<UserDeviceResource>> constraintViolations = validator.validate(deviceResource);
        if(!constraintViolations.isEmpty())
            throw new InvalidResourceFormatException(constraintViolations);
        //Then we delegate the operation
        return getSpecificDeviceManagerForResource(deviceResource).registerDeviceOwnedByUser(owner, deviceResource);
    }

    @Override
    public boolean checkIfUserWithEmailAddressOwnsDevice(String emailAddress, long userDeviceId) {
        return nonSpecificUserDeviceManager.checkIfUserWithEmailAddressOwnsDevice(emailAddress, userDeviceId);
    }

    /**
     * Return the specific device manager to which this implementation will delegate the operation.
     *
     * @param userDeviceResource Device REST resource
     * @return A specific device manager handling operation on the provided resource
     * @throws NoSpecificDeviceManagerForTypeException Thrown if there is no specific device manager for the given device.
     */
    private SpecificUserDeviceManager getSpecificDeviceManagerForResource(UserDeviceResource userDeviceResource) throws NoSpecificDeviceManagerForTypeException {
        for (SpecificUserDeviceManager specificUserDeviceManager : specificUserDeviceManagers)
            if (specificUserDeviceManager.doesSupportResourceType(userDeviceResource.getClass()))
                return specificUserDeviceManager;
        throw new NoSpecificDeviceManagerForTypeException(userDeviceResource);
    }

}
