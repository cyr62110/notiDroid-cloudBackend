package fr.cvlaminck.notidroid.cloud.front.apis.controllers.prvt;

import fr.cvlaminck.notidroid.cloud.core.exceptions.rest.InvalidArgumentFormatException;
import fr.cvlaminck.notidroid.cloud.core.managers.api.devices.user.UserDeviceManager;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * This controller is part of the private API. Controllers of the private API cannot be requested by users.
 * Only registered external service can access those controllers. External service credentials are configured
 * using the JSON configuration file of this cloudBackend instance.
 */
@RestController
@RequestMapping("/api/private/user-devices")
public class PrivateUserDeviceController {

    private Base64 base64 = null;

    @Autowired
    private UserDeviceManager userDeviceManager;

    @PostConstruct
    private void postConstruct() {
        base64 = new Base64(true);
    }

    @RequestMapping(value = "/{userDeviceId}/isOwnedBy/{base64URLEncodedEmailAddress}")
    public boolean checkIfUserWithEmailOwnsDevice(@PathVariable long userDeviceId, @PathVariable String base64URLEncodedEmailAddress) throws InvalidArgumentFormatException {
        final String emailAddress;
        try {
            emailAddress = new String(base64.decode(base64URLEncodedEmailAddress));
        } catch (Exception e) { throw new InvalidArgumentFormatException("Last path segment must be an email address encoded using the Base64URL algorithm."); }
        return userDeviceManager.checkIfUserWithEmailAddressOwnsDevice(emailAddress, userDeviceId);
    }

}
