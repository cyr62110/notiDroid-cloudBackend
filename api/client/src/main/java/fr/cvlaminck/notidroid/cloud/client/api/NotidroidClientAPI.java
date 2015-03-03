package fr.cvlaminck.notidroid.cloud.client.api;

/**
 * Utility class giving some information about this Java implementation of the notidroid client API.
 * Those information will help you to determine if the application using this implementation can communicate
 * with a given server.
 * <p>
 * This library is the one used by the server to deserialize request from clients.
 * It may also be used by client applications to communicate with the server
 * and avoid mistakes in the API implementation.
 *
 * @since 0.1
 */
public abstract class NotidroidClientAPI {

    private NotidroidClientAPI() {
    }

    /**
     * Return the current version of the API that is implemented in this
     * library.
     *
     * @since 0.1
     */
    public static int getAPIVersion() {
        return 1;
    }


}
