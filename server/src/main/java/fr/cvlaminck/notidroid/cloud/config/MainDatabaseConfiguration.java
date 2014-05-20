package fr.cvlaminck.notidroid.cloud.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Neo4J is the main database of this project.
 * This database will store all users, theirs devices, their filters, all applications
 * that have sent notification through this project, etc...
 */
@Configuration
public class MainDatabaseConfiguration {

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory().newEmbeddedDatabase("main.db");
    }

}
