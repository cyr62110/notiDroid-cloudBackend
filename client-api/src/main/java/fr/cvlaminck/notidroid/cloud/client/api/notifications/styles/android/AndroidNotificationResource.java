package fr.cvlaminck.notidroid.cloud.client.api.notifications.styles.android;

import fr.cvlaminck.notidroid.cloud.client.api.medias.MediaResource;
import fr.cvlaminck.notidroid.cloud.client.api.notifications.NotificationResource;

/**
 * TODO : comment here
 * This class define all the properties of a Android notification that are available
 * on all notification, included those that are not build using the Notification.Builder
 * helper class.
 *
 * @since 0.3
 */
public class AndroidNotificationResource
    extends NotificationResource {

    /**
     * Specifies which values should be taken from the defaults.
     *
     * @since 0.3
     */
    private int defaults;

    /**
     * @since 0.3
     */
    private int flags;

    /**
     * The icon in the status bar.
     *
     * @since 0.3
     * TODO : Not supported
     */
    //private MediaResource icon;

    /**
     * If the icon in the status bar is to have more than one level, you can set this.
     *
     * @since 0.3
     * //TODO : Not supported
     */
    //private int iconLevel;

    /**
     * The bitmap that may escape the bounds of the panel and bar.
     *
     * @since 0.3
     * TODO : Not supported
     */
    //private MediaResource largeIcon;

    /**
     * The color of the led.
     *
     * @since 0.3
     */
    private int ledARGB;

    /**
     * The number of milliseconds for the LED to be off while it's flashing.
     *
     * @since 0.3
     */
    private int ledOffMs;

    /**
     * The number of milliseconds for the LED to be on while it's flashing.
     *
     * @since 0.3
     */
    private int ledOnMs;

    /**
     * The number of events that this notification represents.
     *
     * @since 0.3
     */
    private int number;

    /**
     * Relative priority for this notification.
     *
     * @since 0.3
     */
    private int priority;

    /**
     * Text to scroll across the screen when this item is added to the status bar on large and smaller devices.
     *
     * @since 0.3
     */
    private String tickerText;

    /**
     * The pattern with which to vibrate.
     *
     * @since 0.3
     */
    private long[] vibrate;


}
