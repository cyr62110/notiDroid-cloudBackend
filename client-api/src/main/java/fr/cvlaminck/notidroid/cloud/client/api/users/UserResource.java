package fr.cvlaminck.notidroid.cloud.client.api.users;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Representation of an User.
 *
 * @since 0.1
 */
public class UserResource {

    /**
     * Id of this user.
     * Should be used to access other user resources through the REST API.
     * Ex : User Photo /api/users/{id}/photo
     * <p>
     * Client applications must not fill this field when sending user information to the server. The value will be ignored.
     *
     * @since 0.1
     */
    @Null
    private Long id;

    /**
     * User's first name.
     * Must not be null nor empty.
     *
     * @since 0.1
     */
    @NotNull
    @Size(min = 1)
    private String firstName;

    /**
     * User's last name.
     * Must not be null nor empty.
     *
     * @since 0.1
     */
    @NotNull
    @Size(min = 1)
    private String lastName;

    /**
     * User's email address.
     * Must not be null nor empty. It must also look like an email address
     * since an email will be sent to this address to validate the account
     * creation.
     *
     * @since 0.1
     */
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,5}")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
