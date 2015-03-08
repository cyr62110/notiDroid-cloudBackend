package fr.cvlaminck.remapper.impl.converters.collection.generic;

import fr.cvlaminck.remapper.impl.utils.CISISCIterator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Iterator;

/**
 * TODO
 */
public class FieldBasedContentTypeDetectionStrategy
        implements ContentTypeDetectionStrategy {

    @Override
    public boolean canRetrieveContentTypeFrom(Object object) {
        return object instanceof Field;
    }

    @Override
    public Class<?> getContentType(Object oField) {
        if (!(oField instanceof Field)) {
            return null; //FIXME handle this error with a proper exception
        }
        Field field = (Field) oField;
        Class<?> contentType = null;
        Iterator<Type> iterator = new CISISCIterator<Type>(field.getGenericType(), new GenericTypeInformationAccessor());
        while (contentType == null && iterator.hasNext()) {
            Type nextType = iterator.next();
            if (nextType instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) nextType;
                if (pType.getActualTypeArguments()[0] instanceof Class) {
                    contentType = (Class<?>) pType.getActualTypeArguments()[0];
                } else if (nextType instanceof TypeVariable) {
                    return null; //FIXME TypeVariable are not supported by this version. Should be implemented soon.
                }
            }
        }
        return contentType;
    }

    private static class GenericTypeInformationAccessor
            implements CISISCIterator.TypeInformationAccessor<Type> {

        @Override
        public Type getSuperclass(Type type) {
            if (type instanceof ParameterizedType && ((ParameterizedType) type).getRawType() instanceof Class) {
                return ((Class) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
            } else if (type instanceof TypeVariable) {
                //FIXME TypeVariable are not support by this version. Should be implemented soon.
                return null;
            } else if (type instanceof Class) {
                return ((Class) type).getGenericSuperclass();
            }
            return null;
        }

        @Override
        public Type[] getInterfaces(Type type) {
            if (type instanceof ParameterizedType && ((ParameterizedType) type).getRawType() instanceof Class) {
                return ((Class) ((ParameterizedType) type).getRawType()).getGenericInterfaces();
            } else if (type instanceof TypeVariable) {
                //FIXME TypeVariable are not support by this version. Should be implemented soon.
                return null;
            } else if (type instanceof Class) {
                return ((Class) type).getGenericInterfaces();
            }
            return null;
        }
    }

}
