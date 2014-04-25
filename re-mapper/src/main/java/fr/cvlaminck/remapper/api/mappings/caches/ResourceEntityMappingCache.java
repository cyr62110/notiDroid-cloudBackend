package fr.cvlaminck.remapper.api.mappings.caches;

import fr.cvlaminck.remapper.api.mappings.ResourceEntityMapping;

/**
 * Cache that stores mapping between entities and resources so they can be exploited
 * to speed up the process. The ResourceEntityMapper will not to create a new
 * mapping from scratch and reuse the existing one instead.
 */
public interface ResourceEntityMappingCache {

    public ResourceEntityMapping getMapping(Class<?> entity, Class<?> resource);

    public void store(ResourceEntityMapping resourceEntityMapping);

}
