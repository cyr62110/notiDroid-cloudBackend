package fr.cvlaminck.remapper.api.exceptions.runtime;

import fr.cvlaminck.remapper.api.fieldconverters.FieldConverter;

import java.lang.reflect.Field;

//TODO
public class FieldConversionFailedException extends ResourceEntityMappingRuntimeException {

    private static final String MESSAGE = "Conversion from %s to %s.";

    public FieldConversionFailedException(FieldConverter fieldConverter, Field src, Field dst, Throwable ex) {
        super(MESSAGE, ex);
    }

}
