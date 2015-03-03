package fr.cvlaminck.remapper.impl.converters.object;

import fr.cvlaminck.remapper.impl.converters.containers.DefaultObjectConvertersContainer;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import static org.junit.Assert.*;

public class CollectionObjectConverterTest {

    @Test
    public void testGetCollectionType() {
        Collection<String> collection = new ArrayList<String>();

        Type[] genericInterfaces = collection.getClass().getGenericInterfaces();
        for(Type genericInterface : genericInterfaces) {
            if(genericInterface instanceof ParameterizedType) {
                System.out.println(((ParameterizedType) genericInterface).getActualTypeArguments().length);
                if(((ParameterizedType) genericInterface).getActualTypeArguments().length > 0)
                    System.out.println(((ParameterizedType) genericInterface).getActualTypeArguments()[0].getTypeName());
            }
        }
    }

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

        assertFalse(converter.supports(String.class, Collection.class));
        assertFalse(converter.supports(Collection.class, Object.class));
        assertFalse(converter.supports(Collection.class, List.class));
        assertFalse(converter.supports(List.class, Set.class));
    }

    @Test
    public void testConvert() throws Exception {
        CollectionObjectConverter converter = new CollectionObjectConverter(new DefaultObjectConvertersContainer());
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