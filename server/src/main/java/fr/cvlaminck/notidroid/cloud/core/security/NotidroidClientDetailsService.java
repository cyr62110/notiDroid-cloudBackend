package fr.cvlaminck.notidroid.cloud.core.security;

import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import fr.cvlaminck.notidroid.cloud.data.repositories.users.UserRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

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

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        if (clientId == null)
            throw new ClientRegistrationException("clientId query parameter is mandatory");
        //We need to decode the client id before trying to retrieve our user in the database
        //clientId is user email encoded using Base64URL.
        UserEntity user = userRepository.findByEmail(clientId);
        if (user == null)
            throw new ClientRegistrationException("User with client id '" + clientId + "' is not registered");

        return new NotidroidClientDetails(user);
    }

}
