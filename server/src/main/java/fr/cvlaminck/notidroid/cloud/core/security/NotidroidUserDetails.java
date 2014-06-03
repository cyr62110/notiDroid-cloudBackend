package fr.cvlaminck.notidroid.cloud.core.security;

import fr.cvlaminck.notidroid.cloud.data.entities.users.AdministratorEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;

/**
 * UserDetails representation of notidroid user.
 * Used by Spring Security.
 */
public class NotidroidUserDetails
        implements UserDetails {

    private UserEntity userEntity = null;

    private Collection<? extends GrantedAuthority> authorities = null;

    public NotidroidUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            LinkedList<GrantedAuthorityImpl> authorities = new LinkedList<GrantedAuthorityImpl>();
            //All user has at least the USER role to access the api
            authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
            //Then if the user is an administrator, we will add the ADMINISTRATOR role and
            //all permissions that we have granted to him
            if (userEntity instanceof AdministratorEntity) {
                authorities.add(new GrantedAuthorityImpl("ROLE_ADMINISTRATOR"));
                //TODO : add all permissions
            }
            this.authorities = authorities;
        }
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
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
        //User credentials never expires
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
