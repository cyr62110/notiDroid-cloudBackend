package fr.cvlaminck.remapper.api.exceptions.runtime;

public abstract class Object2ObjectMappingRuntimeException extends RuntimeException {

    protected Object2ObjectMappingRuntimeException() {
    }

    protected Object2ObjectMappingRuntimeException(String message) {
        super(message);
    }

    protected Object2ObjectMappingRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
