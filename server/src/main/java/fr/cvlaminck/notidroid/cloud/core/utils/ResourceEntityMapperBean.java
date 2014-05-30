package fr.cvlaminck.notidroid.cloud.core.utils;

import fr.cvlaminck.remapper.api.ResourceEntityMapper;
import fr.cvlaminck.remapper.impl.DefaultResourceEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Provides the ResourceEntityMapper as a Spring Bean so it can be
 * injected in all managers.
 */
@Component
public class ResourceEntityMapperBean {

    @Bean
    public ResourceEntityMapper resourceEntityMapper() {
        final ResourceEntityMapper resourceEntityMapper = new DefaultResourceEntityMapper();
        //TODO : configure the re-mapper here
        return resourceEntityMapper;
    }


}
