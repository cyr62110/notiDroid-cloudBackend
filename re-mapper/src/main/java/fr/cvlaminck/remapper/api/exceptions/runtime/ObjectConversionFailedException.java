package fr.cvlaminck.remapper.api.exceptions.runtime;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;

/**
 * Thrown by an ObjectConverter if it cannot convert the source into a new
 * object.
 */
public class ObjectConversionFailedException
        extends Object2ObjectMappingRuntimeException {

    private static final String MESSAGE = "'%s' failed to convert '%s'";

    public ObjectConversionFailedException(ObjectConverter converter, Object src) {
        super(String.format(MESSAGE, converter.getClass().getSimpleName(), src.getClass().getSimpleName()));
    }

    public ObjectConversionFailedException(ObjectConverter converter, Object src, String message) {
        super(String.format(MESSAGE, converter.getClass().getSimpleName(), src.getClass().getSimpleName()) + ": " + message);
    }

    public ObjectConversionFailedException(ObjectConverter converter, Object src, Throwable t) {
        super(String.format(MESSAGE, converter.getClass().getSimpleName(), src.getClass().getSimpleName()), t);
    }
}
