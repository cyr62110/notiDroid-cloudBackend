package fr.cvlaminck.neo4j.repository;

import fr.cvlaminck.neo4j.entities.Neo4JEntity;
import fr.cvlaminck.neo4j.exceptions.Neo4JRepositoryEntityInstantiationException;
import fr.cvlaminck.neo4j.exceptions.Neo4JRepositoryException;

/**
 * Interface for a database repository implementing a basic CRUD for
 * all neo4j entities.
 */
public interface Neo4JRepository<T extends Neo4JEntity> {

    public T getById(long id);

    public T create() throws Neo4JRepositoryException;

    public T create(Class<? extends T> subclass) throws Neo4JRepositoryException;

    public T update(T object);

    public void delete(long id);

}
