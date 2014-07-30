package fr.cvlaminck.notidroid.cloud.core.exceptions.users;

import fr.cvlaminck.notidroid.cloud.core.exceptions.NotidroidException;
import org.springframework.http.HttpStatus;

/**
 * Thrown if a client try to sign-in to the server while the registration are
 * not allowed by the configuration or his email is not in the white-list if the
 * server is running in white-list mode.
 */
public class RegistrationAreClosedException
        extends NotidroidException {
    private static String MESSAGE = "Registration are not allowed on this server. Select another one.";

    public RegistrationAreClosedException() {
        super(HttpStatus.FORBIDDEN, MESSAGE);
    }

}
