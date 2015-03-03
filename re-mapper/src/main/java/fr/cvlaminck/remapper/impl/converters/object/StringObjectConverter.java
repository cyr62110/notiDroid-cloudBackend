package fr.cvlaminck.remapper.impl.converters.object;

import fr.cvlaminck.remapper.impl.converters.BasicObjectConverter;

public class StringObjectConverter
        extends BasicObjectConverter {

    public StringObjectConverter() {
        super(String.class);
    }

    @Override
    public Object convert(Object src, Class<?> srcType, Class<?> dstType) {
        return new String((String) src);
    }
}
