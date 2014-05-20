package fr.cvlaminck.neo4j.entities;

import org.neo4j.graphdb.Node;

/**
 * Base for all entity using neo4j as storage.
 */
public class Neo4JEntity {

    private Node underlyingNode = null;

    public Neo4JEntity(Node underlyingNode) {
        this.underlyingNode = underlyingNode;
    }

    public Long getId() {
        return underlyingNode.getId();
    }

    public Node getUnderlyingNode() {
        return underlyingNode;
    }

    @Override
    public int hashCode() {
        return underlyingNode.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Neo4JEntity &&
                underlyingNode.equals(((Neo4JEntity) o).underlyingNode);
    }

}
