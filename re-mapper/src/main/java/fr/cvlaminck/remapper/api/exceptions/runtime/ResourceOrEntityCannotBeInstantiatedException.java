package fr.cvlaminck.remapper.api.exceptions.runtime;

/**
 * Thrown when the resource or the entity type has no default constructor.
 * Therefor the library cannot instantiate a blank one to map to.
 */
public class ResourceOrEntityCannotBeInstantiatedException
        extends ResourceEntityMappingRuntimeException {
    private static final String MESSAGE = "Class '%s' cannot be instantiated using default constructor.";

    public ResourceOrEntityCannotBeInstantiatedException(Class<?> resourceOrEntityType, Exception e) {
        super(String.format(MESSAGE, resourceOrEntityType.getSimpleName()), e);
    }

}
