package fr.cvlaminck.remapper.api.exceptions.runtime;

/**
 * Thrown if you have defined an alias using the name of an existing field.
 */
public class AliasOverlappingFieldException
        extends Object2ObjectMappingRuntimeException {

    private static final String MESSAGE = "Overlapping alias found when trying building mapping for %s. '%s' is also the name of a field.";

    public AliasOverlappingFieldException(Class<?> clazz, String alias) {
        super(String.format(MESSAGE, clazz.getSimpleName(), alias));
    }
}
