package fr.cvlaminck.remapper.impl.fieldconverters;

import fr.cvlaminck.remapper.api.fieldconverters.FieldConverter;
import fr.cvlaminck.remapper.api.fieldconverters.FieldConvertersContainer;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the FieldConvertersContainer.
 */
public class DefaultFieldConvertersContainer
        implements FieldConvertersContainer {

    private Map<Pair<Class<?>, Class<?>>, FieldConverter> fieldConverters = null;

    public DefaultFieldConvertersContainer() {
        this.fieldConverters = new HashMap<Pair<Class<?>, Class<?>>, FieldConverter>();

        //TODO Initialize with BasicFieldConverter for all common java types
    }

    @Override
    public FieldConverter getConverterFor(Class<?> srcType, Class<?> dstType) {
        Pair<Class<?>, Class<?>> conversion = new ImmutablePair<Class<?>, Class<?>>(srcType, dstType);
        return fieldConverters.get(conversion);
    }

    /**
     * Add a new FieldConverter to this container.
     *
     * @param fieldConverter
     */
    public void addFieldConverter(FieldConverter fieldConverter) {
        Pair<Class<?>, Class<?>> conversion = new ImmutablePair<Class<?>, Class<?>>(fieldConverter.getInputType(), fieldConverter.getOutputType());
        fieldConverters.put(conversion, fieldConverter);
    }

}
