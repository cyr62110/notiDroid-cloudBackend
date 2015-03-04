package fr.cvlaminck.remapper.impl.converters.containers;

import fr.cvlaminck.remapper.api.Object2ObjectMapper;
import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.api.converters.containers.ObjectConvertersContainer;
import fr.cvlaminck.remapper.impl.converters.collection.CollectionObjectConverter;
import fr.cvlaminck.remapper.impl.converters.object.EnumObjectConverter;
import fr.cvlaminck.remapper.impl.converters.object.Object2ObjectMapperBasedObjectConverter;
import fr.cvlaminck.remapper.impl.converters.object.StringObjectConverter;
import fr.cvlaminck.remapper.impl.converters.object.boxed.BoxedPrimitiveObjectConverters;
import fr.cvlaminck.remapper.impl.converters.primitive.PrimitiveObjectConverters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the FieldConvertersContainer.
 */
public class DefaultObjectConvertersContainer
        implements ObjectConvertersContainer {

    private Object2ObjectMapper object2ObjectMapper;

    private Map<Class<?>, Collection<ObjectConverter>> fieldConverters = null;

    public DefaultObjectConvertersContainer() {
        this.fieldConverters = new HashMap<Class<?>, Collection<ObjectConverter>>();
        addStandardJavaTypeConverters();
    }

    public DefaultObjectConvertersContainer(Object2ObjectMapper mapper) {
        this();
        addFieldConverter(new Object2ObjectMapperBasedObjectConverter(mapper));
    }

    /**
     * Automatically add converters for standard java types.
     */
    protected void addStandardJavaTypeConverters() {
        //Primitive types
        addFieldConverters(PrimitiveObjectConverters.get());
        //Boxed primitive types
        addFieldConverters(BoxedPrimitiveObjectConverters.get());
        //Enum
        addFieldConverter(new EnumObjectConverter());
        //String
        addFieldConverter(new StringObjectConverter());
        //java.util.Date
        //TODO
        //java.util.Collection
        addFieldConverter(new CollectionObjectConverter(this));
    }

    @Override
    public ObjectConverter getConverterFor(Class<?> srcType, Class<?> dstType) {
        Collection<ObjectConverter> convertersForType = fieldConverters.get(srcType);
        if (convertersForType == null) {
            return null;
        }
        for (ObjectConverter objectConverter : convertersForType) {
            if (objectConverter.supports(srcType, dstType))
                return objectConverter;
        }
        return null;
    }

    /**
     * Add a new FieldConverter to this container. By adding a new field converter, you will allow your
     * resource-entity mapper to convert a new type of data.
     *
     * @param objectConverter Field converter to add to this container.
     */
    protected void addFieldConverter(ObjectConverter objectConverter) {
        Collection<ObjectConverter> convertersForType = fieldConverters.get(objectConverter.getSourceType());
        if (convertersForType == null) {
            convertersForType = new ArrayList<ObjectConverter>();
            fieldConverters.put(objectConverter.getSourceType(), convertersForType);
        }
        convertersForType.add(objectConverter);
    }

    /**
     * Add all field converters contained in the collection to this container.
     *
     * @param objectConverters
     */
    protected void addFieldConverters(Collection<ObjectConverter> objectConverters) {
        for (ObjectConverter fc : objectConverters)
            addFieldConverter(fc);
    }

}
