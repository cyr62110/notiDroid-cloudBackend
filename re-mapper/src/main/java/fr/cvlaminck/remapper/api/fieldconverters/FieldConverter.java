package fr.cvlaminck.remapper.api.fieldconverters;

import java.lang.reflect.Field;

/**
 * During the mapping all value stored in the resource or entity must be copied to
 * the other. Sometimes types are not the same between the field in the entity and in
 * the resource, so we need to handle the conversion from one to another. This is what this
 * class is meant for, copying from one to another and converting the value if it is required.
 * One example of conversion is boxing/unboxing of primitive type.
 * <p>
 * Field in A and B classes must be of the type that is supported by the FieldConverter.
 */
public interface FieldConverter {

    /**
     * Return the type of fields that can be converted by this converter.
     */
    public Class<?> getInputType();

    /**
     * Return the type that will be output as result of the conversion
     */
    public Class<?> getOutputType();

    /**
     * Does this converter supports the conversion of srcType to dstType.
     *
     * @param srcType Type of the source field
     * @param dstType Type of the destination field
     * @return true if this converter can handle the conversion or false if not.
     */
    public boolean supports(Class<?> srcType, Class<?> dstType);

    public void convert(Object src, Field srcField, Object dst, Field dstField);

}
