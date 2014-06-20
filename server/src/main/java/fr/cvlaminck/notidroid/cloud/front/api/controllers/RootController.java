package fr.cvlaminck.notidroid.cloud.front.api.controllers;

import fr.cvlaminck.notidroid.cloud.client.api.ServerInformation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that expose server information so client can determine if they
 * can use this instance.
 */
@RestController
@RequestMapping("/api")
public class RootController {

    @RequestMapping(method = RequestMethod.GET)
    public ServerInformation getServerInformation() {
        //TODO : fill with real server information, use config, properties, etc...
        final ServerInformation serverInformation = new ServerInformation();
        serverInformation.setPublicName("notidroid-dev");
        serverInformation.setVersion("0.1.0-SNAPSHOT");
        return serverInformation;
    }

}
