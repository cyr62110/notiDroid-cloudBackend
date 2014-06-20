package fr.cvlaminck.notidroid.cloud;

import fr.cvlaminck.notidroid.cloud.config.core.CoreConfiguration;
import fr.cvlaminck.notidroid.cloud.config.data.MainDatabaseConfiguration;
import fr.cvlaminck.notidroid.cloud.config.data.NotifDatabaseConfiguration;
import fr.cvlaminck.notidroid.cloud.config.security.SecurityConfiguration;
import fr.cvlaminck.notidroid.cloud.config.webmvc.WebMVCConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * This class help us to configure the Spring DispatcherServlet using
 * annotations.
 */
@Order(2)
public class NotidroidWebAppInitializer
    extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
                MainDatabaseConfiguration.class,
                NotifDatabaseConfiguration.class,
                CoreConfiguration.class,
                SecurityConfiguration.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {
                WebMVCConfiguration.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }


}
