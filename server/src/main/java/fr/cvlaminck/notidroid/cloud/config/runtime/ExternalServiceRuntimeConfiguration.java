package fr.cvlaminck.notidroid.cloud.config.runtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 */
public abstract class ExternalServiceRuntimeConfiguration {

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Username used by the cloudBackend to authenticate itself on the service.
     * This username is also used if the service wants to access the cloudBackend private API.
     */
    private String username = "active-mq";

    /**
     * Password used by the cloudBackend to authenticate itself on the service.
     * This password is also used if the service wants to access the cloudBackend private API.
     */
    private String password = "password";

    /**
     * Address of the server hosting the ActiveMQ server. Must be an URL.
     */
    private String serviceLocation = "localhost";

    /**
     * Url that will be used by the service connector to connect to this service.
     */
    private String connectorUrl = "tcp://localhost:8888";

    /**
     * Return the password encoded using the default password encoder so it
     * can be used by Spring Security framework.
     */
    public String getEncodedPassword() {
        return passwordEncoder.encode(password);
    }

    /**
     * A specific authority that will be granted to the service. This authority can be
     * use to filter the access to the private API from this external service.
     * For ex. only the ActiveMQ service can access the /oauth/check_token endpoint.
     */
    public abstract GrantedAuthority getExternalServiceSpecificGrantedAuthority();

    //Standard getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getConnectorUrl() {
        return connectorUrl;
    }

    public void setConnectorUrl(String connectorUrl) {
        this.connectorUrl = connectorUrl;
    }
}
