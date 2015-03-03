package fr.cvlaminck.notidroid.cloud.config.security;

import fr.cvlaminck.notidroid.cloud.core.security.services.NotidroidClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Order(-1)
@Configuration
@EnableWebMvcSecurity
public class APISecurityConfiguration
        extends WebSecurityConfigurerAdapter {

    public static final String NOTIDROID_RESSOURCE_ID = "notidroid";

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/api/client/public/**")
                .antMatchers("/api/admin/public/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers(HttpMethod.OPTIONS, "/oauth/token", "/api/**")
            .and()
                .csrf().disable()
            .authorizeRequests().anyRequest().permitAll()
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Configuration
    @EnableResourceServer
    public static class ResourceServerConfiguration
            extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**")
                    .authorizeRequests()
                            //Client API allowing the communication between clients (Web, mobile) and the backend.
                            //Some endpoints are not protected otherwise no user will be able to register himself. Otherwise, all other client API
                            //endpoints require the user to be authenticated.
                    .antMatchers("/api/client/**").access("#oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('client-api')")
                    //Admin API allowing the communication between the administration frontend and the backend. Only administrators can access those endpoints
                    .antMatchers("/api/admin/**").access("#oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('admin-api')")
                    //Service API is only accessible by registered external service.
                    .antMatchers("/api/service/**").access("#oauth2.clientHasRole('ROLE_EXTERNAL_SERVICE') and #oauth2.hasScope('service-api')")
            ;
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
            //.tokenServices(DefaultTokenServices) Change this to generate refresh_token... may be worth it.
            //.pathMapping("/oauth/token", "/api/public/oauth/token");
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.realm("notidroid")
                    .checkTokenAccess("hasRole('ROLE_EXTERNAL_SERVICE')"); //External services are allowed to use the check_token endpoint to authenticate users that connect to them.
        }

    }
}
