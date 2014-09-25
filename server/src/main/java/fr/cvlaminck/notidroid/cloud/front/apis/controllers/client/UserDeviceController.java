package fr.cvlaminck.notidroid.cloud.front.apis.controllers.client;

import fr.cvlaminck.notidroid.cloud.client.api.devices.UserDeviceResource;
import fr.cvlaminck.notidroid.cloud.core.exceptions.devices.NoSpecificDeviceManagerForTypeException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.rest.InvalidResourceFormatException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.CrossUserOperationException;
import fr.cvlaminck.notidroid.cloud.core.managers.api.devices.user.UserDeviceManager;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserDeviceManager userDeviceManager;

    /**
     *
     * @param userRef
     * @param userDeviceResource
     * @return
     * @throws InvalidResourceFormatException
     * @throws NoSpecificDeviceManagerForTypeException
     * @throws CrossUserOperationException
     * @since 0.1
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public UserDeviceResource registerDeviceOwnedByUser(@PathVariable String userRef, @RequestBody UserDeviceResource userDeviceResource)
            throws InvalidResourceFormatException, NoSpecificDeviceManagerForTypeException, CrossUserOperationException {
        return userDeviceManager.registerDeviceOwnedByUser(userRef, userDeviceResource);
    }

}
