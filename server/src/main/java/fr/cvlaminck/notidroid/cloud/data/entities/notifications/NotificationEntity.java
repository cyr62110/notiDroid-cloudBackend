package fr.cvlaminck.notidroid.cloud.data.entities.notifications;

import fr.cvlaminck.notidroid.cloud.data.entities.notifications.actions.ActionEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

/**
 *
 */
@Document(collection = "notifications")
public class NotificationEntity {

    @Id
    private String id;

    /**
     * Id of the device on which this notification has been 'posted'
     */
    private long deviceId;

    /**
     * Id of the application that has 'posted' this notification
     * on the user device
     */
    private long applicationId;

    /**
     * When this notification has been 'posted'. Timestamp.
     */
    private long when;

    /**
     * Actions that can be executed from this notification.
     */
    private Collection<ActionEntity> actions = null;


    //Getters and setters

    public String getId() {
        return id;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }

    public Collection<ActionEntity> getActions() {
        return actions;
    }

    public void setActions(Collection<ActionEntity> actions) {
        this.actions = actions;
    }
}
