package fr.cvlaminck.notidroid.cloud.core.exceptions.users;

import fr.cvlaminck.notidroid.cloud.core.exceptions.NotidroidException;
import org.springframework.http.HttpStatus;

/**
 * Thrown if the authenticated user try to execute an operation on someone
 * else account without having the right to do. For ex. adding a device
 * to someone else account.
 */
public class CrossUserOperationException extends NotidroidException {
    private final static String MESSAGE = "You do not have the permission to execute this operation on someone else account.";

    public CrossUserOperationException() {
        super(HttpStatus.FORBIDDEN, MESSAGE);
    }
}
