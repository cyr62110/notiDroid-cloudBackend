package fr.cvlaminck.remapper.impl.mappings;

import fr.cvlaminck.remapper.api.fieldcloners.FieldCloner;
import fr.cvlaminck.remapper.api.mappings.ResourceEntityFieldMapping;

import java.lang.reflect.Field;

/**
 * Field that have the same name in the resource and the entity are mapped together
 * if they are of a supported type and have the same type.
 */
class DefaultResourceEntityFieldMapping
    implements ResourceEntityFieldMapping {

    /**
     * Name of the field in both class
     */
    private String fieldName;

    /**
     * Representation of the mapped field in the resource.
     * Obtained through the reflexion API.
     */
    private Field fieldInResourceType;

    /**
     * Representation of the mapped field in the entity.
     * Obtained through the reflexion API.
     */
    private Field fieldInEntityType;

    /**
     * FieldCloner that will be use to copy this field
     * from an instance of class A to an instance of class B.
     */
    private FieldCloner fieldCloner;

    DefaultResourceEntityFieldMapping(String fieldName, Field fieldInResourceType, Field fieldInEntityType, FieldCloner fieldCloner) {
        this.fieldName = fieldName;
        this.fieldInResourceType = fieldInResourceType;
        this.fieldInEntityType = fieldInEntityType;
        this.fieldCloner = fieldCloner;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Field getFieldInResourceType() {
        return fieldInResourceType;
    }

    public Field getFieldInEntityType() {
        return fieldInEntityType;
    }

    public FieldCloner getFieldCloner() {
        return fieldCloner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultResourceEntityFieldMapping that = (DefaultResourceEntityFieldMapping) o;

        if (!fieldName.equals(that.fieldName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return fieldName.hashCode();
    }
}