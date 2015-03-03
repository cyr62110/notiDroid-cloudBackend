package fr.cvlaminck.notidroid.cloud.core.utils;

import fr.cvlaminck.remapper.api.Object2ObjectMapper;
import fr.cvlaminck.remapper.impl.DefaultObject2ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Provides the ResourceEntityMapper as a Spring Bean so it can be
 * injected in all managers.
 */
@Component
public class ResourceEntityMapperBean {

    @Bean
    public Object2ObjectMapper resourceEntityMapper() {
        final Object2ObjectMapper object2ObjectMapper = new DefaultObject2ObjectMapper();
        //TODO : configure the re-mapper here
        return object2ObjectMapper;
    }


}
