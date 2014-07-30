package fr.cvlaminck.notidroid.cloud.front.api.controllers;

import fr.cvlaminck.notidroid.cloud.client.api.devices.UserDeviceResource;
import fr.cvlaminck.notidroid.cloud.core.exceptions.devices.NoSpecificDeviceManagerForTypeException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.rest.InvalidResourceFormatException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.CrossUserOperationException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.UserNotFoundException;
import fr.cvlaminck.notidroid.cloud.core.managers.api.devices.DeviceManager;
import fr.cvlaminck.notidroid.cloud.core.utils.security.SecurityUtils;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Controller exposing operations that can be done on the collection
 * of devices that an user own. You can, for example, retrieve the list
 * of all devices of an user using this controller.
 */
@RestController
@RequestMapping("/api/users/{userRef}/devices")
public class UserDeviceController {

    @Autowired
    private DeviceManager deviceManager;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public UserDeviceResource registerDeviceOwnedByUser(@PathVariable String userRef, @RequestBody UserDeviceResource userDeviceResource)
            throws InvalidResourceFormatException, NoSpecificDeviceManagerForTypeException, CrossUserOperationException {
        return deviceManager.registerDeviceOwnedByUser(userRef, userDeviceResource);
    }

}
