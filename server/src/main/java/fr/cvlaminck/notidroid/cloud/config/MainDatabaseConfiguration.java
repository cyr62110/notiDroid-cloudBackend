package fr.cvlaminck.notidroid.cloud.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.JtaTransactionManagerFactoryBean;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.data.neo4j.support.Infrastructure;
import org.springframework.data.neo4j.support.MappingInfrastructureFactoryBean;
import org.springframework.data.neo4j.support.Neo4jExceptionTranslator;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.mapping.Neo4jMappingContext;
import org.springframework.data.neo4j.support.typesafety.TypeSafetyOption;
import org.springframework.data.neo4j.support.typesafety.TypeSafetyPolicy;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Neo4J is the main database of this project.
 * This database will store all users, theirs devices, their filters, all applications
 * that have sent notification through this project, etc...
 */
@Configuration
@EnableNeo4jRepositories(basePackages = {"fr.cvlaminck.notidroid.cloud.data.repositories"})
public class MainDatabaseConfiguration {

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return new SpringRestGraphDatabase("http://localhost:7474/db/data/");
        //return new GraphDatabaseFactory().newEmbeddedDatabase("notidroid-main.db");
    }

    /**
     * We must create manually the infrastructure so we can have a bit
     * of control over the type safety policy for example.
     *
     * @param graphDatabaseService GraphDatabaseService used to communicate with the database.
     * @return The infrastructure to give to the Neo4JTemplate that will be used by repositories.
     */
    @Bean
    @Autowired
    public Infrastructure infrastructure(GraphDatabaseService graphDatabaseService) {
        //We create our mapping infrastructure bean that will create the infrastructure
        final MappingInfrastructureFactoryBean mappingInfrastructureFactoryBean = new MappingInfrastructureFactoryBean(graphDatabaseService, null);
        //We set the type safety policy cause the default one is not sufficient
        mappingInfrastructureFactoryBean.setTypeSafetyPolicy(new TypeSafetyPolicy(TypeSafetyOption.RETURNS_NULL));
        //Then we finish the creation
        mappingInfrastructureFactoryBean.afterPropertiesSet();
        return mappingInfrastructureFactoryBean.getObject();
    }

    @Bean
    @Autowired
    public Neo4jTemplate neo4jTemplate(Infrastructure infrastructure) {
        final Neo4jTemplate neo4jTemplate = new Neo4jTemplate(infrastructure);
        return neo4jTemplate;
    }

    @Bean
    public Neo4jMappingContext neo4jMappingContext() {
        return new Neo4jMappingContext();
    }

    @Bean
    public PersistenceExceptionTranslator exceptionTranslator() {
        return new Neo4jExceptionTranslator();
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(GraphDatabaseService graphDatabaseService) throws Exception {
        return new JtaTransactionManagerFactoryBean(graphDatabaseService).getObject();
    }

}
