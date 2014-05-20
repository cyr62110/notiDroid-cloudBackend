package fr.cvlaminck.neo4j.exceptions;

/**
 * Base class of all exceptions thrown by the library.
 */
public abstract class Neo4JRepositoryException extends Exception {

    protected Neo4JRepositoryException() {
    }

    protected Neo4JRepositoryException(String message) {
        super(message);
    }

    protected Neo4JRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    protected Neo4JRepositoryException(Throwable cause) {
        super(cause);
    }

    protected Neo4JRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
