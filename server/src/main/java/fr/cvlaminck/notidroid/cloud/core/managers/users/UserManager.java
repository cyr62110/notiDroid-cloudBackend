package fr.cvlaminck.notidroid.cloud.core.managers.users;

import fr.cvlaminck.notidroid.cloud.api.users.UserResource;
import fr.cvlaminck.notidroid.cloud.api.users.UserWithCredentialsResource;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.ExistingUserWithEmailException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.IncompleteUserInformationException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.InvalidEmailFormatException;
import fr.cvlaminck.notidroid.cloud.core.managers.users.utils.UserInformationUtils;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import fr.cvlaminck.notidroid.cloud.data.repositories.users.UserRepository;
import fr.cvlaminck.remapper.api.ResourceEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManager {

    @Autowired
    private ResourceEntityMapper resourceEntityMapper;

    @Autowired
    private UserRepository userRepository;

    /**
     * Register a new user in the system.
     * It will also send an email to this user to validate his email address. The user must validate
     * his email address before logging in and starting to use notidroid cloud services.
     *
     * @param userWithCredentials The user with its credentials.
     * @return The user with its id filled in.
     */
    public UserResource registerNewUser(UserWithCredentialsResource userWithCredentials) throws InvalidEmailFormatException, IncompleteUserInformationException, ExistingUserWithEmailException {
        //First, we map the resource on a new entity
        UserEntity userEntity = resourceEntityMapper.convertToEntity(userWithCredentials, UserWithCredentialsResource.class, UserEntity.class);

        //Then, we format the information
        UserInformationUtils.format(userEntity);
        //and check if everything is valid
        UserInformationUtils.validateUserInformation(userEntity);
        UserInformationUtils.validateEmailNotUsed(userEntity, userRepository);

        //We set some attributes on the new entity
        userEntity.setHasValidatedHisEmail(true); //TODO : set to false when email will be implemented

        //We save this new user in our database so he will be able to login after he has validated his email address
        userRepository.save(userEntity);

        //We send an email to the user address to validate that this email exists
        //TODO: send an email so the user can validate its email

        //Finally, we return a REST  representation with the ID
        return resourceEntityMapper.convertToResource(userEntity, UserEntity.class, UserResource.class);
    }

}
