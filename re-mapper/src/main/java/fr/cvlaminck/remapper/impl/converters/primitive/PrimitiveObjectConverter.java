package fr.cvlaminck.remapper.impl.converters.primitive;

import fr.cvlaminck.remapper.impl.converters.BasicObjectConverter;
import org.apache.commons.lang3.ArrayUtils;

public class PrimitiveObjectConverter
        extends BasicObjectConverter {

    public static final Class<?>[] JAVA_PRIMITIVE_TYPES = new Class<?>[]{
            byte.class,
            char.class,
            short.class,
            int.class,
            long.class,
            float.class,
            double.class
    };

    private PrimitiveObjectConverter(Class<?> convertedType) {
        super(convertedType);
    }

    /**
     * Build a field converter for the given primitive type.
     *
     * @param convertedPrimitiveType Primitive type that will be supported by the new FieldConverter.
     * @return a field converter that can copy the primitive type.
     */
    public static PrimitiveObjectConverter buildFor(Class<?> convertedPrimitiveType) {
        if (!ArrayUtils.contains(JAVA_PRIMITIVE_TYPES, convertedPrimitiveType))
            throw new IllegalArgumentException(convertedPrimitiveType.getSimpleName() + " is not a primitive types. Only primitive types are supported by this converter.");
        return new PrimitiveObjectConverter(convertedPrimitiveType);
    }

    @Override
    public Object convert(Object src, Class<?> srcType, Class<?> dstType) {
        return src;
    }

}
