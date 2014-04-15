package fr.cvlaminck.remapper.api;

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

    public <E, R> R convertToResource(E entity, Class<E> entityType, Class<R> resourceType);

    public <E, R> E convertToEntity(R resource, Class<E> entityType, Class<R> resourceType);

}
