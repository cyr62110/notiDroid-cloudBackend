package fr.cvlaminck.remapper.api.converters.containers;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;

import java.util.Collection;

public interface ObjectConvertersContainer {

    /**
     * Retrieve all converters that accepts srcType as source.
     *
     * @param srcType Type of the field in the source object
     */
    public Collection<ObjectConverter> getConverters(Class<?> srcType);

}
