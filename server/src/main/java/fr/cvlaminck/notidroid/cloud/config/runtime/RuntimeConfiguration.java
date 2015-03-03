package fr.cvlaminck.notidroid.cloud.config.runtime;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration that will load all runtime configuration
 * as Spring bean so they can be easily manipulated.
 * <p>
 * Runtime configurations are loaded from a JSON config file. Some configurations can be changed during
 * runtime. Changes done to the configuration during runtime are nether saved on the file.
 */
@Configuration
@ComponentScan("fr.cvlaminck.notidroid.cloud.config.runtime")
public class RuntimeConfiguration {
}
