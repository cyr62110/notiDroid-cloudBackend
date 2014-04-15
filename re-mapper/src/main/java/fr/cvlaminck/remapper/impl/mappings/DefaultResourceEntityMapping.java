package fr.cvlaminck.remapper.impl.mappings;

import fr.cvlaminck.remapper.api.ResourceEntityMapper;
import fr.cvlaminck.remapper.api.mappings.ResourceEntityMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapping between a resource and an entity.
 */
public class DefaultResourceEntityMapping
    implements ResourceEntityMapping {

    private Class<?> resourceType;

    private Class<?> entityType;

    /**
     * Map containing mapped fields between the resource and the entity.
     * The map use the field name as the key and the mapping as the value.
     */
    private Map<String, DefaultResourceEntityFieldMapping> fields;

    public DefaultResourceEntityMapping(Class<?> resourceType, Class<?> entityType) {
        this.resourceType = resourceType;
        this.entityType = entityType;
        this.fields = new HashMap<String, DefaultResourceEntityFieldMapping>();
    }

    public void addFieldMapping(DefaultResourceEntityFieldMapping fieldMapping) {
        if(fields.containsKey(fieldMapping.getFieldName()))
            throw new IllegalArgumentException("Field '" + fieldMapping.getFieldName() + "' is already registered in this mapping. Maybe your MappingBuilder is not functioning correctly.");

        fields.put(fieldMapping.getFieldName(), fieldMapping);
    }

    public

}
