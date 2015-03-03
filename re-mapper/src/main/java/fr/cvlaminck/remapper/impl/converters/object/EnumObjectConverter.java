package fr.cvlaminck.remapper.impl.converters.object;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EnumObjectConverter
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
    public Object convert(Object src, Class<?> srcType, Class<?> dstType) {
        return valueOf(dstType, src.toString());
    }

    private Object valueOf(Class<?> dstType, String value) {
        Method valueOfMethod = null;
        try {
            valueOfMethod = dstType.getDeclaredMethod("valueOf", String.class);
            return valueOfMethod.invoke(dstType, value);
        } catch (NoSuchMethodException e) { //TODO : We ignore silently all errors, find a better way to handle this
        } catch (InvocationTargetException e) {
        } catch (IllegalAccessException e) {
        }
        return null;
    }

}
