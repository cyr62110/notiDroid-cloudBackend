package fr.cvlaminck.remapper.impl;

import fr.cvlaminck.remapper.api.Object2ObjectMapper;
import fr.cvlaminck.remapper.api.converters.containers.ObjectConvertersContainer;
import fr.cvlaminck.remapper.api.converters.strategies.ObjectConverterSelectionStrategy;
import fr.cvlaminck.remapper.api.exceptions.runtime.DstObjectCannotBeInstantiatedException;
import fr.cvlaminck.remapper.api.mappings.Object2ObjectFieldMapping;
import fr.cvlaminck.remapper.api.mappings.Object2ObjectMapping;
import fr.cvlaminck.remapper.api.mappings.caches.Object2ObjectMappingCache;
import fr.cvlaminck.remapper.impl.converters.containers.DefaultObjectConvertersContainer;
import fr.cvlaminck.remapper.impl.converters.strategies.CISISCObjectConverterSelectionStrategy;
import fr.cvlaminck.remapper.impl.mappings.DefaultObject2ObjectMappingBuilder;
import org.apache.commons.lang3.reflect.ConstructorUtils;

public class DefaultObject2ObjectMapper
        implements Object2ObjectMapper {

    /**
     * Container of all field converters that can be use to map the data.
     * Developers can add new field converters using this source code :
     * ((DefaultFieldConvertersContainer) getFieldConvertersContainer()).addFieldConverter(...)
     */
    private ObjectConvertersContainer objectConvertersContainer = null;

    /**
     * Strategy used to select the object converter for a mapped field.
     */
    private ObjectConverterSelectionStrategy objectConverterSelectionStrategy = null;

    /**
     * Cache storing all mapping that have been computed to map entities and resources.
     */
    private Object2ObjectMappingCache object2ObjectMappingCache = null;

    /**
     * Builder used to compute mapping between resources and entities.
     */
    private DefaultObject2ObjectMappingBuilder mappingBuilder = null;

    public DefaultObject2ObjectMapper() {
        objectConvertersContainer = new DefaultObjectConvertersContainer(this);
        objectConverterSelectionStrategy = new CISISCObjectConverterSelectionStrategy();
    }

    @Override
    public ObjectConvertersContainer getObjectConvertersContainer() {
        return objectConvertersContainer;
    }

    @Override
    public void setObjectConverterSelectionStrategy(ObjectConverterSelectionStrategy selectionStrategy) {
        this.objectConverterSelectionStrategy = selectionStrategy;
        this.mappingBuilder = null;
    }

    @Override
    public void setObject2ObjectMappingCache(Object2ObjectMappingCache object2ObjectMappingCache) {
        this.object2ObjectMappingCache = object2ObjectMappingCache;
    }

    @Override
    public <In, Out> void prepareMapping(Class<In> srcType, Class<Out> dstType) {
        if (object2ObjectMappingCache != null)
            getMapping(srcType, dstType);
    }

    @Override
    public <In, Out> Out convert(In src, Class<In> srcType, Class<Out> dstType) {
        Object2ObjectMapping mapping = getMapping(srcType, dstType);
        Out dst = instantiateUsingDefaultConstructor(dstType);
        for (Object2ObjectFieldMapping fieldMapping : mapping) {
            fieldMapping.convert(src, dst);
        }
        return dst;
    }

    @Override
    public <In, Out> Out convert(In entity, Class<Out> dstType) {
        return null;
    }

    @Override
    public <In, Out> Out convert(In entity) {
        return null;
    }

    private <Out> Out instantiateUsingDefaultConstructor(Class<Out> resourceOrEntityType) {
        try {
            return ConstructorUtils.invokeConstructor(resourceOrEntityType);
        } catch (Exception e) {
            throw new DstObjectCannotBeInstantiatedException(resourceOrEntityType, e);
        }
    }

    /**
     * Provide a mapping between the two types.
     * If this mapper have no cache or the cache does not contain this mapping, it will build a new mapping
     * using the mapping builder. Otherwise, it will return the one store in cache.
     *
     * @return
     */
    private <In, Out> Object2ObjectMapping getMapping(Class<In> srcType, Class<Out> dstType) {
        //We try to retrieve the mapping from cache
        Object2ObjectMapping object2ObjectMapping = (object2ObjectMappingCache != null) ?
                object2ObjectMappingCache.getMapping(srcType, dstType) : null;
        //If not, we build a new one
        if (object2ObjectMapping == null)
            object2ObjectMapping = getMappingBuilder().buildMapping(srcType, dstType);
        //And store it in cache, if available
        if (object2ObjectMappingCache != null) object2ObjectMappingCache.store(object2ObjectMapping);
        return object2ObjectMapping;
    }

    private DefaultObject2ObjectMappingBuilder getMappingBuilder() {
        if (mappingBuilder == null) {
            mappingBuilder = new DefaultObject2ObjectMappingBuilder(objectConvertersContainer, objectConverterSelectionStrategy);
        }
        return mappingBuilder;
    }
}

