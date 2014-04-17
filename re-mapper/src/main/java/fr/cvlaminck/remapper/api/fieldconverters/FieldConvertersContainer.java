package fr.cvlaminck.remapper.api.fieldconverters;

/**
 * Contains the conversion that are actually supported by the ResourceEntityMapper and
 * so the type of fields that will be converted in both way.
 */
public interface FieldConvertersContainer {

    /**
     * Retrieve a converter that accept srcType as source and dstType as output.
     * If there is no converter that supports both, this function will return null.
     *
     * @param srcType Type of the field in the source object
     * @param dstType Type of the field in the destination object
     * @return A converter or null if no converter in the container match the prerequisites
     */
    public FieldConverter getConverterFor(Class<?> srcType, Class<?> dstType);

}
