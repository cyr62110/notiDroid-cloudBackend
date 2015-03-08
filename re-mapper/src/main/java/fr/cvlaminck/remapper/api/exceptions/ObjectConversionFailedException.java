package fr.cvlaminck.remapper.api.exceptions;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;

/**
 * Thrown by an ObjectConverter if it cannot convert the source into a new
 * object.
 */
public class ObjectConversionFailedException
        extends Object2ObjectMappingException {

    private static final String MESSAGE = "ObjectConverter '%s' failed to convert value of type '%s'.";

    public ObjectConversionFailedException(ObjectConverter converter, Object src, String message, Throwable t) {
        super(formatMessage(converter, src, message), t);
    }

    public ObjectConversionFailedException(ObjectConverter converter, Object src) {
        this(converter, src, null, null);
    }

    public ObjectConversionFailedException(ObjectConverter converter, Object src, String message) {
        this(converter, src, message, null);
    }

    public ObjectConversionFailedException(ObjectConverter converter, Object src, Throwable t) {
        this(converter, src, null, t);
    }

    private static String formatMessage(ObjectConverter objectConverter, Object src, String additionalMessage) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(MESSAGE, objectConverter.getClass().getSimpleName(), src.getClass().getSimpleName()));
        if (additionalMessage != null && !additionalMessage.isEmpty()) {
            sb.append("Cause : " + additionalMessage);
        }
        return sb.toString();
    }
}
