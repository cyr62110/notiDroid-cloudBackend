package fr.cvlaminck.notidroid.cloud.client.api.push;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base representation of messages that are transferred between the server and the clients or between clients
 * through the message broker.
 *
 * Message can only be sent by client installed on user devices (desktop, mobile, ...). Web application
 * can receive messages from the broker but cannot send them. Also all clients connected to the broker
 * using user credentials will receive all messages.
 *
 * Those message are the body of JMS or MQTT messages sent to/received from the broker. There are encoded
 * in BSON.
 *
 * @since 0.2
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(EventPushMessage.class),
        @JsonSubTypes.Type(TextPushMessage.class),
})
public abstract class PushMessage {

    /**
     * Id used if the message is sent by the cloud backend
     * instead of an user device.
     */
    public static final long FROM_CLOUD_BACKEND = 0;

    /**
     * Id used as application id for all notidroid clients.
     * Notidroid client may be mobile, desktop or
     * web application.
     */
    public static final long NOTIDROID_CLIENT_ID = 0;

    /**
     * Id of the device that have sent the message.
     * 0 if the message is sent by the cloudBackend.
     *
     * @since 0.2
     */
    private long from;

    /**
     * Id of devices that should handle this message.
     * If empty, we consider that all device connected to the message broker
     * should handle the message.
     *
     * @since 0.2
     */
    private long[] to = new long[]{};

    /**
     * Id of the application that has sent this message.
     * 0 is reserved to notidroid.
     *
     * @since 0.2
     */
    private long appId;

    protected PushMessage() {
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long[] getTo() {
        return to;
    }

    public void setTo(long[] to) {
        this.to = (to != null) ? to : new long[]{};
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
