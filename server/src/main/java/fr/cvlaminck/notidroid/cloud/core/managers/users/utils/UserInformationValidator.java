package fr.cvlaminck.notidroid.cloud.core.managers.users.utils;

import fr.cvlaminck.notidroid.cloud.core.exceptions.users.IncompleteUserInformationException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.InvalidEmailFormatException;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Utility class that validate that the user is properly fulfilled
 */
public class UserInformationValidator {

    public static void validateUserInformation(UserEntity user) throws IncompleteUserInformationException, InvalidEmailFormatException {
        //First, we check if we have everything to create our administrator
        if(StringUtils.isBlank(user.getEmail()))
            throw new IncompleteUserInformationException();
        if(StringUtils.isBlank(user.getFirstName()))
            throw new IncompleteUserInformationException();
        if(StringUtils.isBlank(user.getLastName()))
            throw new IncompleteUserInformationException();
        if(StringUtils.isBlank(user.getPassword()))
            throw new IncompleteUserInformationException();

        EmailValidator emailValidator = EmailValidator.getInstance(false);
        if(!emailValidator.isValid(user.getEmail()))
            throw new InvalidEmailFormatException();

    }

}
