package fr.cvlaminck.notidroid.cloud.front.client.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/public/hello")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String hello() {
        return "Hello guys";
    }

}
