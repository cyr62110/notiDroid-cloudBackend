package fr.cvlaminck.remapper.impl.converters.object;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.api.exceptions.ObjectConversionFailedException;
import fr.cvlaminck.remapper.impl.converters.AbstractObjectConverter;

import java.lang.reflect.Method;

public class EnumObjectConverter
        extends AbstractObjectConverter
        implements ObjectConverter {

    @Override
    public Class<?> getSourceType() {
        return Enum.class;
    }

    @Override
    public boolean supports(Class<?> srcType, Class<?> dstType) {
        return srcType.isEnum() && dstType.isEnum();
    }

    @Override
    public Object convert(Object src, Class<?> srcType, Class<?> dstType) throws ObjectConversionFailedException {
        return valueOf(dstType, src);
    }

    private Object valueOf(Class<?> dstType, Object src) throws ObjectConversionFailedException {
        Method valueOfMethod = null;
        try {
            valueOfMethod = dstType.getDeclaredMethod("valueOf", String.class);
            return valueOfMethod.invoke(dstType, src.toString());
        } catch (Exception e) {
            throw new ObjectConversionFailedException(this, src, e);
        }
    }

}
