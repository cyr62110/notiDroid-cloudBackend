package fr.cvlaminck.notidroid.cloud.front.api.controllers;

import fr.cvlaminck.notidroid.cloud.client.api.servers.ServerInformationResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that exposes server information. Those information include the version of
 * notidroid cloud backend, which version of the client-api are supported, etc...
 */
@RestController
@RequestMapping("/api/info")
public class ServerInformationController {

    @RequestMapping(method = RequestMethod.GET)
    public ServerInformationResource getServerInformation() {
        //TODO : fill with real server information, use config, properties, etc...
        final ServerInformationResource serverInformation = new ServerInformationResource();
        serverInformation.setPublicName("notidroid-dev");
        serverInformation.setVersion("0.1.0-SNAPSHOT");
        return serverInformation;
    }

}
