package fr.cvlaminck.remapper.impl.converters.containers;

import fr.cvlaminck.remapper.api.Object2ObjectMapper;
import fr.cvlaminck.remapper.impl.converters.object.CollectionObjectConverter;
import fr.cvlaminck.remapper.impl.converters.object.Object2ObjectMapperBasedObjectConverter;
import fr.cvlaminck.remapper.impl.converters.object.boxed.BoxedPrimitiveObjectConverter;
import fr.cvlaminck.remapper.impl.converters.primitive.PrimitiveObjectConverter;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DefaultObjectConvertersContainerTest {

    private DefaultObjectConvertersContainer container;

    @Before
    public void setUp() throws Exception {
        Object2ObjectMapper mapper = mock(Object2ObjectMapper.class);
        container = new DefaultObjectConvertersContainer(mapper);
    }

    @Test
    public void testGetConverterForPrimitiveTypes() {
        for(Class<?> primitiveType : PrimitiveObjectConverter.JAVA_PRIMITIVE_TYPES) {
            assertNotNull(container.getConverterFor(primitiveType, primitiveType));
            assertTrue(container.getConverterFor(primitiveType, primitiveType) instanceof PrimitiveObjectConverter);
        }
    }

    @Test
    public void testGetConverterForBoxedPrimitiveTypes() {
        for(Class<?> boxedPrimitiveType : BoxedPrimitiveObjectConverter.JAVA_BOXED_PRIMITIVE_TYPES) {
            assertNotNull(container.getConverterFor(boxedPrimitiveType, boxedPrimitiveType));
            assertTrue(container.getConverterFor(boxedPrimitiveType, boxedPrimitiveType) instanceof BoxedPrimitiveObjectConverter);
        }
    }

    @Test
    public void testGetConverterFor() {
        assertNotNull(container.getConverterFor(Collection.class, Collection.class));
        assertTrue(container.getConverterFor(Collection.class, Collection.class) instanceof CollectionObjectConverter);

        assertNotNull(container.getConverterFor(Object.class, Object.class));
        assertTrue(container.getConverterFor(Object.class, Object.class) instanceof Object2ObjectMapperBasedObjectConverter);
    }

}