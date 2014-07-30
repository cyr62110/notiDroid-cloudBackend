package fr.cvlaminck.notidroid.cloud.core.managers.impl.users;

import fr.cvlaminck.notidroid.cloud.client.api.users.UserResource;
import fr.cvlaminck.notidroid.cloud.client.api.users.UserWithCredentialsResource;
import fr.cvlaminck.notidroid.cloud.core.exceptions.rest.InvalidResourceFormatException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.ExistingUserWithEmailException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.RegistrationAreClosedException;
import fr.cvlaminck.notidroid.cloud.core.managers.api.users.UserManager;
import fr.cvlaminck.notidroid.cloud.core.utils.users.UserInformationUtils;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import fr.cvlaminck.notidroid.cloud.data.repositories.users.UserRepository;
import fr.cvlaminck.remapper.api.ResourceEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class UserManagerImpl
        implements UserManager {

    @Autowired
    private ResourceEntityMapper resourceEntityMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Validator validator;

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserResource registerNewUser(UserWithCredentialsResource userWithCredentials) throws ExistingUserWithEmailException, InvalidResourceFormatException, RegistrationAreClosedException {
        //We use the Validation API to validate the resource received
        final Set<ConstraintViolation<UserWithCredentialsResource>> violations = validator.validate(userWithCredentials);
        if (!violations.isEmpty())
            throw new InvalidResourceFormatException(violations);

        //First, we map the resource on a new entity
        UserEntity userEntity = resourceEntityMapper.convertToEntity(userWithCredentials, UserWithCredentialsResource.class, UserEntity.class);
        //We do not forget to encode the password before storing it
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        //Then, we format the information
        UserInformationUtils.format(userEntity);
        //We validate that no user is actually registered with this email address
        UserInformationUtils.validateEmailNotUsed(userEntity, userRepository);

        //We set some attributes on the new entity
        userEntity.setHasValidatedHisEmail(true); //TODO : set to false when email will be implemented

        //We save this new user in our database so he will be able to login after he has validated his email address
        userEntity = userRepository.save(userEntity);

        //We send an email to the user address to validate that this email exists
        //TODO: send an email so the user can validate its email

        //Finally, we return a REST  representation with the ID
        return resourceEntityMapper.convertToResource(userEntity, UserEntity.class, UserResource.class);
    }

}
