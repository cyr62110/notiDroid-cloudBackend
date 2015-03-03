package fr.cvlaminck.remapper.impl.converters;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;

/**
 * A BasicObjectConverter only converts an object into an object of the same type. ex : Float -> Float, float -> float.
 * All ObjectConverters that only do deep copy must inherits from this class.
 */
public abstract class BasicObjectConverter
        implements ObjectConverter {

    private Class<?> convertedType = null;

    public BasicObjectConverter(Class<?> convertedType) {
        this.convertedType = convertedType;
    }

    @Override
    public Class<?> getSourceType() {
        return convertedType;
    }

    @Override
    public boolean supports(Class<?> srcType, Class<?> dstType) {
        return (srcType == getSourceType()) && (srcType == dstType);
    }

}
