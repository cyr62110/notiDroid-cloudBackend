package fr.cvlaminck.remapper.fieldconverters;

import fr.cvlaminck.remapper.impl.fieldconverters.BasicFieldConverter;
import fr.cvlaminck.remapper.impl.fieldconverters.object.StringFieldConverter;
import fr.cvlaminck.remapper.objects.ObjectFullOfObjectFields;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class DefaultObjectFieldConverterTests {

    private final static String STRING_TEST_VALUE = "hello";

    @Test
    public void testShouldOutputNullIfSourceIsNull() throws Exception {
        BasicFieldConverter converter = new StringFieldConverter();

        ObjectFullOfObjectFields object = new ObjectFullOfObjectFields();
        Field src = FieldUtils.getField(ObjectFullOfObjectFields.class, "stringSrc", true);
        Field dst = FieldUtils.getField(ObjectFullOfObjectFields.class, "stringDst", true);
        object.setStringSrc(null);
        object.setStringDst(STRING_TEST_VALUE);

        converter.convert(object, src, object, dst);

        assertNull(object.getStringDst());
    }

    @Test
    public void testShouldConvertString() throws Exception {
        StringFieldConverter converter = new StringFieldConverter();

        ObjectFullOfObjectFields object = new ObjectFullOfObjectFields();
        Field src = FieldUtils.getField(ObjectFullOfObjectFields.class, "stringSrc", true);
        Field dst = FieldUtils.getField(ObjectFullOfObjectFields.class, "stringDst", true);
        object.setStringSrc(STRING_TEST_VALUE);

        converter.convert(object, src, object, dst);

        assertEquals(object.getStringSrc(), object.getStringDst());
        assertNotSame(object.getStringSrc(), object.getStringDst());
    }
}
