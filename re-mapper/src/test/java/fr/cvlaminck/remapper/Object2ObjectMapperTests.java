package fr.cvlaminck.remapper;

import fr.cvlaminck.remapper.api.Object2ObjectMapper;
import fr.cvlaminck.remapper.impl.DefaultObject2ObjectMapper;
import fr.cvlaminck.remapper.objects.mapper.SimpleObject;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 *
 */
public class Object2ObjectMapperTests {

    /**
     * Return a new instance of the tested ResourceEntityMapper.
     * If you want to run those tests on your mapper implementation, just change
     * the code of this function to return an instance of your implementation instead
     * of the default one.
     *
     * @return
     */
    public Object2ObjectMapper instantiateMapper() {
        return new DefaultObject2ObjectMapper();
    }

    @Test
    public void testConvertWithOutOfTheBoxSupportedTypes() {
        Object2ObjectMapper mapper = instantiateMapper();
        SimpleObject o1 = SimpleObject.build();

        SimpleObject o2 = mapper.convert(o1, SimpleObject.class, SimpleObject.class);

        assertNotSame(o1, o2);
        SimpleObject.assertEquals(o1, o2);
    }
}
