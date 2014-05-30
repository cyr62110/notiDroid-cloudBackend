package fr.cvlaminck.notidroid.cloud.front.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String hello() {
        return "Hello World";
    }

}
