package fr.cvlaminck.remapper.impl.mappings;

import fr.cvlaminck.remapper.api.mappings.Object2ObjectFieldMapping;
import fr.cvlaminck.remapper.api.mappings.Object2ObjectMapping;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Mapping between a resource and an entity.
 */
public class DefaultObject2ObjectMapping
        implements Object2ObjectMapping {

    private Class<?> sourceType;

    private Class<?> destinationType;

    /**
     * Map containing mapped fields between the resource and the entity.
     * The map use the field name as the key and the mapping as the value.
     */
    private Map<String, Object2ObjectFieldMapping> fields;

    public DefaultObject2ObjectMapping(Class<?> sourceType, Class<?> destinationType) {
        this.sourceType = sourceType;
        this.destinationType = destinationType;
        this.fields = new HashMap<String, Object2ObjectFieldMapping>();
    }

    /**
     * Register a new field that belong to both classes and that can be mapped.
     *
     * @param fieldMapping Mapping of the new field.
     */
    public void addFieldMapping(Object2ObjectFieldMapping fieldMapping) {
        if (fields.containsKey(fieldMapping.getFieldName()))
            throw new IllegalArgumentException("Field '" + fieldMapping.getFieldName() + "' is already registered in this mapping. Maybe your MappingBuilder is not functioning correctly.");

        fields.put(fieldMapping.getFieldName(), fieldMapping);
    }

    public Object2ObjectFieldMapping getFieldMappingByName(String fieldName) {
        if (!fields.containsKey(fieldName))
            throw new IllegalArgumentException("Field '" + fieldName + "' is not registered in this mapping. Maybe does it not belong to this mapping or is it not mappable");
        return fields.get(fieldName);
    }

    @Override
    public Iterator<Object2ObjectFieldMapping> iterator() {
        return fields.values().iterator();
    }

    @Override
    public Class<?> getSourceType() {
        return sourceType;
    }

    @Override
    public Class<?> getDestinationType() {
        return destinationType;
    }
}
