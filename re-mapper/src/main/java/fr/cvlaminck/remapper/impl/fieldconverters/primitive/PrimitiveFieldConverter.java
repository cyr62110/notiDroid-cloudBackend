package fr.cvlaminck.remapper.impl.fieldconverters.primitive;

import fr.cvlaminck.remapper.impl.fieldconverters.BasicFieldConverter;
import org.apache.commons.lang3.ArrayUtils;

public class PrimitiveFieldConverter
        extends BasicFieldConverter {

    public static final Class<?>[] JAVA_PRIMITIVE_TYPES = new Class<?>[]{
            char.class,
            short.class,
            int.class,
            long.class,
            float.class,
            double.class
    };

    private PrimitiveFieldConverter(Class<?> convertedType) {
        super(convertedType);
    }

    /**
     * Build a field converter for the given primitive type.
     *
     * @param convertedPrimitiveType Primitive type that will be supported by the new FieldConverter.
     * @return a field converter that can copy the primitive type.
     */
    public static PrimitiveFieldConverter buildFor(Class<?> convertedPrimitiveType) {
        if (!ArrayUtils.contains(JAVA_PRIMITIVE_TYPES, convertedPrimitiveType))
            throw new IllegalArgumentException(convertedPrimitiveType.getSimpleName() + " is not a primitive types. Only primitive types are supported by this converter.");
        return new PrimitiveFieldConverter(convertedPrimitiveType);
    }

    @Override
    protected Object deepCopy(Object srcFieldValue) {
        return srcFieldValue;
    }

}
