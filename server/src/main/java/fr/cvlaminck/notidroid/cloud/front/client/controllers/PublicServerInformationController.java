package fr.cvlaminck.notidroid.cloud.front.client.controllers;

import fr.cvlaminck.notidroid.cloud.client.api.servers.ServerInformationResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that exposes public server information. Those information include the version of
 * notidroid cloud backend running on this server, which version of the client-api are supported, etc...
 * <p>
 * These endpoint is not protected and can be access by everyone to determine if he can
 * communicate with this server. It is one of the not so many public public endpoints exposed
 * by the notidroid cloud backend.
 */
@RestController
@RequestMapping("/api/client/public/info")
public class PublicServerInformationController {

    /**
     * @since 0.1
     */
    @RequestMapping(method = RequestMethod.GET)
    public ServerInformationResource getServerInformation() {
        //TODO : fill with real server information, use config, properties, etc...
        final ServerInformationResource serverInformation = new ServerInformationResource();
        serverInformation.setPublicName("notidroid-dev");
        serverInformation.setVersion("0.1.0-SNAPSHOT");
        serverInformation.setSupportedAPIVersion(new int[]{1});
        return serverInformation;
    }

}
