package fr.cvlaminck.notidroid.cloud.config.webmvc;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Spring WebMVC configuration. Includes :
 * - Configuration to allow static resources to be served by the DispatcherServlet
 * - Thymeleaf template engine configuration
 * - Message source for I18N configuration
 * <p>
 * If you are using Spring Security Oauth 2, you must use WebMvcConfigurationSupport instead of
 * WebMvcConfigurationAdapter otherwise your Oauth2 method mappings will not be accessible and you
 * will receive 404 http error. In this case, you must not put the @EnableWebMvc annotation anywhere.
 */
@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = {
        "fr.cvlaminck.notidroid.cloud.front.admin.controllers",
        "fr.cvlaminck.notidroid.cloud.front.api.controllers",
        "fr.cvlaminck.notidroid.cloud.front.debug.controllers" //TODO : remove this in production
})
public class WebMVCConfiguration
        extends WebMvcConfigurationSupport {
    private final static Logger LOG = LoggerFactory.getLogger(WebMVCConfiguration.class);

    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        //I18N messages are stored in a bundle in the classpath
        messageSource.setBasename("i18n/messages");

        // # -1 : never reload, 0 always reload
        messageSource.setCacheSeconds(-1);

        return messageSource;
    }

    @Bean
    public TemplateResolver templateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("XHTML");
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    @Autowired
    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
        final SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver);
        engine.addDialect(new LayoutDialect());
        return engine;
    }

    @Bean
    @Autowired
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }

    @Override
    protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        //We add some resource handlers for our static resources :
        // - CSS and JS in resources
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }
}
