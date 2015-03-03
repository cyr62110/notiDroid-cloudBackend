package fr.cvlaminck.remapper.api.mappings;

public interface Object2ObjectFieldMapping {

    /**
     * Name of the field that is converted by this mapping
     */
    public String getFieldName();

    /**
     * Copy the value of this mapped field from the source object to the destination object.
     * The value is converted during the copy using the predetermined ObjectConverter.
     */
    public void convert(Object src, Object dst);

}
