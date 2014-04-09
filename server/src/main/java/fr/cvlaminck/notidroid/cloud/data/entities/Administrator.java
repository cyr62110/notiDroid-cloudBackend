package fr.cvlaminck.notidroid.cloud.data.entities;

import org.springframework.data.annotation.Id;

/**
 * This class represents an user that can access the administration.
 * Administrator and User are fully separated because they do not have the
 * same tasks. An administrator can have the same email and username than a
 * user because they are fully separated.
 */
public class Administrator {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public Administrator() {
    }

    public String getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
