package fr.cvlaminck.notidroid.cloud.client.api.notifications;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.cvlaminck.notidroid.cloud.client.api.applications.ApplicationResource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Base class of all notification resources. Each style of notification must have its
 * own class that inherits this class.
 *
 * @since 0.3
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "style")
@JsonSubTypes({

})
public abstract class NotificationResource {

    /**
     * Id of the notification. Can be used to retrieve the media associated to
     * this notification.
     *
     * @since 0.3
     */
    @Null
    private String id;

    /**
     * Id of the device on which that notification has been 'posted'.
     *
     * @since 0.3
     */
    private long deviceId;

    /**
     * Representation of the application that has posted the notification on the
     * device.
     *
     * @since 0.3
     */
    @NotNull
    private ApplicationResource application;

    /**
     * When the notification has been posted.
     *
     */
    private long when;

    //TODO : icon

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public ApplicationResource getApplication() {
        return application;
    }

    public void setApplication(ApplicationResource application) {
        this.application = application;
    }

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }
}
