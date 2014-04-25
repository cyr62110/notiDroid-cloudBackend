package fr.cvlaminck.remapper.api.mappings;

public interface ResourceEntityFieldMapping {

    /**
     * Name of the field that is converted by this mapping
     */
    public String getFieldName();

    //TODO : comment here
    public void copyFromResource(Object resource, Object entity);

    //TODO : comment here
    public void copyFromEntity(Object entity, Object resource);

}
