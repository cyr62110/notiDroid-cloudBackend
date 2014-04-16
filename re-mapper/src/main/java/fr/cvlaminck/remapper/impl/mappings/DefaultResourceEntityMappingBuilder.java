package fr.cvlaminck.remapper.impl.mappings;

import fr.cvlaminck.remapper.api.mappings.ResourceEntityMapping;
import fr.cvlaminck.remapper.api.mappings.ResourceEntityMappingBuilder;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Build the mapping between a resource and an entity.
 */
public class DefaultResourceEntityMappingBuilder
    implements ResourceEntityMappingBuilder {

    /**
     * Build a map containing all fields that are contained by the provided type.
     * Fields can have public, protected, private visibility.
     * This return a map using the field name as key and the field obtained by reflexion
     * as value.
     *
     * @param type Class we want to examine
     * @return Map containing all fields of type
     */
    private Map<String, Field> getFieldsInType(Class<?> type) {
        return null;
    }

    /**
     * Use the reflexion to build a new mapping between the provided resource and entity
     * types.
     *
     * @param entityType Entity type
     * @param resourceType Resource type
     * @return The mapping between the entity type and the resource type
     */
    public ResourceEntityMapping buildMapping(Class<?> entityType, Class<?> resourceType) {
        //We need to retrieve all fields that are contained in both types
        //TODO
        //Since we are doing one to one mapping, we do not care about the map on which we will iterate.
        //If a field is not in both types, it will not be mapped.
        //TODO

        return null;
    }

}
