package fr.cvlaminck.remapper.api;

import fr.cvlaminck.remapper.api.converters.containers.ObjectConvertersContainer;
import fr.cvlaminck.remapper.api.converters.strategies.ObjectConverterSelectionStrategy;
import fr.cvlaminck.remapper.api.mappings.caches.Object2ObjectMappingCache;

/**
 * When you build a REST webservice, you have the resource that you are exposing
 * through this webservice but also your entities that are stored in your database.
 * If you want to separate your entities from your resources and have two truly separated
 * world, you will need to write some code like resource.a = entity.a, etc... in your managers.
 * This code is quite ugly and does not have any kind of value, so you can automate this.
 * <p>
 * This is the purpose of this o2o-mapper api. It will convert your entities in resources and your
 * resources in entities in a one-to-one way. This library does use reflexion and make the conversion using
 * the fields inside your classes, if a field is present in your entity and your resource and does have the same
 * type, it is copied from one to another.
 * <p>
 * Type that are currently supported :
 * - int / Integer
 * - long / Long
 * - float / Float
 * - double / Double
 * - boolean / Boolean
 * - String
 * - Collection / Collection
 */
public interface Object2ObjectMapper {

    public ObjectConvertersContainer getObjectConvertersContainer();

    /**
     * Configure the strategy used to select the object converter to set up in a mapping.
     */
    public void setObjectConverterSelectionStrategy(ObjectConverterSelectionStrategy selectionStrategy);

    /**
     * Set up a cache to the API so all computed mapping can be stored in order to be
     * reused if the same mapping is used latter.
     * Providing a cache will improve the performance.
     */
    public void setObject2ObjectMappingCache(Object2ObjectMappingCache object2ObjectMappingCache);

    /**
     * Compute the mapping between the source and the destination and cache it to optimize performance.
     * If no cache has been provided to the API, this function will do nothing.
     */
    public <In, Out> void prepareMapping(Class<In> srcType, Class<Out> dstType);

    public <In, Out> Out convert(In entity, Class<In> srcType, Class<Out> dstType);

    public <In, Out> Out convert(In entity, Class<Out> dstType);

    public <In, Out> Out convert(In entity);

}
