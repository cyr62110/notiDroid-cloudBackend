package fr.cvlaminck.remapper.api.mappings.caches;

import fr.cvlaminck.remapper.api.mappings.Object2ObjectMapping;

/**
 * Cache that stores mapping between entities and resources so they can be exploited
 * to speed up the process. The ResourceEntityMapper will not to create a new
 * mapping from scratch and reuse the existing one instead.
 */
public interface Object2ObjectMappingCache {

    public Object2ObjectMapping getMapping(Class<?> entity, Class<?> resource);

    public void store(Object2ObjectMapping object2ObjectMapping);

}
