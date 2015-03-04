package fr.cvlaminck.remapper.impl.converters.strategies;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.api.converters.containers.ObjectConvertersContainer;
import fr.cvlaminck.remapper.api.converters.strategies.ObjectConverterSelectionStrategy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Default strategy to select object mappers that will be used for a mapping.
 * The strategy implemented is the following :
 * - Class
 * - Interfaces
 * - Super Interfaces
 * - Super super interfaces...
 * - Super class
 * - Super class interfaces
 * - Super Super class...
 * - Object
 */
public class CISISCObjectConverterSelectionStrategy
        implements ObjectConverterSelectionStrategy {

    @Override
    public ObjectConverter getConverterFrom(ObjectConvertersContainer container, Class<?> srcType, Class<?> dstType) {
        ObjectConverter objectConverter = null;
        Deque<Class<?>> types = new ArrayDeque<>();
        types.addFirst(srcType);
        while (objectConverter == null && !types.isEmpty()) {
            Class<?> type = types.removeFirst();
            objectConverter = container.getConverterFor(type, dstType);
            if (type != Object.class && objectConverter == null) {
                if (type.getSuperclass() != null)
                    types.addFirst(type.getSuperclass());
                for (Class<?> interfaceType : type.getInterfaces()) {
                    types.addFirst(interfaceType);
                }
            }
        }
        return objectConverter;
    }

}
