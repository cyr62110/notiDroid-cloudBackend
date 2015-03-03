package fr.cvlaminck.remapper.impl.converters.primitive;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Utility class helping to build all converters for java primitive types.
 */
public class PrimitiveObjectConverters {

    private static ArrayList<ObjectConverter> primitiveObjectConverters = null;

    public static Collection<ObjectConverter> get() {
        if (primitiveObjectConverters == null) {
            primitiveObjectConverters = new ArrayList<ObjectConverter>(PrimitiveObjectConverter.JAVA_PRIMITIVE_TYPES.length);
            for (Class<?> primitiveType : PrimitiveObjectConverter.JAVA_PRIMITIVE_TYPES)
                primitiveObjectConverters.add(PrimitiveObjectConverter.buildFor(primitiveType));
        }
        return primitiveObjectConverters;
    }

}
