package fr.cvlaminck.notidroid.cloud.config.data;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * A mongo database will be used to store notification and images.
 * Images via GridFS that simplify the installation of the project on a server.
 * Notification in a collection since the TTL feature will be really useful for such data.
 */
@Configuration
@EnableMongoRepositories(basePackages = {
        "fr.cvlaminck.notidroid.cloud.data.repositories.notifications",
        "fr.cvlaminck.notidroid.cloud.data.repositories.tokens",
})
public class NotifDatabaseConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "notidroid"; //TODO configure the db name from a runtime configuration
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost"); //TODO configure the host from a runtime configuration
    }

    @Override
    public MongoTemplate mongoTemplate() throws Exception {
        final MongoTemplate mongoTemplate = super.mongoTemplate();
        //TODO : Configure the WriteConcern when the concept is fully understood
        return mongoTemplate;
    }

    @Override
    protected String getMappingBasePackage() {
        return "fr.cvlaminck.notidroid.cloud.data.entities";
    }
}
