package fr.cvlaminck.notidroid.cloud.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

}
