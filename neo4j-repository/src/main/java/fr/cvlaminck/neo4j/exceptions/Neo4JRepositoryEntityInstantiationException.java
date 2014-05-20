package fr.cvlaminck.neo4j.exceptions;

import fr.cvlaminck.neo4j.entities.Neo4JEntity;

/**
 * Thrown when the library cannot instantiate an entity for any kind of reason.
 */
public class Neo4JRepositoryEntityInstantiationException
    extends Neo4JRepositoryException {
    private static final String MESSAGE = "Cannot instantiate entity %s. Entity must have a constructor defined as following : public Entity(Node node).";

    public Neo4JRepositoryEntityInstantiationException(Class<? extends Neo4JEntity> entityClass, Throwable cause) {
        super(String.format(MESSAGE, entityClass.getSimpleName()), cause);
    }
}
