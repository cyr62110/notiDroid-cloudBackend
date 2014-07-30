package fr.cvlaminck.notidroid.cloud.core.exceptions.users;

import fr.cvlaminck.notidroid.cloud.core.exceptions.NotidroidException;
import org.springframework.http.HttpStatus;

/**
 * Throw if we try to create an user with an already registered email
 */
public class ExistingUserWithEmailException extends NotidroidException {

    private static final String MESSAGE = "Another user is already registered with this email address";
    private static final String I18N_MESSAGE_ID = "exception.users.existinguserwithemail";

    public ExistingUserWithEmailException() {
        super(HttpStatus.CONFLICT, MESSAGE, I18N_MESSAGE_ID);
    }

}
