package fr.cvlaminck.remapper.api;

import fr.cvlaminck.remapper.api.cache.ResourceEntityMappingCache;
import fr.cvlaminck.remapper.api.fieldconverters.FieldConvertersContainer;

/**
 * When you build a REST webservice, you have the resource that you are exposing
 * through this webservice but also your entities that are stored in your database.
 * If you want to separate your entities from your resources and have two truly separated
 * world, you will need to write some code like resource.a = entity.a, etc... in your managers.
 * This code is quite ugly and does not have any kind of value, so you can automate this.
 *
 * This is the purpose of this re-mapper api. It will convert your entities in resources and your
 * resources in entities in a one-to-one way. This library does use reflexion and make the conversion using
 * the fields inside your classes, if a field is present in your entity and your resource and does have the same
 * type, it is copied from one to another.
 *
 * Type that are currently supported :
 * - int / Integer
 * - long / Long
 * - float / Float
 * - double / Double
 * - boolean / Boolean
 * - String
 *
 */
public interface ResourceEntityMapper {

    /**
     * Return a object containing all FieldConverters that can be use to map a resource and an
     * entity. All supported conversions have a FieldConverter in this object.
     *
     * @return A FieldConvertersContainer
     */
    public FieldConvertersContainer getFieldConvertersContainer();

    /**
     * Provide a cache to the API so all computed mapping can be stored in order to be
     * reused if the same mapping is used latter. Providing a cache will help the performance of the
     * API.
     *
     * @param resourceEntityMappingCache A cache where the API can store computed ResourceEntityMapping
     */
    public void setResourceEntityMappingCache(ResourceEntityMappingCache resourceEntityMappingCache);

    /**
     * Compute the mapping between the resource and the entity and store it in the cache.
     * If no cache has been provided to the API, this function will do nothing.
     *
     * @param entity
     * @param resource
     */
    public <E, R> void prepareMapping(Class<E> entity, Class<R> resource);

    public <E, R> R convertToResource(E entity, Class<E> entityType, Class<R> resourceType);

    public <E, R> E convertToEntity(R resource, Class<E> entityType, Class<R> resourceType);

}
