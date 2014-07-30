package fr.cvlaminck.notidroid.cloud.config.runtime;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration that will load all runtime configuration
 * as Spring bean so they can be easily manipulated.
 */
@Configuration
@ComponentScan("fr.cvlaminck.notidroid.cloud.config.runtime")
public class RuntimeConfiguration {
}
