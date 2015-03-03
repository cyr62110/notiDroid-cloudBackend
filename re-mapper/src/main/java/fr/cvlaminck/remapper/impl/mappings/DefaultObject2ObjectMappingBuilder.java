package fr.cvlaminck.remapper.impl.mappings;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.api.converters.strategies.ObjectConverterSelectionStrategy;
import fr.cvlaminck.remapper.api.converters.containers.ObjectConvertersContainer;
import fr.cvlaminck.remapper.api.mappings.Object2ObjectFieldMapping;
import fr.cvlaminck.remapper.api.mappings.Object2ObjectMapping;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Build the mapping between a resource and an entity.
 */
public class DefaultObject2ObjectMappingBuilder {

    /**
     * Contains all field conversion that are supported by the current
     * ResourceEntityMapper.
     */
    private ObjectConvertersContainer objectConvertersContainer = null;

    private ObjectConverterSelectionStrategy objectConverterSelectionStrategy = null;

    public DefaultObject2ObjectMappingBuilder(ObjectConvertersContainer container, ObjectConverterSelectionStrategy selectionStrategy) {
        this.objectConvertersContainer = container;
        this.objectConverterSelectionStrategy = selectionStrategy;
    }

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
        Field[] fields = FieldUtils.getAllFields(type);
        for (Field field : fields) {
            fieldsInType.put(field.getName(), field);
        }
        return fieldsInType;
    }

    /**
     * Use the reflexion to build a new mapping between the provided resource and entity
     * types.
     *
     * @return The mapping between the entity type and the resource type
     */
    public Object2ObjectMapping buildMapping(Class<?> sourceType, Class<?> destinationType) {
        DefaultObject2ObjectMapping object2ObjectMapping = new DefaultObject2ObjectMapping(sourceType, destinationType);
        //We need to retrieve all fields that are contained in both types
        Map<String, Field> fieldsInSrcType = getFieldsInType(destinationType);
        Map<String, Field> fieldsInDstType = getFieldsInType(sourceType);
        //If a field is not in both types, it will not be mapped.
        for (Field sourceField : fieldsInSrcType.values()) {
            //We need to find a field in the resource with the same name
            Field destinationField = fieldsInDstType.get(sourceField.getName());
            //If we have found one
            if (destinationField != null) {
                Object2ObjectFieldMapping object2ObjectFieldMapping = buildFieldMapping(sourceField, destinationField);
                if (object2ObjectFieldMapping != null)
                    object2ObjectMapping.addFieldMapping(object2ObjectFieldMapping);
            }
        }
        return object2ObjectMapping;
    }

    /**
     * Create a mapping between the two provided fields or return null if no field mapper in the container
     * supports the conversion.
     *
     * @param sourceField
     * @param destinationField
     * @return a mapping or null
     */
    private Object2ObjectFieldMapping buildFieldMapping(Field sourceField, Field destinationField) {
        if (sourceField == null || destinationField == null)
            return null;
        //We check if the type of the field is supported by the ResourceEntityMapper
        ObjectConverter objectConverter = objectConverterSelectionStrategy.getConverterFrom(objectConvertersContainer,
                sourceField.getType(), destinationField.getType());
        if (objectConverter == null)
            return null;
        return new DefaultObject2ObjectFieldMapping(
                sourceField.getName(),
                sourceField,
                destinationField,
                objectConverter
        );
    }


}
