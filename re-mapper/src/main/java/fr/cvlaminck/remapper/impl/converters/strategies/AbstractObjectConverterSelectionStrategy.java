package fr.cvlaminck.remapper.impl.converters.strategies;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.api.converters.containers.ObjectConvertersContainer;
import fr.cvlaminck.remapper.api.converters.strategies.ObjectConverterSelectionStrategy;

import java.util.Collection;

public abstract class AbstractObjectConverterSelectionStrategy
        implements ObjectConverterSelectionStrategy {

    protected ObjectConverter getConverterFor(ObjectConvertersContainer container, Class<?> computedSrcType, Class<?> originalSrcType, Class<?> originalDstType) {
        Collection<ObjectConverter> objectConverters = container.getConverters(computedSrcType);
        for (ObjectConverter objectConverter : objectConverters) {
            if (objectConverter.supports(originalSrcType, originalDstType)) {
                return objectConverter;
            }
        }
        return null;
    }

}
