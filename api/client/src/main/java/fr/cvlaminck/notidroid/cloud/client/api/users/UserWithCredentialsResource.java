package fr.cvlaminck.notidroid.cloud.client.api.users;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * An User with its credentials.
 * This resource must be sent by a client app to the server
 * when the user is creating its account, or modifying its password.
 * Except those two cases, clients and servers MUST only use the UserResource
 * instead of this one.
 *
 * @since 0.1
 */
public class UserWithCredentialsResource
        extends UserResource {

    /**
     * Password that the user will use during the log-in procedure.
     * Must not be null nor empty.
     *
     * @since 0.1
     */
    @NotNull
    @Size(min = 1)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
