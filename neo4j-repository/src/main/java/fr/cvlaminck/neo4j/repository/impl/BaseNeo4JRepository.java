package fr.cvlaminck.neo4j.repository.impl;

import fr.cvlaminck.neo4j.entities.Neo4JEntity;
import fr.cvlaminck.neo4j.exceptions.Neo4JRepositoryEntityInstantiationException;
import fr.cvlaminck.neo4j.exceptions.Neo4JRepositoryException;
import fr.cvlaminck.neo4j.repository.Neo4JRepository;
import fr.cvlaminck.neo4j.utils.ClasspathUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;

/**
 * Base implementation of a Neo4J repository.
 * It will use the simple name of the base class as label in the database.
 * If the instance of a stored object is not directly of the base type, we will
 * add another label with the 'path' to the implementation.
 *
 * The base implementation do not use any kind of transaction, please do use
 * another implementation of neo4j repository to have support for such feature.
 */public class BaseNeo4JRepository<T extends Neo4JEntity>
        implements Neo4JRepository<T> {

    private final static Character PATH_DOT = '_';

    private GraphDatabaseService graphDatabaseService;

    /**
     * Type of element that will be stored using this repository.
     */
    private Class<T> baseEntityClass;

    private Label[] defaultLabels;

    public BaseNeo4JRepository(Class<T> baseEntityClass, GraphDatabaseService graphDatabaseService) {
        this.baseEntityClass = baseEntityClass;
        this.graphDatabaseService = graphDatabaseService;
        this.defaultLabels = new Label[] {DynamicLabel.label(baseEntityClass.getSimpleName())};
    }

    @Override
    public T getById(long id) {
        return null;
    }

    @Override
    public T create() throws Neo4JRepositoryException {
        return create(baseEntityClass);
    }

    @Override
    public T create(Class<? extends T> entityClass) throws Neo4JRepositoryException {
        //First, we need to determine the label that will be set on the node
        //One is mandatory and is the simple name of the base class
        //The other one is present only if entityClass is a subclass of the base class
        final Label[] nodeLabels;
        if(entityClass == baseEntityClass) {
            nodeLabels = defaultLabels;
        } else {
            nodeLabels = new Label[] {defaultLabels[0],
                    DynamicLabel.label(ClasspathUtils.pathTo(baseEntityClass, entityClass, PATH_DOT))};
        }
        //Then we need to create the node using the database
        Node node = graphDatabaseService.createNode(nodeLabels);
        //Then we create an instance of our entity using the node
        try {
            return ConstructorUtils.invokeConstructor(entityClass, node);
        } catch (Exception e) {
            throw new Neo4JRepositoryEntityInstantiationException(entityClass, e);
        }
    }

    @Override
    public T update(T object) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

}
