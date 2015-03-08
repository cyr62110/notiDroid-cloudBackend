package fr.cvlaminck.remapper.fieldconverters;

import fr.cvlaminck.remapper.impl.converters.object.StringObjectConverter;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultObjectFieldConverterTests {

    private final static String STRING_TEST_VALUE = "hello";

    @Test
    public void testShouldConvertString() throws Exception {
        StringObjectConverter converter = new StringObjectConverter();

        String source = "Hello World";
        String destination = (String)converter.convert(source, String.class, String.class);

        assertEquals(source, destination);
        assertNotSame(source, destination);
    }
}
