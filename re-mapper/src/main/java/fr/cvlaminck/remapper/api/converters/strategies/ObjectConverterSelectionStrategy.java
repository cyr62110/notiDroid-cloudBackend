package fr.cvlaminck.remapper.api.converters.strategies;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.api.converters.containers.ObjectConvertersContainer;

/**
 * TODO
 */
public interface ObjectConverterSelectionStrategy {

    /**
     * Returns the ObjectConverter to use to convert from the source type to the destination type.
     * May return null if the conversion is not possible using converters in the container.
     */
    public ObjectConverter getConverterFrom(ObjectConvertersContainer container, Class<?> srcType, Class<?> dstType);

}
