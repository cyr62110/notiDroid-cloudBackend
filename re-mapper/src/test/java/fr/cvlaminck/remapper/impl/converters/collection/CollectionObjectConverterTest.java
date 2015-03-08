package fr.cvlaminck.remapper.impl.converters.collection;

import fr.cvlaminck.remapper.impl.converters.collection.CollectionObjectConverter;
import fr.cvlaminck.remapper.impl.converters.collection.generic.FirstElementContentTypeDetectionStrategy;
import fr.cvlaminck.remapper.impl.converters.containers.DefaultObjectConvertersContainer;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import static org.junit.Assert.*;

public class CollectionObjectConverterTest {

    @Test
    public void testGetSourceType() throws Exception {
        CollectionObjectConverter converter = new CollectionObjectConverter(null);

        assertEquals(Collection.class, converter.getSourceType());
    }

    @Test
    public void testSupports() throws Exception {
        CollectionObjectConverter converter = new CollectionObjectConverter(null);

        assertTrue(converter.supports(Collection.class, Collection.class));
        assertTrue(converter.supports(List.class, Collection.class));
        assertTrue(converter.supports(ArrayList.class, List.class));
        assertTrue(converter.supports(ArrayList.class, Collection.class));

        assertFalse(converter.supports(String.class, Collection.class));
        assertFalse(converter.supports(Collection.class, Object.class));
        assertFalse(converter.supports(Collection.class, List.class));
        assertFalse(converter.supports(List.class, Set.class));
    }

    @Test
    public void testConvert() throws Exception {
        CollectionObjectConverter converter = new CollectionObjectConverter(new DefaultObjectConvertersContainer(), new FirstElementContentTypeDetectionStrategy());
        List<String> numbers = new ArrayList<String>();
        for(int i = 0; i < 10; i++)
            numbers.add(Integer.toString(i));

        List<String> convertedNumbers = (List<String>) converter.convert(numbers, List.class, List.class);

        assertEquals(numbers.size(), convertedNumbers.size());
        Iterator<String> iNumbers = numbers.iterator();
        Iterator<String> iConvertedNumbers = convertedNumbers.iterator();
        while(iNumbers.hasNext() && iConvertedNumbers.hasNext()) {
            String sN = iNumbers.next();
            String sCN = iConvertedNumbers.next();
            assertEquals(sN, sCN);
            assertNotSame(sN, sCN);
        }
    }
}