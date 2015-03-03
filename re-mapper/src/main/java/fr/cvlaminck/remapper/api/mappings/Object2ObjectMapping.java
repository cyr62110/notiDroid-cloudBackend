package fr.cvlaminck.remapper.api.mappings;

/**
 * /!\ Mapping are not reversible : A->B and B->A are two different instance of
 * Object2ObjectMapping.
 */
public interface Object2ObjectMapping
        extends Iterable<Object2ObjectFieldMapping> {

    public Class<?> getSourceType();

    public Class<?> getDestinationType();

}
