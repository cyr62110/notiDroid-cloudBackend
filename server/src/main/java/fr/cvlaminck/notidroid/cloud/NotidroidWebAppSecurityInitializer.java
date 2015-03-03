package fr.cvlaminck.notidroid.cloud;
import fr.cvlaminck.notidroid.cloud.web.filters.CorsFilter;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;

/**
 * This class helps us to configure Spring Security and the springSecurityFilterChain using
 * annotations. Configurations will be imported by the NotidroidWebAppInitializer.
 *
 * In this class, we also registers all filters that must be applied to all request
 * send to our server : ex. CORS, etc...
 */
@Order(1)
public class NotidroidWebAppSecurityInitializer
        extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
        super.afterSpringSecurityFilterChain(servletContext);
        registerFilters(servletContext);
    }

    private void registerFilters(ServletContext context) {
        insertFilters(context, corsFilter());
    }

    private Filter corsFilter() {
        CorsFilter corsFilter = new CorsFilter();
        return new CorsFilter();
    }

}
