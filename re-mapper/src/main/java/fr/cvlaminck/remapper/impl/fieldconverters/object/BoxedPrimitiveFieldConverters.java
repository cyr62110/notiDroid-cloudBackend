package fr.cvlaminck.remapper.impl.fieldconverters.object;

import fr.cvlaminck.remapper.api.fieldconverters.FieldConverter;
import fr.cvlaminck.remapper.impl.fieldconverters.primitive.PrimitiveFieldConverter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Utility class helping to build all converters for java boxed primitive types.
 */
public class BoxedPrimitiveFieldConverters {

    private static ArrayList<FieldConverter> boxedPrimitiveFieldConverters = null;

    public static Collection<FieldConverter> get() {
        if (boxedPrimitiveFieldConverters == null) {
            boxedPrimitiveFieldConverters = new ArrayList<FieldConverter>(BoxedPrimitiveFieldConverter.JAVA_BOXED_PRIMITIVE_TYPES.length);
            for (Class<?> boxedType : BoxedPrimitiveFieldConverter.JAVA_BOXED_PRIMITIVE_TYPES)
                boxedPrimitiveFieldConverters.add(BoxedPrimitiveFieldConverter.buildFor(boxedType));
        }
        return boxedPrimitiveFieldConverters;
    }

}
