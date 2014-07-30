package fr.cvlaminck.notidroid.cloud.core.managers.api.users;

import fr.cvlaminck.notidroid.cloud.core.exceptions.users.ExistingUserWithEmailException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.IncompleteUserInformationException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.InvalidEmailFormatException;
import fr.cvlaminck.notidroid.cloud.data.entities.users.AdministratorEntity;

public interface AdministratorManager {

    /**
     * Check if there is at least one administrator registered in the system.
     */
    public boolean hasAtLeastOneAdministrator();

    /**
     * Create a new administrator with information already filled in the Entity retrieved on
     * the MVC controller
     */
    public void createAdministrator(AdministratorEntity administrator) throws IncompleteUserInformationException, InvalidEmailFormatException, ExistingUserWithEmailException;

}
