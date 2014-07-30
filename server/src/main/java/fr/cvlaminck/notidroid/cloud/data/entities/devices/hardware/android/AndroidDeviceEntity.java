package fr.cvlaminck.notidroid.cloud.data.entities.devices.hardware.android;

import fr.cvlaminck.notidroid.cloud.data.annotations.MergeIndex;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.hardware.DeviceEntity;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * DeviceEntity specific to a device that runs with an android operating system.
 * Most of information contained in this class come from the android.os.Build class
 * of the device. Some information can come from other classes.
 * <p>
 *
 * @see http://developer.android.com/reference/android/os/Build.html
 */
@NodeEntity
public class AndroidDeviceEntity
        extends DeviceEntity {

    /**
     * User-visible brand of this device.
     * May be null or empty.
     */
    private String brand;

    /**
     * Commercial name of this device.
     */
    @MergeIndex
    @Indexed
    private String model;

    /**
     * Height of the screen in pixels
     */
    private int screenHeightInPixels;

    /**
     * With of the screen in pixels
     */
    private int screenWidthInPixels;

    /**
     * Pixel density of the screen in dpi.
     */
    private int screenDensity;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getScreenHeightInPixels() {
        return screenHeightInPixels;
    }

    public void setScreenHeightInPixels(int screenHeightInPixels) {
        this.screenHeightInPixels = screenHeightInPixels;
    }

    public int getScreenWidthInPixels() {
        return screenWidthInPixels;
    }

    public void setScreenWidthInPixels(int screenWidthInPixels) {
        this.screenWidthInPixels = screenWidthInPixels;
    }

    public float getScreenDensity() {
        return screenDensity;
    }

    public void setScreenDensity(int screenDensity) {
        this.screenDensity = screenDensity;
    }
}
