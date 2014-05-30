package fr.cvlaminck.notidroid.cloud.config.security;

import fr.cvlaminck.notidroid.cloud.core.security.NotidroidClientDetailsService;
import fr.cvlaminck.notidroid.cloud.core.security.NotidroidUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Jeter un oeil a https://github.com/spring-projects/spring-security-oauth-javaconfig
 * Security configuration common to both WebMVC and OAuth2 security.
 */
@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    NotidroidUserDetailsService notidroidUserDetailsService;

    @Bean
    public TokenStore tokenStore() {
        //Oauth2 Token are stored in--memory for now, change to store it in a database
        //TODO : store in database
        return new InMemoryTokenStore();
    }

    @Bean
    @Autowired
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        final ProviderManager providerManager = new ProviderManager();
        //We add our user details service
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(notidroidUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        providerManager.setProviders(Collections.singletonList(daoAuthenticationProvider));
        //Then we return our authentication manager
        return providerManager;
    }

    @Bean
    @Autowired
    public UserApprovalHandler userApprovalHandler(TokenStore tokenStore, NotidroidClientDetailsService notidroidClientDetailsService) {
        final TokenStoreUserApprovalHandler userApprovalHandler = new TokenStoreUserApprovalHandler();
        userApprovalHandler.setTokenStore(tokenStore);
        userApprovalHandler.setClientDetailsService(notidroidClientDetailsService);
        userApprovalHandler.setRequestFactory(new DefaultOAuth2RequestFactory(notidroidClientDetailsService));
        return userApprovalHandler;
    }


}
