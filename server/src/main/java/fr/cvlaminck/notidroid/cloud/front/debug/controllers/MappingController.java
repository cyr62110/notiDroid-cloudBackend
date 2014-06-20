package fr.cvlaminck.notidroid.cloud.front.debug.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 *
 */
@RestController
@RequestMapping("/debug/mappings")
public class MappingController {

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping(method = RequestMethod.GET)
    public void getMappings() {
        Object handler = requestMappingHandlerMapping.getHandlerMethods();
        return;
    }
}
