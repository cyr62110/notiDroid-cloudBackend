package fr.cvlaminck.notidroid.cloud.client.api.push;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Push message sent by a device or the server to notify all other user devices that an event has
 * occurred. For ex. a device has forwarded a new notification to the server.
 *
 * @since 0.2
 */
@JsonTypeName(value = EventPushMessage.TYPE_NAME)
public class EventPushMessage
    extends PushMessage {
    public static final String TYPE_NAME = "evt";


}
