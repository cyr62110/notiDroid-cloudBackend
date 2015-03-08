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
        extends AbstractObjectConverterSelectionStrategy
        implements ObjectConverterSelectionStrategy {

    @Override
    public ObjectConverter getConverterFrom(ObjectConvertersContainer container, Class<?> originalSrcType, Class<?> originalDstType) {
        ObjectConverter objectConverter = null;
        Iterator<Class<?>> iterator = CISISCIterator.forClass(originalSrcType);
        while (iterator.hasNext() && objectConverter == null) {
            Class<?> srcType = iterator.next();
            objectConverter = getConverterFor(container, srcType, originalSrcType, originalDstType);
        }
        return objectConverter;
    }


}
