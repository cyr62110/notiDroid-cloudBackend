package fr.cvlaminck.notidroid.cloud.config;

import fr.cvlaminck.notidroid.cloud.Application;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration allowing the server to start with an embedded Jetty.
 * We do not scan the configuration package
 */
@Configuration
@ComponentScan(basePackages = {
        "fr.cvlaminck.notidroid.cloud.core",
        "fr.cvlaminck.notidroid.cloud.front",
        "fr.cvlaminck.notidroid.cloud.data"
})
public class EmbeddedJettyConfiguration {

    @Bean
    EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        return new JettyEmbeddedServletContainerFactory();
    }

}
