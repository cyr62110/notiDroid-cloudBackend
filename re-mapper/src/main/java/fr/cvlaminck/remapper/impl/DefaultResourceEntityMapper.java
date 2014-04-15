package fr.cvlaminck.remapper.impl;

import fr.cvlaminck.remapper.api.ResourceEntityMapper;

public class DefaultResourceEntityMapper
   implements ResourceEntityMapper {

    @Override
    public <E, R> R convertToResource(E entity, Class<E> entityType, Class<R> resourceType) {
        return null;
    }

    @Override
    public <E, R> E convertToEntity(R resource, Class<E> entityType, Class<R> resourceType) {
        return null;
    }
}

