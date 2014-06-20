package fr.cvlaminck.notidroid.cloud.front.debug.controllers;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/debug/beans")
public class BeanController implements ApplicationContextAware {

    private ApplicationContext applicationContext = null;

    @RequestMapping(method = RequestMethod.GET)
    public void getBeans() {
        Object mapping = applicationContext.getBean("mappingController");
        return;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
