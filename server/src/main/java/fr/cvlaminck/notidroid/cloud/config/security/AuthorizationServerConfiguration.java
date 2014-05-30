package fr.cvlaminck.notidroid.cloud.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * The authorization server is the component that provide tokens in OAuth2. We want our own
 * OAuth2 provider so the project can be independent of other providers like Google, Facebook.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration
        extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.realm("notidroid");
    }

}
