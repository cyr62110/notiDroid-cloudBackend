package fr.cvlaminck.remapper.api.exceptions;

public abstract class Object2ObjectMappingException
        extends Exception {

    protected Object2ObjectMappingException() {
    }

    protected Object2ObjectMappingException(String message) {
        super(message);
    }

    protected Object2ObjectMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    protected Object2ObjectMappingException(Throwable cause) {
        super(cause);
    }

    protected Object2ObjectMappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
