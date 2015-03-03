package fr.cvlaminck.notidroid.cloud.core.security.details;

import fr.cvlaminck.notidroid.cloud.config.runtime.ExternalServiceRuntimeConfiguration;
import fr.cvlaminck.notidroid.cloud.config.security.APISecurityConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

/**
 *
 */
public class ExternalServiceClientDetails
        implements ClientDetails {

    private ExternalServiceRuntimeConfiguration externalServiceRuntimeConfiguration;

    public ExternalServiceClientDetails(ExternalServiceRuntimeConfiguration externalServiceRuntimeConfiguration) {
        this.externalServiceRuntimeConfiguration = externalServiceRuntimeConfiguration;
    }

    @Override
    public String getClientId() {
        return externalServiceRuntimeConfiguration.getUsername();
    }

    @Override
    public Set<String> getResourceIds() {
        return Collections.singleton(APISecurityConfiguration.NOTIDROID_RESSOURCE_ID);
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return externalServiceRuntimeConfiguration.getEncodedPassword();
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        final Set<String> scopes = new HashSet<>();
        scopes.add("service-api");
        //TODO: Each service has its own scope allowing it to access some endpoints exposed through the Service API.
        scopes.add("mq");
        return scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return Collections.emptySet();
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Collections.emptySet();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        //All external services have the role ROLE_EXTERNAL_SERVICE
        authorities.add(new SimpleGrantedAuthority("ROLE_EXTERNAL_SERVICE"));
        //Then we also add the service specific authority if defined
        GrantedAuthority serviceSpecificAuthority = externalServiceRuntimeConfiguration.getExternalServiceSpecificGrantedAuthority();
        if (serviceSpecificAuthority != null)
            authorities.add(serviceSpecificAuthority);
        return authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        //We use the default framework value for access token validity
        return null;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        //Refresh token are not used in the current system
        return Integer.valueOf(0);
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.emptyMap();
    }
}
