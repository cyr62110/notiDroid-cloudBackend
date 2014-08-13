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

    /**
     * Number of connection that will be cached for this service.
     */
    private int connectionPoolSize = 1;

    public int getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public void setConnectionPoolSize(int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    @Override
    public GrantedAuthority getExternalServiceSpecificGrantedAuthority() {
        return new SimpleGrantedAuthority("ROLE_MESSAGE_BROKER");
    }
}
