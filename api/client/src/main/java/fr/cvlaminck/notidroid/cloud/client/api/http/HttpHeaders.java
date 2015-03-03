package fr.cvlaminck.notidroid.cloud.client.api.http;

/**
 * Enumeration of all custom http headers used by notidroid.
 * Those headers are not part of the HTTP 1.1 standard.
 */
public final class HttpHeaders {

    /**
     * Id of the device making the request to the server.
     */
    public final static String X_DEVICE_ID = "X-Device-Id";

    private HttpHeaders() {
    }
}
