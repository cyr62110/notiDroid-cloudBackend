package fr.cvlaminck.remapper.impl.converters.object.boxed;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Utility class helping to build all converters for java boxed primitive types.
 */
public class BoxedPrimitiveObjectConverters {

    private static ArrayList<ObjectConverter> boxedPrimitiveObjectConverters = null;

    public static Collection<ObjectConverter> get() {
        if (boxedPrimitiveObjectConverters == null) {
            boxedPrimitiveObjectConverters = new ArrayList<ObjectConverter>(BoxedPrimitiveObjectConverter.JAVA_BOXED_PRIMITIVE_TYPES.length);
            for (Class<?> boxedType : BoxedPrimitiveObjectConverter.JAVA_BOXED_PRIMITIVE_TYPES)
                boxedPrimitiveObjectConverters.add(BoxedPrimitiveObjectConverter.buildFor(boxedType));
        }
        return boxedPrimitiveObjectConverters;
    }

}
