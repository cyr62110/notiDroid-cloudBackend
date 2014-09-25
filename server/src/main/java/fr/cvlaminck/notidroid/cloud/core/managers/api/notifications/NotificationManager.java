package fr.cvlaminck.notidroid.cloud.core.managers.api.notifications;

import fr.cvlaminck.notidroid.cloud.client.api.notifications.NotificationResource;

/**
 *
 */
public interface NotificationManager {

    /**
     * TODO
     *
     * @param deviceId Device on which the notification has been posted
     * @param notification Notification that has been posted
     */
    public void onNotificationPosted(long deviceId, NotificationResource notification);

}
