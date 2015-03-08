package fr.cvlaminck.remapper.impl.converters.collection.generic;

import java.util.Collection;

/**
 * TODO
 *
 * @deprecated
 */
public class FirstElementContentTypeDetectionStrategy
        implements ContentTypeDetectionStrategy {

    @Override
    public boolean canRetrieveContentTypeFrom(Object object) {
        return object instanceof Collection;
    }

    @Override
    public Class<?> getContentType(Object object) {
        Collection<Object> cObject = (Collection<Object>) object;
        if (cObject.isEmpty()) {
            return Object.class;
        } else {
            return cObject.iterator().next().getClass();
        }
    }

}
