package fr.cvlaminck.remapper.fieldconverters;

import fr.cvlaminck.remapper.impl.fieldconverters.primitive.PrimitiveFieldConverter;
import fr.cvlaminck.remapper.objects.fieldconverters.ObjectFullOfPrimitiveFields;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class DefaultPrimitiveFieldConverterTests {

    private static final int INTEGER_TEST_VALUE = 42;

    @Test
    public void testShouldConvertPrimitiveInteger() throws Exception {
        PrimitiveFieldConverter converter = PrimitiveFieldConverter.buildFor(int.class);

        ObjectFullOfPrimitiveFields object = new ObjectFullOfPrimitiveFields();
        Field srcField = FieldUtils.getField(ObjectFullOfPrimitiveFields.class, "primitiveIntSrc", true);
        Field dstField = FieldUtils.getField(ObjectFullOfPrimitiveFields.class, "primitiveIntDst", true);
        object.setPrimitiveIntSrc(INTEGER_TEST_VALUE);

        converter.convert(object, srcField, object, dstField);

        assertEquals(object.getPrimitiveIntSrc(), object.getPrimitiveIntDst());
    }
}
