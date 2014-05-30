package fr.cvlaminck.notidroid.cloud.core.exceptions.users;

import fr.cvlaminck.notidroid.cloud.core.exceptions.NotidroidException;

/**
 * Thrown if we try to create an user but all mandatory information are not fulfilled
 */
public class IncompleteUserInformationException extends NotidroidException {

    public final static String MESSAGE = "All mandatory fields are not fulfilled";

    public final static String I18N_MESSAGE_ID = "exception.users.incompleteuserinformation";

    public IncompleteUserInformationException() {
        super(MESSAGE, I18N_MESSAGE_ID);
    }

}
