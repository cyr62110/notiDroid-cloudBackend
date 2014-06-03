package fr.cvlaminck.remapper;

import fr.cvlaminck.remapper.api.ResourceEntityMapper;
import fr.cvlaminck.remapper.impl.DefaultResourceEntityMapper;
import fr.cvlaminck.remapper.objects.mapper.SimpleEntity;
import org.junit.Test;

/**
 *
 */
public class ResourceEntityMapperTests {

    /**
     * Return a new instance of the tested ResourceEntityMapper.
     * If you want to run those tests on your mapper implementation, just change
     * the code of this function to return an instance of your implementation instead
     * of the default one.
     *
     * @return
     */
    public ResourceEntityMapper instantiateMapper() {
        return new DefaultResourceEntityMapper();
    }

    @Test
    public void testMapSimpleEntityToResource() throws Exception {
        ResourceEntityMapper mapper = instantiateMapper();

        SimpleEntity entity = new SimpleEntity();

    }
}
