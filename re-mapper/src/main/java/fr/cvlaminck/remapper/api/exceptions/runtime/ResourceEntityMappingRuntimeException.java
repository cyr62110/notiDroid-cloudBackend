package fr.cvlaminck.remapper.api.exceptions.runtime;

public abstract class ResourceEntityMappingRuntimeException extends RuntimeException {

    protected ResourceEntityMappingRuntimeException() {
    }

    protected ResourceEntityMappingRuntimeException(String message) {
        super(message);
    }

    protected ResourceEntityMappingRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
