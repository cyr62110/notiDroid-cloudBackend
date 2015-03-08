package fr.cvlaminck.remapper.impl.converters.collection.generic;

public interface ContentTypeDetectionStrategy {

    public boolean canRetrieveContentTypeFrom(Object object);

    public Class<?> getContentType(Object object);

}
