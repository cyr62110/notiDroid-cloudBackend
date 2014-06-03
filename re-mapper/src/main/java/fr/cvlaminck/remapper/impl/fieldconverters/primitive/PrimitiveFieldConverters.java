package fr.cvlaminck.remapper.impl.fieldconverters.primitive;

import fr.cvlaminck.remapper.api.fieldconverters.FieldConverter;

import java.util.ArrayList;

/**
 * Utility class helping to build all converters for java primitive types.
 */
public class PrimitiveFieldConverters {

    private static ArrayList<FieldConverter> primitiveFieldConverters = null;

    public static ArrayList<FieldConverter> get() {
        if (primitiveFieldConverters == null) {
            primitiveFieldConverters = new ArrayList<FieldConverter>(PrimitiveFieldConverter.JAVA_PRIMITIVE_TYPES.length);
            for (Class<?> primitiveType : PrimitiveFieldConverter.JAVA_PRIMITIVE_TYPES)
                primitiveFieldConverters.add(PrimitiveFieldConverter.buildFor(primitiveType));
        }
        return primitiveFieldConverters;
    }

}
