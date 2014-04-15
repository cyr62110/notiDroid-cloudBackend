package fr.cvlaminck.remapper.api.fieldcloners;

import java.lang.reflect.Field;

/**
 * A fieldcloner is a class that support the copy of the value of a field from an instance of a class A to a field in an
 * instance of a class B.
 *
 * Field in A and B classes must be of the type that is supported by the FieldCloner.
 */
public interface FieldCloner {

    public Class<?> getSupportedType();

    public void copy(Object src, Field srcField, Object dst, Field dstField);

}
