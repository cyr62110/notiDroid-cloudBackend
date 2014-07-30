package fr.cvlaminck.notidroid.cloud.core.exceptions.users;

import fr.cvlaminck.notidroid.cloud.core.exceptions.NotidroidException;
import org.springframework.http.HttpStatus;

/**
 * Thrown if the account reference passed as parameter has no account associated
 * in the database.
 */
public class UserNotFoundException extends NotidroidException {
    private final static String MESSAGE = "No account associated to the reference '%s'";

    public UserNotFoundException(String userRef) {
        super(HttpStatus.NOT_FOUND, String.format(MESSAGE, userRef));
    }

}
