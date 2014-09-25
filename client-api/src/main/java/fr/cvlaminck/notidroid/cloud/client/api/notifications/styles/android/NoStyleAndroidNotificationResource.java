package fr.cvlaminck.notidroid.cloud.client.api.notifications.styles.android;

import fr.cvlaminck.notidroid.cloud.client.api.medias.MediaResource;

/**
 * Resource describing a notification created using the Notification.Builder without
 * using a style or a custom view. Most of applications use this kind of notification.
 *
 * @since 0.3
 */
public class NoStyleAndroidNotificationResource {

    /**
     * This is a small piece of additional text as supplied to setContentInfo(CharSequence).
     *
     * @since 0.3
     */
    private String infoText;

    /**
     * This is a bitmap to be used instead of the small icon when showing the notification payload,
     * as supplied to setLargeIcon(android.graphics.Bitmap).
     *
     * @since 0.3
     */
    private MediaResource largeIcon;

    /**
     * This is the progress value supplied to setProgress(int, int, boolean).
     *
     * @since 0.3
     */
    private int progress;

    /**
     * Whether the progress bar is indeterminate, supplied to setProgress(int, int, boolean)
     *
     * @since 0.3
     */
    private boolean progressIndeterminate;

    /**
     * This is the maximum value supplied to setProgress(int, int, boolean)
     *
     * @since 0.3
     */
    private int progressMax;

    /**
     * Whether when should be shown as a count-up timer (specifically a Chronometer) instead of a timestamp, as supplied to setUsesChronometer(boolean).
     *
     * @since 0.3
     */
    private boolean showChronometer;

    /**
     * Whether when should be shown, as supplied to setShowWhen(boolean).
     *
     * @since 0.3
     */
    private boolean showWhen;

    /**
     * This is the resource ID of the notification's main small icon, as supplied to setSmallIcon(int).
     * TODO
     * @since 0.3
     */
    private MediaResource smallIcon;

    /**
     * This is a third line of text, as supplied to setSubText(CharSequence).
     *
     * @since 0.3
     */
    private String subText;

    /**
     * This is a line of summary information intended to be shown alongside expanded notifications,
     * as supplied to (e.g.) setSummaryText(CharSequence).
     *
     * @since 0.3
     */
    private String summaryText;

    /**
     * This is the main text payload, as supplied to setContentText(CharSequence).
     *
     * @since 0.3
     */
    private String text;

    /**
     * This is the title of the notification, as supplied to setContentTitle(CharSequence).
     *
     * @since 0.3
     */
    private String title;

}
