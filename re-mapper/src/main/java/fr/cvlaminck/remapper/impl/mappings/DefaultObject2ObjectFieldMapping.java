package fr.cvlaminck.remapper.impl.mappings;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.api.exceptions.ObjectConversionFailedException;
import fr.cvlaminck.remapper.api.exceptions.runtime.FieldConversionFailedException;
import fr.cvlaminck.remapper.api.mappings.Object2ObjectFieldMapping;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

/**
 * Field that have the same name in the resource and the entity are mapped together
 * if they are of a supported type and have the same type.
 */
class DefaultObject2ObjectFieldMapping
        implements Object2ObjectFieldMapping {

    /**
     * Name or alias of fields in both class used to
     * make the match.
     */
    private String fieldName;

    private Field fieldInSrcType;

    private Field fieldInDstType;

    /**
     * FieldConverter that will be use to copy this field
     * from an instance of class A to an instance of class B.
     */
    private ObjectConverter objectConverter;

    DefaultObject2ObjectFieldMapping(String fieldName, Field fieldInSrcType, Field fieldInDstType, ObjectConverter objectConverter) {
        this.fieldName = fieldName;
        this.fieldInSrcType = fieldInSrcType;
        this.fieldInDstType = fieldInDstType;
        this.objectConverter = objectConverter;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public void convert(Object src, Object dst) {
        try {
            Object srcFieldValue = FieldUtils.readField(fieldInSrcType, src, true);
            Object dstFieldValue = null;
            if (srcFieldValue != null) {
                dstFieldValue = objectConverter.convert(srcFieldValue, fieldInSrcType, fieldInDstType);
            }
            FieldUtils.writeField(fieldInDstType, dst, dstFieldValue, true);
        } catch (IllegalAccessException e) {
            throw new FieldConversionFailedException(fieldInSrcType, fieldInDstType, e);
        } catch (ObjectConversionFailedException e) {
            e.printStackTrace();
            //FIXME : Create an object to accumulate all exceptions retrieved from the converter so the dev can know if the conversion is complete or not.
            //For now, we ignore
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultObject2ObjectFieldMapping that = (DefaultObject2ObjectFieldMapping) o;

        if (!fieldName.equals(that.fieldName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return fieldName.hashCode();
    }
}
