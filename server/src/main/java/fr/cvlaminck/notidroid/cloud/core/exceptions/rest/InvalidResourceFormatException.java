package fr.cvlaminck.notidroid.cloud.core.exceptions.rest;

import fr.cvlaminck.notidroid.cloud.core.exceptions.NotidroidException;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Exception thrown when the resource sent to the server by
 * the client does not follow the format specified in the
 * API. The format is validated using the javax.validation
 * package.
 */
public class InvalidResourceFormatException
        extends NotidroidException {

    private final static String MESSAGE = "The format of the resource does not follow the format specified in the API";

    /**
     * All constraint violations that have been found on the sent resource.
     * Formatted as string written in english which is the official language of the API.
     */
    private Collection<String> constraintViolations = null;

    public InvalidResourceFormatException(String message) {
        super(message);
    }

    public <T> InvalidResourceFormatException(Collection<ConstraintViolation<T>> constraintViolations) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, MESSAGE);
        this.constraintViolations = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            this.constraintViolations.add(constraintViolation.getPropertyPath().toString() + " " + constraintViolation.getMessage());
        }
    }

    public Collection<String> getConstraintViolations() {
        return constraintViolations;
    }
}
