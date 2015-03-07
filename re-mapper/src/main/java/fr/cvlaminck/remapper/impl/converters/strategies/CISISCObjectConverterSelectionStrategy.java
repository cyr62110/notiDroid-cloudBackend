package fr.cvlaminck.remapper.impl.converters.strategies;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.api.converters.containers.ObjectConvertersContainer;
import fr.cvlaminck.remapper.api.converters.strategies.ObjectConverterSelectionStrategy;
import fr.cvlaminck.remapper.impl.utils.CISISCIterator;

import java.util.Iterator;

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
        Iterator<Class<?>> iterator = CISISCIterator.forClass(srcType);
        while (iterator.hasNext() && objectConverter == null) {
            objectConverter = container.getConverterFor(iterator.next(), dstType);
        }
        return objectConverter;
    }

}
