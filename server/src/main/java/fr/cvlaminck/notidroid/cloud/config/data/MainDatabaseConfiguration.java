package fr.cvlaminck.notidroid.cloud.config.data;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.data.neo4j.support.typesafety.TypeSafetyOption;
import org.springframework.data.neo4j.support.typesafety.TypeSafetyPolicy;

/**
 * Neo4J is the main database of this project.
 * This database will store all users, theirs devices, their filters, all applications
 * that have sent notification through this project, etc...
 */
@Configuration
@EnableNeo4jRepositories(basePackages = {
        "fr.cvlaminck.notidroid.cloud.data.repositories.devices",
        "fr.cvlaminck.notidroid.cloud.data.repositories.users",
})
@ComponentScan(basePackages = "fr.cvlaminck.notidroid.cloud.data.repositories.extensions")
public class MainDatabaseConfiguration extends Neo4jConfiguration {

    public MainDatabaseConfiguration() {
        setBasePackage("fr.cvlaminck.notidroid.cloud.data");
    }

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return new SpringRestGraphDatabase("http://localhost:7474/db/data/");
        //return new GraphDatabaseFactory().newEmbeddedDatabase("notidroid-main.db");
    }

    /**
     * We change the type safety policy to return null if the requested class
     * do not inherit from or is not the stored class.
     */
    @Override
    public TypeSafetyPolicy typeSafetyPolicy() throws Exception {
        return new TypeSafetyPolicy(TypeSafetyOption.RETURNS_NULL);
    }

}
