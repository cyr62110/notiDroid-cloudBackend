package fr.cvlaminck.notidroid.cloud.core.managers.users.utils;

import fr.cvlaminck.notidroid.cloud.core.exceptions.users.ExistingUserWithEmailException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.IncompleteUserInformationException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.InvalidEmailFormatException;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import fr.cvlaminck.notidroid.cloud.data.repositories.users.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Locale;

/**
 * Utility class that validate/format/etc. the user information.
 */
public class UserInformationUtils {

    public static void format(UserEntity user) {
        //We format the mail address
        user.setEmail(user.getEmail().toLowerCase(Locale.US));
        //We can also format his name, but we do not care.
    }

    public static void validateUserInformation(UserEntity user) throws IncompleteUserInformationException, InvalidEmailFormatException {
        //First, we check if we have everything to create our administrator
        if (StringUtils.isBlank(user.getEmail()))
            throw new IncompleteUserInformationException();
        if (StringUtils.isBlank(user.getFirstName()))
            throw new IncompleteUserInformationException();
        if (StringUtils.isBlank(user.getLastName()))
            throw new IncompleteUserInformationException();
        if (StringUtils.isBlank(user.getPassword()))
            throw new IncompleteUserInformationException();

        EmailValidator emailValidator = EmailValidator.getInstance(false);
        if (!emailValidator.isValid(user.getEmail()))
            throw new InvalidEmailFormatException();

    }

    public static void validateEmailNotUsed(UserEntity user, UserRepository userRepository) throws ExistingUserWithEmailException {
        UserEntity existingUserWithEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserWithEmail != null) {
            throw new ExistingUserWithEmailException();
        }
    }

}
