package fr.cvlaminck.notidroid.cloud.core.security.services;

import fr.cvlaminck.notidroid.cloud.config.runtime.ExternalServiceRuntimeConfiguration;
import fr.cvlaminck.notidroid.cloud.core.security.details.ExternalServiceUserDetails;
import fr.cvlaminck.notidroid.cloud.core.security.details.NotidroidUserDetails;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import fr.cvlaminck.notidroid.cloud.data.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Components required by Spring security that convert information about
 * user retrieved in the Neo4J database in a representation usable by
 * Spring Security.
 */
@Component
public class NotidroidUserDetailsService
        implements UserDetailsService {

    @Autowired
    private Collection<ExternalServiceRuntimeConfiguration> externalServiceRuntimeConfigurations;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Some external services (ActiveMQ, etc...) that are also part of the project use authentication
        //to access to the API. So we need to handle them.
        for(ExternalServiceRuntimeConfiguration externalServiceRuntimeConfiguration : externalServiceRuntimeConfigurations) {
            if(externalServiceRuntimeConfiguration.getUsername().equals(username)) {
                return new ExternalServiceUserDetails(externalServiceRuntimeConfiguration);
            }
        }

        //If the username does not belong to an external service, it must be an username associated to an actual user.
        //So we check if the email is associated with an user account.
        UserEntity user = userRepository.findByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException("No user registered with this email address '" + username + "'");

        //Then we create the representation of the User for Spring Security using a custom UserDetails implementation.
        return new NotidroidUserDetails(user);
    }
}
