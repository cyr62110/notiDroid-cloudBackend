package fr.cvlaminck.notidroid.cloud.core.security;

import fr.cvlaminck.notidroid.cloud.config.security.APISecurityConfiguration;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

/**
 * ClientDetails representation of a notidroid user.
 * Used by Spring Security OAuth2.
 * <p>
 * Since Oauth2 is only used on the client side REST API, all user have the same
 * rights.
 */
public class NotidroidClientDetails
        implements ClientDetails {

    private static Base64 base64 = new Base64(true);

    private UserEntity userEntity;

    private Collection<GrantedAuthority> authorities = null;

    public NotidroidClientDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * The client id is the email of the user encoded using base64 URL.
     * We use the UTF-8 charset to encode so we ensure interoperability
     */
    @Override
    public String getClientId() {
        return userEntity.getEmail();
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
        return userEntity.getPassword();
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        final Set<String> scopes = new HashSet<>();
        scopes.add("read");
        scopes.add("write");
        return scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        final Set<String> grantTypes = new HashSet<>();
        grantTypes.add("client_credentials"); //Only client_credentials grant is supported for all clients
        //TODO : add more grant_type. For ex. refresh_token
        return grantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return new HashSet<>();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (this.authorities == null) {
            LinkedList<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
            //All user has at least the USER role to access the api
            authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
            this.authorities = authorities;
        }
        return this.authorities;
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
        return new HashMap<>();
    }
}
