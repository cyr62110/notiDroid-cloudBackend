package fr.cvlaminck.notidroid.cloud.front.admin.controllers;

import fr.cvlaminck.notidroid.cloud.core.managers.api.users.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/public/administrators")
public class AdministratorPublicController {

    @Autowired
    private UserManager userManager;

    @RequestMapping(method = RequestMethod.GET, value="isAny",
        consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public boolean isAnyAdministratorRegistered() {
        return userManager.getNumberOfAdministrator() > 0;
    }

    @RequestMapping(method = RequestMethod.POST,
        consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})


}
