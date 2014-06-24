package fr.cvlaminck.notidroid.cloud.front.admin.helpers;

import fr.cvlaminck.notidroid.cloud.core.security.NotidroidUserDetails;
import fr.cvlaminck.notidroid.cloud.data.entities.users.AdministratorEntity;
import org.springframework.security.core.Authentication;

/**
 * Helpers providing some utility methods for handling security
 * with Spring Security framework
 */
public abstract class SecurityHelper {

    public static AdministratorEntity getAdministrator(Authentication authentication) {
        return (AdministratorEntity) ((NotidroidUserDetails) authentication.getPrincipal()).getUser();
    }

}
