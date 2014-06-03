package fr.cvlaminck.notidroid.cloud.config.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration that will make the Spring framework instantiate
 * managers and core helpers through the component scan annotation.
 */
@Configuration
@ComponentScan(basePackages = {
        "fr.cvlaminck.notidroid.cloud.core.managers",
        "fr.cvlaminck.notidroid.cloud.core.utils"
})
public class CoreConfiguration {
}
