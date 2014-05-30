package fr.cvlaminck.notidroid.cloud.core.security;

import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import fr.cvlaminck.notidroid.cloud.data.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Components required by Spring security that convert information about
 * user retrieved in the Neo4J database in a representation usable by
 * Spring Security.
 */
@Component
public class NotidroidUserDetailsService
        implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //First, we are looking after our user
        UserEntity user = null;//userRepository.findByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException("No user registered with this email address '" + username + "'");

        //Then we create the representation for Spring Security
        return new NotidroidUserDetails(user);
    }
}
