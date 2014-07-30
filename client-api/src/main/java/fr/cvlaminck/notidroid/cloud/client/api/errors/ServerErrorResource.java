package fr.cvlaminck.notidroid.cloud.client.api.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.Collections;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Resource that is send as response body if the server encounters
 * an error while processing the request. This resource contains
 * more information about the error that the server has encountered
 * to help third-party developers to understand what happen.
 * <p>
 * It may also include the full stack trace if the server is configured
 * to output it.
 *
 * @since 0.1
 */
@JsonInclude(NON_NULL)
public class ServerErrorResource {

    /**
     * Version of the API that the server is currently using to communicate
     * with clients. All API version are not compatible, using a older or newer
     * API version may be the cause of the error.
     *
     * @since 0.1
     */
    private int apiVersion = 0;

    /**
     * HTTP method that have been used in the request that
     * caused this error.
     *
     * @since 0.1
     */
    private String httpMethod = null;

    /**
     * Url pointed in the request that caused this error.
     *
     * @since 0.1
     */
    private String endpoint = null;

    /**
     * A message explaining the underlying cause of the error.
     *
     * @since 0.1
     */
    private String message = null;

    /**
     * Stack trace of the error.
     * May be null.
     *
     * @since 0.1
     */
    private String stackTrace = null;

    /**
     * More information about the causes of the error. For ex. if you
     * have sent a malformed resource, this field will contains the list
     * of fields that does not match the API requirements.
     * May be empty.
     */
    private Collection<String> detailedCauses = null;

    public ServerErrorResource() {
        this.detailedCauses = Collections.emptyList();
    }

    public int getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public Collection<String> getDetailedCauses() {
        return detailedCauses;
    }

    public void setDetailedCauses(Collection<String> detailedCauses) {
        if (detailedCauses == null)
            this.detailedCauses = Collections.emptyList();
        else
            this.detailedCauses = detailedCauses;
    }
}
