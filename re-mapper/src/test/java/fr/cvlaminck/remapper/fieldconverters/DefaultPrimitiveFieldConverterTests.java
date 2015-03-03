package fr.cvlaminck.remapper.fieldconverters;

import fr.cvlaminck.remapper.impl.converters.primitive.PrimitiveObjectConverter;
import fr.cvlaminck.remapper.objects.fieldconverters.ObjectFullOfPrimitiveFields;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class DefaultPrimitiveFieldConverterTests {

    private static final int INTEGER_TEST_VALUE = 42;

    @Test
    public void testShouldConvertPrimitiveInteger() throws Exception {
        PrimitiveObjectConverter converter = PrimitiveObjectConverter.buildFor(int.class);
        int i = 42;

        assertEquals(i, converter.convert(i, int.class, int.class));
    }
}
