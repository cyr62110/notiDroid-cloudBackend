package fr.cvlaminck.remapper.api.mappings;

/**
 * Cache that stores mapping between entities and resources so they can be exploited
 * to speed up the process. The ResourceEntityMapper will not to create a new
 * mapping from scratch and reuse the existing one instead.
 */
public interface ResourceEntityMappingCache {

    public ResourceEntityMapping getMappingFor(Class<?> entity, Class<?> resource);

}
