package fr.cvlaminck.notidroid.cloud.api.users;

/**
 * User with its credentials.
 * Send by a client to the server to create an account for this user.
 */
public class UserWithCredentialsResource
        extends UserResource {

    /**
     * New password for this user
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
