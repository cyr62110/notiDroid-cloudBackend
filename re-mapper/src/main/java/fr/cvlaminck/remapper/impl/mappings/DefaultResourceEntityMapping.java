package fr.cvlaminck.remapper.impl.mappings;

import fr.cvlaminck.remapper.api.ResourceEntityMapper;
import fr.cvlaminck.remapper.api.mappings.ResourceEntityFieldMapping;
import fr.cvlaminck.remapper.api.mappings.ResourceEntityMapping;

import java.util.HashMap;
import java.util.Iterator;
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
    private Map<String, ResourceEntityFieldMapping> fields;

    public DefaultResourceEntityMapping(Class<?> resourceType, Class<?> entityType) {
        this.resourceType = resourceType;
        this.entityType = entityType;
        this.fields = new HashMap<String, ResourceEntityFieldMapping>();
    }

    /**
     * Register a new field that belong to both classes and that can be mapped.
     *
     * @param fieldMapping Mapping of the new field.
     */
    public void addFieldMapping(DefaultResourceEntityFieldMapping fieldMapping) {
        if(fields.containsKey(fieldMapping.getFieldName()))
            throw new IllegalArgumentException("Field '" + fieldMapping.getFieldName() + "' is already registered in this mapping. Maybe your MappingBuilder is not functioning correctly.");

        fields.put(fieldMapping.getFieldName(), fieldMapping);
    }

    public ResourceEntityFieldMapping getFieldMappingByName(String fieldName) {
        if(!fields.containsKey(fieldName))
            throw new IllegalArgumentException("Field '" + fieldName + "' is not registered in this mapping. Maybe does it not belong to this mapping or is it not mappable");
        return fields.get(fieldName);
    }

    @Override
    public Iterator<ResourceEntityFieldMapping> iterator() {
        return fields.values().iterator();
    }

}
