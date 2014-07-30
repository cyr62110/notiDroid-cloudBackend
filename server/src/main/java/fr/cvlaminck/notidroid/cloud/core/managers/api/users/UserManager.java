package fr.cvlaminck.notidroid.cloud.core.managers.api.users;

import fr.cvlaminck.notidroid.cloud.client.api.users.UserResource;
import fr.cvlaminck.notidroid.cloud.client.api.users.UserWithCredentialsResource;
import fr.cvlaminck.notidroid.cloud.core.exceptions.rest.InvalidResourceFormatException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.ExistingUserWithEmailException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.RegistrationAreClosedException;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;

public interface UserManager {

    /**
     * Find the user using the provided address as its email address.
     * Returns null if no account associated with this email address.
     *
     * @param email Address associated to the account we want to find
     * @return an account or null if no account associated to the address
     */
    public UserEntity findUserByEmail(String email);

    /**
     * Register a new user in the system.
     * It will also send an email to this user to validate his email address. The user must validate
     * his email address before logging in and starting to use notidroid cloud services.
     *
     * @param userWithCredentials The user with its credentials.
     * @return The user with its id filled in.
     */
    public UserResource registerNewUser(UserWithCredentialsResource userWithCredentials) throws ExistingUserWithEmailException, InvalidResourceFormatException, RegistrationAreClosedException;

}
