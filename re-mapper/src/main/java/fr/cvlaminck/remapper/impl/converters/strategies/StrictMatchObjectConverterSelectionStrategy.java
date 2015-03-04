package fr.cvlaminck.remapper.impl.converters.strategies;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.api.converters.containers.ObjectConvertersContainer;
import fr.cvlaminck.remapper.api.converters.strategies.ObjectConverterSelectionStrategy;

/**
 * Simplest strategy : returns only an ObjectConverter for the container that strictly convert the input source type.
 */
public class StrictMatchObjectConverterSelectionStrategy
        implements ObjectConverterSelectionStrategy {

    @Override
    public ObjectConverter getConverterFrom(ObjectConvertersContainer container, Class<?> srcType, Class<?> dstType) {
        return container.getConverterFor(srcType, dstType);
    }

}
