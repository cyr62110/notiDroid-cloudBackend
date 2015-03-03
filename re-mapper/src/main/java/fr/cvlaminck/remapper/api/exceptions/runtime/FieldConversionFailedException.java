package fr.cvlaminck.remapper.api.exceptions.runtime;

import java.lang.reflect.Field;

//TODO
public class FieldConversionFailedException extends Object2ObjectMappingRuntimeException {

    private static final String MESSAGE = "Conversion from %s to %s.";

    public FieldConversionFailedException(Field src, Field dst, Throwable ex) {
        super(MESSAGE, ex);
    }

}
