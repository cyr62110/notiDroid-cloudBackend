package fr.cvlaminck.remapper.impl.converters;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;

import java.lang.reflect.Field;

/**
 * If you write your own ObjectConverter and you do not need access to Field then you
 * should extends from this AbstractObjectConverter that will handle all methods with
 * Field parameters for you.
 */
public abstract class AbstractObjectConverter
        implements ObjectConverter {

    @Override
    public boolean supports(Field srcField, Field dstField) {
        return supports(srcField.getType(), dstField.getType());
    }

    @Override
    public Object convert(Object src, Field srcField, Field dstField) {
        return convert(src, srcField.getType(), dstField.getType());
    }

}
