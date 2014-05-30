package fr.cvlaminck.notidroid.cloud.front.api.controllers;

import fr.cvlaminck.notidroid.cloud.api.users.UserResource;
import fr.cvlaminck.notidroid.cloud.api.users.UserWithCredentialsResource;
import fr.cvlaminck.notidroid.cloud.core.exceptions.NotidroidException;
import fr.cvlaminck.notidroid.cloud.core.managers.users.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller exposing those functions in the form of a REST API :
 *  - New user registration
 *  -
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserManager userManager;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public UserResource createUser(UserWithCredentialsResource userWithCredentialsResource) throws NotidroidException {
        return userManager.registerNewUser(userWithCredentialsResource);
    }

}
