package fr.cvlaminck.notidroid.cloud.core.security.details;

import fr.cvlaminck.notidroid.cloud.config.runtime.ExternalServiceRuntimeConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementation of Spring Security UserDetails for external service
 * accessing the API. For ex. ActiveMQ service accessing the /oauth/check_token
 * endpoint.
 * <p>
 * //TODO
 */
public class ExternalServiceUserDetails
        implements UserDetails {

    private ExternalServiceRuntimeConfiguration externalServiceRuntimeConfiguration = null;

    public ExternalServiceUserDetails(ExternalServiceRuntimeConfiguration externalServiceRuntimeConfiguration) {
        this.externalServiceRuntimeConfiguration = externalServiceRuntimeConfiguration;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        //All external services have the role ROLE_EXTERNAL_SERVICE
        authorities.add(new SimpleGrantedAuthority("ROLE_EXTERNAL_SERVICE"));
        //Then we also add the service specific authority if defined
        GrantedAuthority serviceSpecificAuthority = externalServiceRuntimeConfiguration.getExternalServiceSpecificGrantedAuthority();
        if(serviceSpecificAuthority != null)
            authorities.add(serviceSpecificAuthority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return externalServiceRuntimeConfiguration.getEncodedPassword();
    }

    @Override
    public String getUsername() {
        return externalServiceRuntimeConfiguration.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
