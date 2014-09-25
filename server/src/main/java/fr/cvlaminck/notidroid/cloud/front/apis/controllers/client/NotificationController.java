package fr.cvlaminck.notidroid.cloud.front.apis.controllers.client;

import fr.cvlaminck.notidroid.cloud.client.api.notifications.NotificationResource;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping(value = "/users/{userRef}")
public class NotificationController {


    /**
     *
     * @param userRef
     * @param deviceRef
     * @param notification
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/devices/{deviceRef}/notifications",
            consumes = "application/json", produces = "application/json")
    public long onNotificationPosted(@PathVariable String userRef, @PathVariable String deviceRef, @RequestBody NotificationResource notification) {
        return 0;
    }

}
