package fr.cvlaminck.notidroid.cloud;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * This class helps us to configure Spring Security and the springSecurityFilterChain using
 * annotations. Configurations will be imported by the NotidroidWebAppInitializer.
 */
@Order(1)
public class NotidroidWebAppSecurityInitializer
    extends AbstractSecurityWebApplicationInitializer {



}
