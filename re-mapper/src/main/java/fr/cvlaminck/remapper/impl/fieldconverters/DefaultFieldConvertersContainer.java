package fr.cvlaminck.remapper.impl.fieldconverters;

import fr.cvlaminck.remapper.api.fieldconverters.FieldConverter;
import fr.cvlaminck.remapper.api.fieldconverters.FieldConvertersContainer;
import fr.cvlaminck.remapper.impl.fieldconverters.object.BoxedPrimitiveFieldConverter;
import fr.cvlaminck.remapper.impl.fieldconverters.object.BoxedPrimitiveFieldConverters;
import fr.cvlaminck.remapper.impl.fieldconverters.object.StringFieldConverter;
import fr.cvlaminck.remapper.impl.fieldconverters.primitive.PrimitiveFieldConverters;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the FieldConvertersContainer.
 */
public class DefaultFieldConvertersContainer
        implements FieldConvertersContainer {

    private Map<Pair<Class<?>, Class<?>>, FieldConverter> fieldConverters = null;

    public DefaultFieldConvertersContainer(boolean initializeWithStandardJavaTypes) {
        this.fieldConverters = new HashMap<Pair<Class<?>, Class<?>>, FieldConverter>();
        if(initializeWithStandardJavaTypes)
            addStandardJavaTypeConverters();
    }

    public DefaultFieldConvertersContainer() {
        this(true);
    }

    /**
     * Automatically add converters for standard java types.
     */
    public void addStandardJavaTypeConverters() {
        //Primitive types
        addFieldConverters(PrimitiveFieldConverters.get());
        //Boxed primitive types
        addFieldConverters(BoxedPrimitiveFieldConverters.get());
        //String
        addFieldConverter(new StringFieldConverter());
        //java.util.Date
        //TODO
    }

    @Override
    public FieldConverter getConverterFor(Class<?> srcType, Class<?> dstType) {
        Pair<Class<?>, Class<?>> conversion = new ImmutablePair<Class<?>, Class<?>>(srcType, dstType);
        return fieldConverters.get(conversion);
    }

    /**
     * Add a new FieldConverter to this container. By adding a new field converter, you will allow your
     * resource-entity mapper to convert a new type of data.
     *
     * @param fieldConverter Field converter to add to this container.
     */
    public void addFieldConverter(FieldConverter fieldConverter) {
        final Pair<Class<?>, Class<?>> conversion = new ImmutablePair<Class<?>, Class<?>>(fieldConverter.getInputType(), fieldConverter.getOutputType());
        fieldConverters.put(conversion, fieldConverter);
    }

    /**
     * Add all field converters contained in the collection to this container.
     *
     * @param fieldConverters
     */
    public void addFieldConverters(Collection<FieldConverter> fieldConverters) {
        for(FieldConverter fc : fieldConverters)
            addFieldConverter(fc);
    }

}
