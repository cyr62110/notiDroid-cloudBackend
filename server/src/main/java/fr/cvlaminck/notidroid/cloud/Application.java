package fr.cvlaminck.notidroid.cloud;

import fr.cvlaminck.notidroid.cloud.config.EmbeddedJettyConfiguration;
import fr.cvlaminck.notidroid.cloud.config.data.MainDatabaseConfiguration;
import fr.cvlaminck.notidroid.cloud.config.data.NotifDatabaseConfiguration;
import fr.cvlaminck.notidroid.cloud.config.security.APISecurityConfiguration;
import fr.cvlaminck.notidroid.cloud.config.security.AdminSecurityConfiguration;
import fr.cvlaminck.notidroid.cloud.config.security.SecurityConfiguration;
import fr.cvlaminck.notidroid.cloud.config.webmvc.ThymeleafConfiguration;
import fr.cvlaminck.notidroid.cloud.config.webmvc.WebMVCConfiguration;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.jetty.ServletContextInitializerConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * This class is the configuration of our Spring Boot.
 * And also the class containing our main that will start our server.
 * We do not use the AutoConfiguration annotation since it will
 * conflict between our various Spring Data repositories (some for Neo4j, some for Mongo).
 */
@Import({
        EmbeddedJettyConfiguration.class,
        WebMVCConfiguration.class,
        ThymeleafConfiguration.class,
        MainDatabaseConfiguration.class,
        NotifDatabaseConfiguration.class,
        SecurityConfiguration.class,
        APISecurityConfiguration.class,
        AdminSecurityConfiguration.class})
public class Application {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setResourceBase("src/main/webapp");
        webAppContext.setContextPath("/");
        webAppContext.setConfigurations(new Configuration[] { new AnnotationConfiguration() });
        webAppContext.setParentLoaderPriority(true);

        server.setHandler(webAppContext);
        server.start();
        server.join();
    }

}
