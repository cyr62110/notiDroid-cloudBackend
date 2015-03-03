package fr.cvlaminck.notidroid.cloud.data.entities.notifications.styles.android;

/**
 *
 */
public class NoStyleNotificationEntity
        extends AndroidNotificationEntity {

    /**
     * This is a small piece of additional text as supplied to setContentInfo(CharSequence).
     */
    private String infoText;

    /**
     * This is a bitmap to be used instead of the small icon when showing the notification payload,
     * as supplied to setLargeIcon(android.graphics.Bitmap).
     */
    //private MediaResource largeIcon;

    /**
     * This is the progress value supplied to setProgress(int, int, boolean).
     */
    private int progress;

    /**
     * Whether the progress bar is indeterminate, supplied to setProgress(int, int, boolean)
     */
    private boolean progressIndeterminate;

    /**
     * This is the maximum value supplied to setProgress(int, int, boolean)
     */
    private int progressMax;

    /**
     * Whether when should be shown as a count-up timer (specifically a Chronometer) instead of a timestamp, as supplied to setUsesChronometer(boolean).
     */
    private boolean showChronometer;

    /**
     * Whether when should be shown, as supplied to setShowWhen(boolean).
     */
    private boolean showWhen;

    /**
     * This is the resource ID of the notification's main small icon, as supplied to setSmallIcon(int).
     * TODO
     */
    //private MediaResource smallIcon;

    /**
     * This is a third line of text, as supplied to setSubText(CharSequence).
     */
    private String subText;

    /**
     * This is a line of summary information intended to be shown alongside expanded notifications,
     * as supplied to (e.g.) setSummaryText(CharSequence).
     */
    private String summaryText;

    /**
     * This is the main text payload, as supplied to setContentText(CharSequence).
     */
    private String text;

    /**
     * This is the title of the notification, as supplied to setContentTitle(CharSequence).
     */
    private String title;

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isProgressIndeterminate() {
        return progressIndeterminate;
    }

    public void setProgressIndeterminate(boolean progressIndeterminate) {
        this.progressIndeterminate = progressIndeterminate;
    }

    public int getProgressMax() {
        return progressMax;
    }

    public void setProgressMax(int progressMax) {
        this.progressMax = progressMax;
    }

    public boolean isShowChronometer() {
        return showChronometer;
    }

    public void setShowChronometer(boolean showChronometer) {
        this.showChronometer = showChronometer;
    }

    public boolean isShowWhen() {
        return showWhen;
    }

    public void setShowWhen(boolean showWhen) {
        this.showWhen = showWhen;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public String getSummaryText() {
        return summaryText;
    }

    public void setSummaryText(String summaryText) {
        this.summaryText = summaryText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
