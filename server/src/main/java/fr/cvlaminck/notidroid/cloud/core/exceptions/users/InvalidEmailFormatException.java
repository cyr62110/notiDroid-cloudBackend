package fr.cvlaminck.notidroid.cloud.core.exceptions.users;

import fr.cvlaminck.notidroid.cloud.core.exceptions.NotidroidException;

/**
 * Thrown if the user try to create its account with a non-valid email.
 * Only the format of the address has been tested at this point.
 */
public class InvalidEmailFormatException extends NotidroidException {

    private static final String MESSAGE = "Invalid email address format. Please check your email address.";
    private static final String I18N_MESSAGE_ID = "exception.users.invalidemailformat";

    public InvalidEmailFormatException() {
        super(MESSAGE, I18N_MESSAGE_ID);
    }

}
