package fr.cvlaminck.notidroid.cloud.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * Configuration of Spring Security for the administration side.
 * It will use a basic form based authentication instead of the OAuth2 that is used for the client-api.
 */
@Configuration
@EnableWebMvcSecurity
//@EnableResourceServer
public class AdminSecurityConfiguration
        extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return this.authenticationManager;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                //Static resources like CSS and JS public libraries do not need to be protected.
                .antMatchers("/webjars/**", "/css/**", "/js/**")
                        //Also some parts of the API must be exposed : account creation, server public information, etc...
                .antMatchers("/api/public/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .regexMatcher("/admin/?.*")
                .authorizeRequests()
                        //We configure here our Administration URLs that will be protected and the role that must have the user in its UserDetails
                        //to access the page.
                .antMatchers("/admin/administrators/create-first-admin").permitAll() //The page to create the first administrator must be allowed for all.
                //TODO : Add special roles for each task
                .regexMatchers("/admin/?.*").hasRole("ADMINISTRATOR") //The user must be logged in as an administrator to access the console.
                .and()
                .formLogin() //We configure our sign-in page for administrators.
                .loginPage("/admin/log-in")
                .usernameParameter("txtEmail")
                .passwordParameter("txtPassword")
                .defaultSuccessUrl("/admin") //When an admin is authenticated, we show him his dashboard
                .failureUrl("/admin/log-in")
                .permitAll(); //Everybody can access the login page, looks logic for me.
    }
}
