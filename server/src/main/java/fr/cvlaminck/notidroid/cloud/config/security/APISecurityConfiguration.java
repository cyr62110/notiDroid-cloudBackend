package fr.cvlaminck.notidroid.cloud.config.security;

import fr.cvlaminck.notidroid.cloud.core.security.services.NotidroidClientDetailsService;
import fr.cvlaminck.notidroid.cloud.core.security.stores.RepositoryBasedTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class APISecurityConfiguration {

    public static final String NOTIDROID_RESSOURCE_ID = "notidroid";

    @Configuration
    @EnableResourceServer
    public static class ResourceServerConfiguration
            extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    //Almost all endpoints under /api are protected using the Spring Security OAuth2 framework. Only endpoint under /api/public are not protected at all.
                    .antMatchers("/api/private/**").access("#oauth2.clientHasRole('ROLE_EXTERNAL_SERVICE') and #oauth2.hasScope('private-api')") //Private API is only accessible by registered external service.
                    .antMatchers("/api/**").access("#oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('client-api')"); //Non enumerated endpoints are part of the public API and requires the 'client-api' scope.
        }
    }

    @Configuration
    @EnableAuthorizationServer
    public static class AuthorizationServerConfiguration
            extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private TokenStore tokenStore;

        @Autowired
        private UserApprovalHandler userApprovalHandler;

        @Autowired
        private NotidroidClientDetailsService notidroidClientDetailsService;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(notidroidClientDetailsService);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .tokenStore(tokenStore)
                    .authenticationManager(authenticationManager);
                    //.pathMapping("/oauth/token", "/api/public/oauth/token");
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.realm("notidroid")
                       .checkTokenAccess("hasRole('ROLE_EXTERNAL_SERVICE')"); //External services are allowed to use the check_token endpoint to authenticate users that connect to them.
        }

    }
}
