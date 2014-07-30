package fr.cvlaminck.notidroid.cloud.config.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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

    /**
     * JSR-compliant validator that will be used by managers to validate
     * resources coming from all REST controllers.
     */
    @Bean
    public Validator validator() {
        final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }

}
