package fr.cvlaminck.notidroid.cloud.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
public class AdminSecurityConfiguration
    extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //We use an inMemoryAuthentication for now, will be replaced by a proper UserService working on Neo4J
        auth.inMemoryAuthentication()
                .withUser("admin@notidroid.fr").password("admin").roles("ADMINISTRATOR");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //Static resources like CSS and JS public libraries do not need to be protected.
        web.ignoring().antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .antMatcher("/admin/**") //This security filter only apply on the administration side of the project
            .authorizeRequests()
                //We configure here our URLs that will be protected and the role that must have the user in its UserDetails
                //to access the page.
                //.antMatchers("/admin/sign-in").permitAll()
                .antMatchers("/admin/administrators/create-first-admin").permitAll() //The page to create the first administrator must be allowed for all.
                .regexMatchers("/admin/?.*").hasRole("ADMINISTRATOR") //The user must be logged in as an administrator to access the console.
                //TODO : Add special roles for each task
        .and()
                //We configure our sign-in page for administrators.
            .formLogin()
                .loginPage("/admin/log-in")
                .usernameParameter("txtEmail")
                .passwordParameter("txtPassword")
                .defaultSuccessUrl("/admin") //When an admin is authenticated, we show him its dashboard
                .failureUrl("/admin/log-in?failed")
                .permitAll(); //Everybody can access the sign-in page, looks logic for me.
    }
}
