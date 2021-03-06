package fr.cvlaminck.notidroid.cloud.core.managers.impl.users;

import fr.cvlaminck.notidroid.cloud.client.api.users.UserResource;
import fr.cvlaminck.notidroid.cloud.client.api.users.UserWithCredentialsResource;
import fr.cvlaminck.notidroid.cloud.core.exceptions.rest.InvalidResourceFormatException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.ExistingUserWithEmailException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.RegistrationAreClosedException;
import fr.cvlaminck.notidroid.cloud.core.managers.api.users.UserManager;
import fr.cvlaminck.notidroid.cloud.core.utils.users.UserInformationUtils;
import fr.cvlaminck.notidroid.cloud.data.entities.users.PermissionEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import fr.cvlaminck.notidroid.cloud.data.repositories.users.UserRepository;
import fr.cvlaminck.remapper.api.Object2ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

@Component
public class UserManagerImpl
        implements UserManager {

    @Autowired
    private Object2ObjectMapper object2ObjectMapper;

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
    public int getNumberOfAdministrator() {
        return 0;
    }

    @Override
    public UserResource registerNewAdministrator(UserWithCredentialsResource userWithCredentials) throws ExistingUserWithEmailException, InvalidResourceFormatException {
        final Set<ConstraintViolation<UserWithCredentialsResource>> violations = validator.validate(userWithCredentials);
        if (!violations.isEmpty())
            throw new InvalidResourceFormatException(violations);

        UserEntity userEntity = object2ObjectMapper.convertToEntity(userWithCredentials, UserWithCredentialsResource.class, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        UserInformationUtils.format(userEntity);
        UserInformationUtils.validateEmailNotUsed(userEntity, userRepository);

        userEntity.setHasValidatedHisEmail(true); //Administrator emails are not validated
        userEntity.setEmailValidationDate(new Date());
        userEntity.getPermissions().addAll(Arrays.asList(PermissionEntity.values()));

        userEntity = userRepository.save(userEntity);

        return object2ObjectMapper.convertToResource(userEntity, UserEntity.class, UserResource.class);
    }

    @Override
    public UserResource registerNewUser(UserWithCredentialsResource userWithCredentials) throws ExistingUserWithEmailException, InvalidResourceFormatException, RegistrationAreClosedException {
        final Set<ConstraintViolation<UserWithCredentialsResource>> violations = validator.validate(userWithCredentials);
        if (!violations.isEmpty())
            throw new InvalidResourceFormatException(violations);

        UserEntity userEntity = object2ObjectMapper.convertToEntity(userWithCredentials, UserWithCredentialsResource.class, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        UserInformationUtils.format(userEntity);
        UserInformationUtils.validateEmailNotUsed(userEntity, userRepository);

        userEntity.setHasValidatedHisEmail(true); //TODO : set to false when email will be implemented

        userEntity = userRepository.save(userEntity);

        //We send an email to the user address to validate that this email exists
        //TODO: send an email so the user can validate its email

        return object2ObjectMapper.convertToResource(userEntity, UserEntity.class, UserResource.class);
    }

}
