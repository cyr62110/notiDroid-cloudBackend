package fr.cvlaminck.notidroid.cloud.front.apis.controllers.client;

import fr.cvlaminck.notidroid.cloud.client.api.users.UserResource;
import fr.cvlaminck.notidroid.cloud.client.api.users.UserWithCredentialsResource;
import fr.cvlaminck.notidroid.cloud.core.exceptions.rest.InvalidResourceFormatException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.ExistingUserWithEmailException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.RegistrationAreClosedException;
import fr.cvlaminck.notidroid.cloud.core.managers.api.users.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller allowing client application to register new user. This is the only
 * functionality that is exposed by this controller. All other operation on user accounts
 * are on the protected UserController.
 * <p>
 * These endpoint is not protected and can be access by everyone to determine if he can
 * communicate with this server. It is one of the not so many public public endpoints exposed
 * by the notidroid cloud backend.
 */
@RestController
@RequestMapping("/api/public/users")
public class PublicUserController {

    @Autowired
    private UserManager userManager;

    /**
     * Create an account for the user with the information provided in the request body.
     *
     * @param userWithCredentialsResource Information about the user
     * @return A resource representing the user with its identifier on the server.
     * @throws RegistrationAreClosedException 403 Forbidden - Registration of the user is not allowed on the server
     * @throws ExistingUserWithEmailException 409 Conflict - An account has already been created with the provided email address
     * @throws InvalidResourceFormatException 422 Unprocessable Entity - The resource sent to the server does not follow the format defined in the API
     * @since 0.1
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public UserResource createUser(@RequestBody UserWithCredentialsResource userWithCredentialsResource) throws ExistingUserWithEmailException, InvalidResourceFormatException, RegistrationAreClosedException {
        return userManager.registerNewUser(userWithCredentialsResource);
    }

}
