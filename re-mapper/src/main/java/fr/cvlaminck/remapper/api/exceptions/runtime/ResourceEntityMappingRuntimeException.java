package fr.cvlaminck.remapper.api.exceptions.runtime;

import fr.cvlaminck.remapper.api.mappings.ResourceEntityMapping;

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
