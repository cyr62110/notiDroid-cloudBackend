package fr.cvlaminck.notidroid.cloud.admin.api.users;

public class UserWithCredentialsResource
    extends UserResource {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
