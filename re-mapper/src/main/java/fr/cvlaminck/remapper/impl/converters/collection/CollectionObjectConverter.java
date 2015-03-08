package fr.cvlaminck.remapper.impl.converters.collection;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.api.converters.containers.ObjectConvertersContainer;
import fr.cvlaminck.remapper.api.converters.strategies.ObjectConverterSelectionStrategy;
import fr.cvlaminck.remapper.api.exceptions.ObjectConversionFailedException;
import fr.cvlaminck.remapper.impl.converters.collection.generic.ContentTypeDetectionStrategy;
import fr.cvlaminck.remapper.impl.converters.collection.generic.FieldBasedContentTypeDetectionStrategy;
import fr.cvlaminck.remapper.impl.converters.strategies.CISISCObjectConverterSelectionStrategy;
import org.apache.commons.lang3.reflect.ConstructorUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 *
 */
public class CollectionObjectConverter
        implements ObjectConverter {

    private ObjectConvertersContainer container;

    private ObjectConverterSelectionStrategy selectionStrategy = null;

    private ContentTypeDetectionStrategy contentTypeDetectionStrategy = null;

    public CollectionObjectConverter(ObjectConvertersContainer container) {
        this(container, new CISISCObjectConverterSelectionStrategy());
    }

    public CollectionObjectConverter(ObjectConvertersContainer container, ObjectConverterSelectionStrategy selectionStrategy) {
        this(container, selectionStrategy, new FieldBasedContentTypeDetectionStrategy());
    }

    public CollectionObjectConverter(ObjectConvertersContainer container, ContentTypeDetectionStrategy contentTypeDetectionStrategy) {
        this(container, new CISISCObjectConverterSelectionStrategy(), contentTypeDetectionStrategy);
    }

    public CollectionObjectConverter(ObjectConvertersContainer container, ObjectConverterSelectionStrategy selectionStrategy, ContentTypeDetectionStrategy contentTypeDetectionStrategy) {
        this.container = container;
        this.selectionStrategy = selectionStrategy;
        this.contentTypeDetectionStrategy = contentTypeDetectionStrategy;
    }

    @Override
    public Class<?> getSourceType() {
        return Collection.class;
    }

    @Override
    public boolean supports(Class<?> srcType, Class<?> dstType) {
        //First, both types must inherits from Collection
        if (!Collection.class.isAssignableFrom(srcType) || !Collection.class.isAssignableFrom(dstType))
            return false;
        //Second, the source type MUST be the same as or a superclass/superinterface of the destination one.
        if (!dstType.isAssignableFrom(srcType))
            return false;
        return true;
    }

    @Override
    public Object convert(Object oSrc, Class<?> srcType, Class<?> dstType) throws ObjectConversionFailedException {
        Collection<Object> src = (Collection<Object>) oSrc;
        Collection<Object> dst = null;
        try {
            dst = instantiateNewCollection(src);
        } catch (Exception e) {
            throw new ObjectConversionFailedException(this, oSrc, e);
        }
        if (dst == null) {
            throw new ObjectConversionFailedException(this, oSrc, "No public default constructor");
        }
        return convert(src, null, dst, null);
    }

    @Override
    public Object convert(Object oSrc, Field srcField, Field dstField) throws ObjectConversionFailedException {
        Collection<Object> src = (Collection<Object>) oSrc;
        Collection<Object> dst = null;
        try {
            dst = instantiateNewCollection(src);
        } catch (Exception e) {
            throw new ObjectConversionFailedException(this, oSrc, e);
        }
        if (dst == null) {
            throw new ObjectConversionFailedException(this, oSrc, "No public default constructor");
        }
        return convert(src, srcField, dst, dstField);
    }

    private Object convert(Collection<Object> src, Field srcField, Collection<Object> dst, Field dstField) throws ObjectConversionFailedException {
        Class<?> srcContentType = null;
        Class<?> dstContentType = null;
        if (srcField != null && contentTypeDetectionStrategy.canRetrieveContentTypeFrom(srcField)) {
            srcContentType = contentTypeDetectionStrategy.getContentType(srcField);
        } else if (contentTypeDetectionStrategy.canRetrieveContentTypeFrom(src)) {
            srcContentType = contentTypeDetectionStrategy.getContentType(src);
        }
        if (srcContentType == null) {
            throw new ObjectConversionFailedException(this, src, "Cannot detect content type of the source collection using '" + contentTypeDetectionStrategy.getClass().getSimpleName() + "' strategy");
        }
        if (dstField != null && contentTypeDetectionStrategy.canRetrieveContentTypeFrom(dstField)) {
            dstContentType = contentTypeDetectionStrategy.getContentType(dstField);
        } else {
            dstContentType = srcContentType;
        }
        convertObjectsInSource(src, srcContentType, dst, dstContentType);
        return dst;
    }

    private Collection<Object> instantiateNewCollection(Collection<Object> src) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        //First, we try to find a constructor using capacity to reduce the number of memory allocation
        Collection<Object> dst = instantiateNewCollectionUsingCapacity(src);
        if (dst == null) {
            dst = instantiateNewCollectionUsingDefaultConstructor(src);
        }
        return dst;
    }

    private Collection<Object> instantiateNewCollectionUsingCapacity(Collection<Object> src) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            return ConstructorUtils.invokeConstructor(src.getClass(), new Object[]{src.size()}, new Class<?>[]{int.class});
        } catch (NoSuchMethodException e) {
            return null;
        }

    }

    private Collection<Object> instantiateNewCollectionUsingDefaultConstructor(Collection<Object> src) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            return ConstructorUtils.invokeConstructor(src.getClass());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private void convertObjectsInSource(Collection<Object> src, Class<?> srcContentType, Collection<Object> dst, Class<?> dstContentType) throws ObjectConversionFailedException {
        if (src.isEmpty()) {
            return;
        }
        ObjectConverter converter = selectionStrategy.getConverterFrom(container, srcContentType, dstContentType);
        if (converter == null) {
            throw new ObjectConversionFailedException(this, src, "No converter available to convert '" + srcContentType.getSimpleName() + "' into '" + dstContentType.getSimpleName() + "'");
        }
        for (Object o : src) {
            dst.add(converter.convert(o, srcContentType, dstContentType));
        }
    }

}
