package fr.cvlaminck.remapper.impl.converters.object;

import fr.cvlaminck.remapper.api.Object2ObjectMapper;
import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.impl.converters.AbstractObjectConverter;

/**
 * Field converter based on an Object2ObjectMapper. It converts an Object into another Object
 */
public class Object2ObjectMapperBasedObjectConverter
        extends AbstractObjectConverter
        implements ObjectConverter {

    private Object2ObjectMapper object2ObjectMapper;

    public Object2ObjectMapperBasedObjectConverter(Object2ObjectMapper object2ObjectMapper) {
        this.object2ObjectMapper = object2ObjectMapper;
    }

    @Override
    public Class<?> getSourceType() {
        return Object.class;
    }

    @Override
    public boolean supports(Class<?> srcType, Class<?> dstType) {
        return true;
    }

    @Override
    public Object convert(Object src, Class<?> srcType, Class<?> dstType) {
        return object2ObjectMapper.convert(src);
    }

}
