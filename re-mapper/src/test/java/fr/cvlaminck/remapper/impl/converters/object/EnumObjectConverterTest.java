package fr.cvlaminck.remapper.impl.converters.object;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnumObjectConverterTest {

    @Test
    public void testSupports() {
        EnumObjectConverter converter = new EnumObjectConverter();

        assertTrue(converter.supports(SimpleEnum.class, SimpleEnum.class));
    }

    @Test
    public void testConvert() {
        EnumObjectConverter converter = new EnumObjectConverter();

        assertEquals(SimpleEnum.A, converter.convert(SimpleEnum.A, SimpleEnum.class, SimpleEnum.class));
    }

    public enum SimpleEnum {
        A,
        B,
        C,
        D
    }
}