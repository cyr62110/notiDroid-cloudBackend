package fr.cvlaminck.notidroid.cloud.config.webmvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Spring WebMVC configuration. We are only using RestControllers so the configuration is quite
 * easy.
 * <p>
 * If you are using Spring Security Oauth 2, you must use WebMvcConfigurationSupport instead of
 * WebMvcConfigurationAdapter otherwise your Oauth2 method mappings will not be accessible and you
 * will receive 404 http error. In this case, you must not put the @EnableWebMvc annotation anywhere.
 */
@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = {
        "fr.cvlaminck.notidroid.cloud.front",
})
public class WebMVCConfiguration
        extends WebMvcConfigurationSupport {
}
