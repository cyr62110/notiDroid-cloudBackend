package fr.cvlaminck.notidroid.cloud.config.webmvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring WebMVC configuration
 */
@Configuration
@EnableWebMvc
public class WebMVCConfiguration
    extends WebMvcConfigurerAdapter {
}
