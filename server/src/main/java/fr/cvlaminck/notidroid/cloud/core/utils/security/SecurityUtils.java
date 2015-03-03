package fr.cvlaminck.notidroid.cloud.core.utils.security;

import fr.cvlaminck.notidroid.cloud.core.exceptions.users.CrossUserOperationException;
import fr.cvlaminck.notidroid.cloud.core.managers.api.users.UserManager;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Class helping to manipulate the Spring Security framework.
 */
@Component
public class SecurityUtils {

    @Autowired
    private UserManager userManager;

    /**
     * Return the authenticated user or null if none.
     */
    public UserEntity getAuthenticatedUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && StringUtils.isNoneBlank(authentication.getName()))
            return getUserFromAuthentication(authentication);
        else
            return null;
    }

    /**
     * Check that the authenticated user is the one designated by the user reference passed as
     * parameter or throw an exception. This method should be used to protect operation that can
     * only be executed by the owner of the account.
     *
     * @param userRef Account reference : can be an user id or 'me' (authenticated user account).
     * @return the authenticated user.
     * @throws CrossUserOperationException Thrown if the authenticated is not the one designed by the ref. passed in params.
     */
    public UserEntity checkIfAuthenticatedUserIsDesignedByRef(String userRef) throws CrossUserOperationException {
        final UserEntity authenticatedUser = getAuthenticatedUser();
        if (authenticatedUser == null)
            throw new IllegalStateException("No user found in the security context. You should check that this method is only called in method protected using Spring Security.");
        if (!("me".equals(userRef)) && !authenticatedUser.getId().toString().equals(userRef))
            throw new CrossUserOperationException();
        return authenticatedUser;
    }

    public UserEntity getUserFromAuthentication(Authentication authentication) {
        return userManager.findUserByEmail(authentication.getName());
    }

}
