package fr.cvlaminck.remapper.api.converters;

import fr.cvlaminck.remapper.api.exceptions.ObjectConversionFailedException;

import java.lang.reflect.Field;

/**
 * During the mapping all value stored in the source must be copied to the destination.
 * Sometimes types are not the same between the field in the source object and the destination object,
 * so we need to handle the conversion from one type to another. This is what this
 * class is meant for, converting from one to another and converting the value if it is required.
 * <p>
 * One example of conversion is boxing/unboxing of primitive type.
 */
public interface ObjectConverter {

    /**
     * Return the type of fields that can be converted by this converter.
     */
    public Class<?> getSourceType();

    /**
     * Does this converter supports the conversion of srcType to dstType.
     *
     * @param srcType Type of the source field
     * @param dstType Type of the destination field
     * @return true if this converter can handle the conversion or false if not.
     */
    public boolean supports(Class<?> srcType, Class<?> dstType);

    public Object convert(Object src, Class<?> srcType, Class<?> dstType) throws ObjectConversionFailedException;

    public Object convert(Object src, Field srcField, Field dstField) throws ObjectConversionFailedException;

    ;

}
