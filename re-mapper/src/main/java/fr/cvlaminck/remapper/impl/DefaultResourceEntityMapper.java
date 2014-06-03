package fr.cvlaminck.remapper.impl;

import fr.cvlaminck.remapper.api.ResourceEntityMapper;
import fr.cvlaminck.remapper.api.exceptions.runtime.ResourceOrEntityCannotBeInstantiatedException;
import fr.cvlaminck.remapper.api.fieldconverters.FieldConvertersContainer;
import fr.cvlaminck.remapper.api.mappings.ResourceEntityFieldMapping;
import fr.cvlaminck.remapper.api.mappings.ResourceEntityMapping;
import fr.cvlaminck.remapper.api.mappings.caches.ResourceEntityMappingCache;
import fr.cvlaminck.remapper.impl.fieldconverters.DefaultFieldConvertersContainer;
import fr.cvlaminck.remapper.impl.mappings.DefaultResourceEntityMappingBuilder;
import org.apache.commons.lang3.reflect.ConstructorUtils;

public class DefaultResourceEntityMapper
        implements ResourceEntityMapper {

    /**
     * Container of all field converters that can be use to map the data.
     * Developers can add new field converters using this source code :
     * ((DefaultFieldConvertersContainer) getFieldConvertersContainer()).addFieldConverter(...)
     */
    private FieldConvertersContainer fieldConvertersContainer = null;

    /**
     * Cache storing all mapping that have been computed to map entities and resources.
     */
    private ResourceEntityMappingCache resourceEntityMappingCache = null;

    /**
     * Builder used to compute mapping between resources and entities.
     */
    private DefaultResourceEntityMappingBuilder resourceEntityMappingBuilder = null;

    public DefaultResourceEntityMapper() {
        fieldConvertersContainer = new DefaultFieldConvertersContainer();
        resourceEntityMappingBuilder = new DefaultResourceEntityMappingBuilder();
        resourceEntityMappingCache = null;
    }

    @Override
    public FieldConvertersContainer getFieldConvertersContainer() {
        return fieldConvertersContainer;
    }

    @Override
    public void setResourceEntityMappingCache(ResourceEntityMappingCache resourceEntityMappingCache) {
        this.resourceEntityMappingCache = resourceEntityMappingCache;
    }

    @Override
    public <E, R> void prepareMapping(Class<R> resource, Class<E> entity) {
        if (resourceEntityMappingCache != null)
            getMapping(resource, entity);
    }

    @Override
    public <E, R> R convertToResource(E entity, Class<E> entityType, Class<R> resourceType) {
        ResourceEntityMapping mapping = getMapping(resourceType, entityType);
        R resource = instantiateUsingDefaultConstructor(resourceType);
        for (ResourceEntityFieldMapping fieldMapping : mapping)
            fieldMapping.copyFromEntity(entity, resource);
        return resource;
    }

    @Override
    public <E, R> E convertToEntity(R resource, Class<R> resourceType, Class<E> entityType) {
        ResourceEntityMapping mapping = getMapping(resourceType, entityType);
        E entity = instantiateUsingDefaultConstructor(entityType);
        for (ResourceEntityFieldMapping fieldMapping : mapping)
            fieldMapping.copyFromResource(resource, entity);
        return entity;
    }

    private <EoR> EoR instantiateUsingDefaultConstructor(Class<EoR> resourceOrEntityType) {
        try {
            return ConstructorUtils.invokeConstructor(resourceOrEntityType);
        } catch (Exception e) {
            throw new ResourceOrEntityCannotBeInstantiatedException(resourceOrEntityType, e);
        }
    }

    /**
     * Provide a mapping between the resource and the entity.
     * If this mapper have no cache or the cache does not contain this mapping, it will build a new mapping
     * using the mapping builder. Otherwise, it will return the one store in cache.
     *
     * @param resource
     * @param entity
     * @param <E>
     * @param <R>
     * @return
     */
    private <E, R> ResourceEntityMapping getMapping(Class<R> resource, Class<E> entity) {
        //We try to retrieve the mapping from cache
        ResourceEntityMapping resourceEntityMapping = (resourceEntityMappingCache != null) ?
                resourceEntityMappingCache.getMapping(entity, resource) : null;
        //If not, we build a new one
        if (resourceEntityMapping == null)
            resourceEntityMapping = resourceEntityMappingBuilder.buildMapping(resource, entity);
        //And store it in cache, if available
        if (resourceEntityMappingCache != null) resourceEntityMappingCache.store(resourceEntityMapping);
        return resourceEntityMapping;
    }
}

