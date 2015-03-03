package fr.cvlaminck.notidroid.cloud.core.exceptions.rest;

import fr.cvlaminck.notidroid.cloud.core.exceptions.NotidroidException;
import org.springframework.http.HttpStatus;

/**
 * Thrown if an argument passed in the URL does not follow the format specified in the
 * API. The argument can be a path variable or a query parameter.
 */
public class InvalidArgumentFormatException
        extends NotidroidException {

    public InvalidArgumentFormatException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
