package fr.cvlaminck.notidroid.cloud.core.managers.impl.users;

import fr.cvlaminck.notidroid.cloud.core.exceptions.users.ExistingUserWithEmailException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.IncompleteUserInformationException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.users.InvalidEmailFormatException;
import fr.cvlaminck.notidroid.cloud.core.managers.api.users.AdministratorManager;
import fr.cvlaminck.notidroid.cloud.core.utils.users.UserInformationUtils;
import fr.cvlaminck.notidroid.cloud.data.entities.users.AdministratorEntity;
import fr.cvlaminck.notidroid.cloud.data.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AdministratorManagerImpl
        implements AdministratorManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean hasAtLeastOneAdministrator() {
        return userRepository.getNumberOfAdministrator() != 0;
    }

    @Override
    public void createAdministrator(AdministratorEntity administrator) throws IncompleteUserInformationException, InvalidEmailFormatException, ExistingUserWithEmailException {
        //First, we format user information so we can manipulate them
        UserInformationUtils.format(administrator);
        //We check if we have everything to create our administrator
        UserInformationUtils.validateUserInformation(administrator);
        //We check that there is no existing user with this email
        UserInformationUtils.validateEmailNotUsed(administrator, userRepository);

        //Then, we encode the password before storing it in the database
        final String encodedPassword = passwordEncoder.encode(administrator.getPassword());
        administrator.setPassword(encodedPassword);

        //An administrator do not require to validate his email
        administrator.setEmailValidationDate(new Date());
        administrator.setHasValidatedHisEmail(true);

        //Finally, we save the new administrator in our database
        userRepository.save(administrator);
    }

}
