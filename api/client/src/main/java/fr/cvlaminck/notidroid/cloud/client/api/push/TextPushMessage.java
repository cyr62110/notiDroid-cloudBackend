package fr.cvlaminck.notidroid.cloud.client.api.push;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Push message containing a generic text.
 * This kind of message can be used by third-party application to communicate
 * through the push notification service.
 *
 * @since 0.2
 */
@JsonTypeName(value = TextPushMessage.TYPE)
public class TextPushMessage
    extends PushMessage {
    public static final String TYPE = "text";

    /**
     * Content of the message.
     *
     * @since 0.2
     */
    private String text = null;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
