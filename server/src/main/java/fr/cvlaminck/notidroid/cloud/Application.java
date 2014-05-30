package fr.cvlaminck.notidroid.cloud;

import fr.cvlaminck.notidroid.cloud.config.data.MainDatabaseConfiguration;
import fr.cvlaminck.notidroid.cloud.config.data.NotifDatabaseConfiguration;
import fr.cvlaminck.notidroid.cloud.config.security.AdminSecurityConfiguration;
import fr.cvlaminck.notidroid.cloud.config.security.AuthorizationServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * This class is the configuration of our Spring Boot.
 * And also the class containing our main that will start our server.
 * We do not use the AutoConfiguration annotation since it will
 * conflict between our various Spring Data repositories (some for Neo4j, some for Mongo).
 */
@ComponentScan
@Import({
        MainDatabaseConfiguration.class,
        NotifDatabaseConfiguration.class,
        AuthorizationServerConfiguration.class,
        AdminSecurityConfiguration.class})
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
    }

}
