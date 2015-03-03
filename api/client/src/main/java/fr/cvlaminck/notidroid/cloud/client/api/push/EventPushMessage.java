package fr.cvlaminck.notidroid.cloud.client.api.push;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fr.cvlaminck.notidroid.cloud.client.api.push.events.Event;

/**
 * Push message sent by a device or the server to notify all other user devices that an event has
 * occurred. For ex. a device has forwarded a new notification to the server.
 *
 * @since 0.2
 */
@JsonTypeName(value = EventPushMessage.TYPE)
public class EventPushMessage
    extends PushMessage {
    public static final String TYPE = "evt";

    /**
     * Content of the message
     *
     * @since 0.2
     */
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
