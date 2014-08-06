package fr.cvlaminck.notidroid.cloud.config.runtime;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ActiveMQRuntimeConfiguration
        extends ExternalServiceRuntimeConfiguration {

    @Override
    public GrantedAuthority getExternalServiceSpecificGrantedAuthority() {
        return new SimpleGrantedAuthority("ROLE_MESSAGE_BROKER");
    }
}
