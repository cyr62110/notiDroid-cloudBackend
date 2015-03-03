package fr.cvlaminck.notidroid.cloud.data.entities.notifications.styles.android;

import fr.cvlaminck.notidroid.cloud.data.entities.notifications.NotificationEntity;

/**
 *
 */
public class AndroidNotificationEntity
        extends NotificationEntity {

    /**
     * Specifies which values should be taken from the defaults.
     */
    private int defaults;

    private int flags;

    //TODO: private int icon;

    /**
     * If the icon in the status bar is to have more than one level, you can set this.
     */
    private int iconLevel;

    //private Bitmap largeIcon;

    /**
     * The color of the led.
     */
    private int ledARGB;

    /**
     * The number of milliseconds for the LED to be off while it's flashing.
     */
    private int ledOffMs;

    /**
     * The number of milliseconds for the LED to be on while it's flashing.
     */
    private int ledOnMs;

    /**
     * The number of events that this notification represents.
     */
    private int number;

    /**
     * Relative priority for this notification.
     */
    private int priority;

    /**
     * Text to scroll across the screen when this item is added to the status bar on large and smaller devices.
     */
    private String tickerText;

    /**
     * The pattern with which to vibrate.
     */
    private long[] vibrate;

    public int getDefaults() {
        return defaults;
    }

    public void setDefaults(int defaults) {
        this.defaults = defaults;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getIconLevel() {
        return iconLevel;
    }

    public void setIconLevel(int iconLevel) {
        this.iconLevel = iconLevel;
    }

    public int getLedARGB() {
        return ledARGB;
    }

    public void setLedARGB(int ledARGB) {
        this.ledARGB = ledARGB;
    }

    public int getLedOffMs() {
        return ledOffMs;
    }

    public void setLedOffMs(int ledOffMs) {
        this.ledOffMs = ledOffMs;
    }

    public int getLedOnMs() {
        return ledOnMs;
    }

    public void setLedOnMs(int ledOnMs) {
        this.ledOnMs = ledOnMs;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTickerText() {
        return tickerText;
    }

    public void setTickerText(String tickerText) {
        this.tickerText = tickerText;
    }

    public long[] getVibrate() {
        return vibrate;
    }

    public void setVibrate(long[] vibrate) {
        this.vibrate = vibrate;
    }
}
