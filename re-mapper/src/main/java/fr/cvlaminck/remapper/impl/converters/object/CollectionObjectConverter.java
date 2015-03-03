package fr.cvlaminck.remapper.impl.converters.object;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.api.converters.containers.ObjectConvertersContainer;
import fr.cvlaminck.remapper.api.converters.strategies.ObjectConverterSelectionStrategy;
import fr.cvlaminck.remapper.api.exceptions.runtime.ObjectConversionFailedException;
import fr.cvlaminck.remapper.impl.converters.strategies.CISISCObjectConverterSelectionStrategy;
import org.apache.commons.lang3.reflect.ConstructorUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class CollectionObjectConverter
        implements ObjectConverter {

    private ObjectConvertersContainer container;

    private ObjectConverterSelectionStrategy selectionStrategy = null;

    public CollectionObjectConverter(ObjectConvertersContainer container) {
        this(container, new CISISCObjectConverterSelectionStrategy());
    }

    public CollectionObjectConverter(ObjectConvertersContainer container, ObjectConverterSelectionStrategy selectionStrategy) {
        this.container = container;
        this.selectionStrategy = selectionStrategy;
    }

    @Override
    public Class<?> getSourceType() {
        return Collection.class;
    }

    @Override
    public boolean supports(Class<?> srcType, Class<?> dstType) {
        //First, both types must inherits from Collection
        if(!Collection.class.isAssignableFrom(srcType) || !Collection.class.isAssignableFrom(dstType))
            return false;
        //Second, the source type MUST be the same as or a superclass/superinterface of the destination one.
        if(!dstType.isAssignableFrom(srcType))
            return false;
        return true;
    }

    @Override
    public Object convert(Object oSrc, Class<?> srcType, Class<?> dstType) {
        Collection<Object> dest = null;
        try {
            Collection<Object> src = (Collection<Object>) oSrc;
            dest = instantiateNewCollection(src);
            convertObjectsInSource(src, dest);
        } catch (NullPointerException npe) {
            throw new ObjectConversionFailedException(this, oSrc, "No public default constructor");
        } catch (Exception e) {
            throw new ObjectConversionFailedException(this, oSrc, e);
        }
        return dest;
    }

    private Collection<Object> instantiateNewCollection(Collection<Object> src) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Collection<Object> dest = null;
        //First, we try to find a constructor using capacity to reduce the number of memory allocation
        dest = instantiateNewCollectionUsingCapacity(src);
        if(dest == null) {
            dest = instantiateNewCollectionUsingDefaultConstructor(src);
        }
        return dest;
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

    private void convertObjectsInSource(Collection<Object> src, Collection<Object> dest) {
        if(src.isEmpty())
            return;
        Map<Class<?>, ObjectConverter> converters = new HashMap<Class<?>, ObjectConverter>();
        for(Object o : src) {
            ObjectConverter converter = converters.get(o.getClass());
            if(converter == null) {
                converter = selectionStrategy.getConverterFrom(container, o.getClass(), o.getClass());
                converters.put(o.getClass(), converter);
            }
            if(converter != null) {
                dest.add(converter.convert(o, o.getClass(), o.getClass()));
            } else {
                //TODO : What should we do, ignore the value or returns an empty collection ?
            }
        }
    }

}
