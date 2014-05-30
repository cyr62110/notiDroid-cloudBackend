package fr.cvlaminck.notidroid.cloud.core.managers.users;

import fr.cvlaminck.notidroid.cloud.core.exceptions.users.IncompleteUserInformationException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.InvalidEmailFormatException;
import fr.cvlaminck.notidroid.cloud.core.managers.users.utils.UserInformationValidator;
import fr.cvlaminck.notidroid.cloud.data.entities.users.AdministratorEntity;
import fr.cvlaminck.notidroid.cloud.data.repositories.users.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdministratorManager {

    @Autowired
    private UserRepository userRepository;

    /**
     * Create a new administrator with information already filled in the Entity retrieved on
     * the MVC controller
     */
    public void createAdministrator(AdministratorEntity administrator) throws IncompleteUserInformationException, InvalidEmailFormatException {
        //First, we check if we have everything to create our administrator
        UserInformationValidator.validateUserInformation(administrator);
    }

}
