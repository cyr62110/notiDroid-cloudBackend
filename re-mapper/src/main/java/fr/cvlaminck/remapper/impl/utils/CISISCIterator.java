package fr.cvlaminck.remapper.impl.utils;

import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * Iterator iterating through class interfaces and superclasses.
 * It iterates through all types by following the pattern CISICS pattern described below : <br />
 * - Class <br />
 * - Interfaces <br />
 * - Super ... Interfaces <br />
 * - Then we repeat these pattern on super classes until we reach Object.
 */
public class CISISCIterator<T extends Type>
        implements Iterator<T> {

    private Deque<T> types;
    private TypeInformationAccessor<T> typeInformationAccessor;

    public CISISCIterator(T type, TypeInformationAccessor<T> typeInformationAccessor) {
        this.typeInformationAccessor = typeInformationAccessor;
        this.types = new ArrayDeque<T>();
        this.types.addFirst(type);
    }

    public static CISISCIterator<Class<?>> forClass(Class<?> type) {
        return new CISISCIterator<Class<?>>(type, new ClassTypeInformationAccessor());
    }

    @Override
    public boolean hasNext() {
        return !types.isEmpty();
    }

    @Override
    public T next() {
        T nextType = types.removeFirst();
        if (nextType != Object.class) {
            T nextTypeSuperclass = typeInformationAccessor.getSuperclass(nextType);
            if (nextTypeSuperclass != null) {
                types.addFirst(typeInformationAccessor.getSuperclass(nextType));
            }
            for (T nextTypeInterface : typeInformationAccessor.getInterfaces(nextType)) {
                types.addFirst(nextTypeInterface);
            }
        }
        return nextType;
    }

    public interface TypeInformationAccessor<T> {

        public T getSuperclass(T type);

        public T[] getInterfaces(T type);

    }

    private static class ClassTypeInformationAccessor
            implements TypeInformationAccessor<Class<?>> {

        @Override
        public Class<?> getSuperclass(Class<?> type) {
            return type.getSuperclass();
        }

        @Override
        public Class<?>[] getInterfaces(Class<?> type) {
            return type.getInterfaces();
        }
    }

}
