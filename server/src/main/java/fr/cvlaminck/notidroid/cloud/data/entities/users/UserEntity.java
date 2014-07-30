package fr.cvlaminck.notidroid.cloud.data.entities.users;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.util.Date;

/**
 * An user of the notiDroid cloudbackend.
 * Administrator are also considered as user.
 */
@NodeEntity
public class UserEntity {

    @GraphId
    private Long id;

    /**
     * First name of the user.
     */
    public String firstName;

    /**
     * Last name of the user.
     */
    public String lastName;

    /**
     * Email of the user
     */
    @Indexed
    private String email;

    /**
     * Password of the current user.
     * The real password is salted and hashed before being stored in the database
     */
    private String password;

    /**
     * When this user has created its account on the backend.
     */
    private Date accountCreationDate;

    /**
     * When the user has last validated its email address.
     * This value may change if the user changes its email address.
     */
    private Date emailValidationDate;

    /**
     * Indicate whether or not the user has validated his email address.
     * An user cannot login until its email is validated.
     */
    private boolean hasValidatedHisEmail;

    public UserEntity() {
        accountCreationDate = new Date();
        hasValidatedHisEmail = false;
        emailValidationDate = null;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getAccountCreationDate() {
        return accountCreationDate;
    }

    public Date getEmailValidationDate() {
        return emailValidationDate;
    }

    public void setEmailValidationDate(Date emailValidationDate) {
        this.emailValidationDate = emailValidationDate;
    }

    public boolean hasValidatedHisEmail() {
        return hasValidatedHisEmail;
    }

    public void setHasValidatedHisEmail(boolean hasValidatedHisEmail) {
        this.hasValidatedHisEmail = hasValidatedHisEmail;
    }
}
