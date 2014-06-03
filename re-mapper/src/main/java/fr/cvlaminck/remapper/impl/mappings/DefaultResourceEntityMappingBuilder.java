package fr.cvlaminck.remapper.impl.mappings;

import fr.cvlaminck.remapper.api.fieldconverters.FieldConverter;
import fr.cvlaminck.remapper.api.fieldconverters.FieldConvertersContainer;
import fr.cvlaminck.remapper.api.mappings.ResourceEntityFieldMapping;
import fr.cvlaminck.remapper.api.mappings.ResourceEntityMapping;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Build the mapping between a resource and an entity.
 */
public class DefaultResourceEntityMappingBuilder {

    /**
     * Contains all field conversion that are supported by the current
     * ResourceEntityMapper.
     */
    private FieldConvertersContainer fieldConvertersContainer = null;

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
        Map<String, Field> fieldsInType = new HashMap<String, Field>();
        //We retrieve all fields declared in the type but also in its super types.
        Field[] fields = type.getFields();
        for (Field field : fields) {
            fieldsInType.put(field.getName(), field);
        }
        return fieldsInType;
    }

    /**
     * Use the reflexion to build a new mapping between the provided resource and entity
     * types.
     *
     * @param resourceType Resource type
     * @param entityType   Entity type
     * @return The mapping between the entity type and the resource type
     */
    public ResourceEntityMapping buildMapping(Class<?> resourceType, Class<?> entityType) {
        DefaultResourceEntityMapping resourceEntityMapping = new DefaultResourceEntityMapping(resourceType, entityType);
        //We need to retrieve all fields that are contained in both types
        Map<String, Field> fieldsInEntityType = getFieldsInType(entityType);
        Map<String, Field> fieldsInResourceType = getFieldsInType(resourceType);
        //Since we are doing one to one mapping, we do not care about the map on which we will iterate.
        //If a field is not in both types, it will not be mapped.
        for (Field entityField : fieldsInEntityType.values()) {
            //We need to find a field in the resource with the same name
            Field resourceField = fieldsInResourceType.get(entityField.getName());
            //If we have found one
            if (resourceField != null) {
                ResourceEntityFieldMapping resourceEntityFieldMapping = buildFieldMapping(entityField, resourceField);
                if (resourceEntityFieldMapping != null)
                    resourceEntityMapping.addFieldMapping(resourceEntityFieldMapping);
            }
        }

        return null;
    }

    /**
     * Create a mapping between the two provided fields or return null if no field mapper in the container
     * supports the conversion.
     *
     * @param entityField
     * @param resourceField
     * @return a mapping or null
     */
    private ResourceEntityFieldMapping buildFieldMapping(Field entityField, Field resourceField) {
        if (entityField == null || resourceField == null)
            return null;
        //We check if the type of the field is supported by the ResourceEntityMapper
        FieldConverter fieldConverter = fieldConvertersContainer.getConverterFor(entityField.getType(), resourceField.getType());
        if (fieldConverter == null)
            return null;
        return new DefaultResourceEntityFieldMapping(
                entityField.getName(),
                entityField,
                resourceField,
                fieldConverter
        );
    }


}
