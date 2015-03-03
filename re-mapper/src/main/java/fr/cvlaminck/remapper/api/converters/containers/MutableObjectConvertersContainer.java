package fr.cvlaminck.remapper.api.converters.containers;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;

public interface MutableObjectConvertersContainer
    extends ObjectConvertersContainer {

    public void addConverterFor(Class<?> srcType, Class<?> dstType, ObjectConverter converter);

}
