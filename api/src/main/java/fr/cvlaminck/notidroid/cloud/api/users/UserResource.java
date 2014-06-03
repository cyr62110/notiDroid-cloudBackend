package fr.cvlaminck.notidroid.cloud.api.users;

/**
 * A User
 */
public class UserResource {

    /**
     * Id of this user.
     * Should be used to access other user resources through the REST API.
     * Ex : User Photo /api/users/{id}/photo
     * <p>
     * Client must not fill this field when sending user information to the server. The value will be ignored.
     */
    private Long id;

    /**
     * User's first name
     */
    private String firstName;

    /**
     * User's last name
     */
    private String lastName;

    /**
     * User's email address
     */
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
