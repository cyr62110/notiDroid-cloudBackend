package fr.cvlaminck.remapper.impl.fieldconverters.object;

import fr.cvlaminck.remapper.impl.fieldconverters.BasicFieldConverter;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;

/**
 * Field converter converting a boxed primitive in
 * another boxed primitive.
 */
public class BoxedPrimitiveFieldConverter
    extends BasicFieldConverter {

    public static final Class<?>[] JAVA_BOXED_PRIMITIVE_TYPES = new Class<?>[]{
            Character.class,
            Short.class,
            Integer.class,
            Long.class,
            Float.class,
            Double.class
    };

    /**
     * Association table between the boxed type and the primitive type.
     * Used internally to find the valueOf method of the boxed type.
     */
    private static final Class<?>[][] BOXED_PRIMITIVE_ASSOCIATION_TABLE = new Class<?>[][]{
            {Character.class, char.class},
            {Short.class, short.class},
            {Integer.class, int.class},
            {Long.class, long.class},
            {Float.class, float.class},
            {Double.class, double.class}
    };

    private Class<?> primitiveType = null;

    private Method valueOfMethod = null;

    private BoxedPrimitiveFieldConverter(Class<?> convertedType) {
        super(convertedType);
        try {
            findValueOfMethod(convertedType);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Cannot find the valueOf method in " + convertedType.getSimpleName() + ". With this method the provided cannot be converted using this converter.", e);
        }
    }

    /**
     * This method find the valueOf method associated with the boxed type
     * so we can deep copy the value.
     *
     * @param convertedType Boxed type that we want to convert using this converter.
     */
    private void findValueOfMethod(Class<?> convertedType) throws NoSuchMethodException {
        for(Class<?>[] boxedType : BOXED_PRIMITIVE_ASSOCIATION_TABLE) {
            if(boxedType[0] == convertedType) {
                //If we have found the boxed types, we search for the valueOf method
                primitiveType = boxedType[1];
                valueOfMethod = boxedType[0].getDeclaredMethod("valueOf", new Class[]{boxedType[1]});
                return;
            }
        }
        //If we have not found our type, may be it is not a boxed type
        throw new IllegalArgumentException(convertedType.getSimpleName() + " is not a boxed primitive type that can be converted using this class");
    }

    @Override
    protected Object deepCopy(Object srcFieldValue) {
        try {
            return valueOfMethod.invoke(getInputType(), srcFieldValue);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot deep copy value '" + srcFieldValue.toString() + "'", e);
        }
    }

    /**
     * Build a field converter for the given primitive type.
     *
     * @param convertedPrimitiveType Primitive type that will be supported by the new FieldConverter.
     * @return a field converter that can copy the primitive type.
     */
    public static BoxedPrimitiveFieldConverter buildFor(Class<?> convertedPrimitiveType) {
        if (!ArrayUtils.contains(JAVA_BOXED_PRIMITIVE_TYPES, convertedPrimitiveType))
            throw new IllegalArgumentException(convertedPrimitiveType.getSimpleName() + " is not a boxed primitive types. Only boxed primitive types are supported by this converter.");
        return new BoxedPrimitiveFieldConverter(convertedPrimitiveType);
    }

}
