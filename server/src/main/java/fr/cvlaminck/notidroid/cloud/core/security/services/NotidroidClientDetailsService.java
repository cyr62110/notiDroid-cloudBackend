package fr.cvlaminck.notidroid.cloud.core.security.services;

import fr.cvlaminck.notidroid.cloud.config.runtime.ExternalServiceRuntimeConfiguration;
import fr.cvlaminck.notidroid.cloud.core.security.details.ExternalServiceClientDetails;
import fr.cvlaminck.notidroid.cloud.core.security.details.NotidroidClientDetails;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import fr.cvlaminck.notidroid.cloud.data.repositories.users.UserRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Components required by Spring security that convert information about
 * user retrieved in the Neo4J database in a representation usable by
 * Spring Security Oauth2.
 */
@Component
public class NotidroidClientDetailsService
        implements ClientDetailsService {

    private Base64 base64 = new Base64(true);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Collection<ExternalServiceRuntimeConfiguration> externalServiceRuntimeConfigurations;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        if (clientId == null)
            throw new ClientRegistrationException("clientId query parameter is mandatory");

        //First, we check if the clientId does not belong to an external service
        for(ExternalServiceRuntimeConfiguration externalServiceRuntimeConfiguration : externalServiceRuntimeConfigurations) {
            if(externalServiceRuntimeConfiguration.getUsername().equals(clientId)) {
                return new ExternalServiceClientDetails(externalServiceRuntimeConfiguration);
            }
        }

        //If the clientId does not belong to an external service, we try to find an user account
        //using the clientId as email.
        UserEntity user = userRepository.findByEmail(clientId);
        if (user == null)
            throw new ClientRegistrationException("User with client id '" + clientId + "' is not registered");

        return new NotidroidClientDetails(user);
    }

}
