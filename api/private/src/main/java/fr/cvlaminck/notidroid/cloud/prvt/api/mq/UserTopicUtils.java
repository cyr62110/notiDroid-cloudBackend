package fr.cvlaminck.notidroid.cloud.prvt.api.mq;

import org.apache.commons.codec.binary.Base64;

/**
 * TODO : a little description here
 *
 * @since 0.2
 */
public class UserTopicUtils {

    public final static String USER_TOPIC_PREFIX = "users.";

    private static final Base64 base64 = new Base64(true);

    /**
     * Return the name of the topic reserved to the provided user.
     * Message send to this topic will be forwarded to all notidroid client
     * application that are connected to the message broker using this user credentials.
     * Clients are mobile app, web app, desktop app, etc...
     *
     * @param userEmailAddress User email address
     * @return Name of the topic reserved to the user.
     * @since 0.2
     */
    public static String getUserTopic(String userEmailAddress) {
        final String base64EncodedEmailAddress = base64.encodeAsString(userEmailAddress.getBytes());
        return USER_TOPIC_PREFIX + base64EncodedEmailAddress;
    }

    /**
     * Return the name of the topic prefixed by 'topic://'.
     *
     * @param userEmailAddress User email address
     * @return Name of the topic
     * @since 0.2
     */
    public static String getUserTopicFullName(String userEmailAddress) {
        return "topic://" + getUserTopic(userEmailAddress);
    }

}
