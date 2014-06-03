package fr.cvlaminck.notidroid.cloud.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Main configuration class for this project.
 * The component scan annotation will import all component specific
 * configurations : Spring Data, Spring WebMVC, Spring Security
 */
@Configuration
@ComponentScan(basePackages = {
        "fr.cvlaminck.notidroid"
})
public class NotidroidCloudConfiguration {
}
