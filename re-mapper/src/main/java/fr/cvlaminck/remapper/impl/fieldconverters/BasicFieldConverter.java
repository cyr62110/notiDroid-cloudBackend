package fr.cvlaminck.remapper.impl.fieldconverters;

import fr.cvlaminck.remapper.api.exceptions.runtime.FieldConversionFailedException;
import fr.cvlaminck.remapper.api.fieldconverters.FieldConverter;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

/**
 * A basic FieldConverter is a FieldConverter that accepts the same type as source and as output.
 * Also there is no conversion between the source and the output, this converter only do a deep
 * copy of the source value before applying it to the destination.
 */
public abstract class BasicFieldConverter
        implements FieldConverter {

    private Class<?> convertedType = null;

    public BasicFieldConverter(Class<?> convertedType) {
        this.convertedType = convertedType;
    }

    @Override
    public Class<?> getInputType() {
        return convertedType;
    }

    @Override
    public Class<?> getOutputType() {
        return convertedType;
    }

    @Override
    public boolean supports(Class<?> srcType, Class<?> dstType) {
        return (srcType == dstType);
    }

    @Override
    public void convert(Object src, Field srcField, Object dst, Field dstField) {
        try {
            //We read the value of the field from the source even if the field is private
            final Object srcFieldValue = FieldUtils.readField(srcField, src, true);
            //If the source is null, the output will be null too. So we do not require a deepCopy of null.
            final Object dstFieldValue = (srcFieldValue != null) ? deepCopy(srcFieldValue) : null;
            //And we write the deepCopy of this value to the destination object
            FieldUtils.writeField(dstField, dst, dstFieldValue, true);
        } catch (IllegalAccessException e) {
            throw new FieldConversionFailedException(this, srcField, dstField, e);
        }
    }

    /**
     * Make a deep copy of the provided object.
     *
     * @param srcFieldValue Object that we want to copy
     * @return A deep copy of the provided value
     */
    protected abstract Object deepCopy(Object srcFieldValue);

}
