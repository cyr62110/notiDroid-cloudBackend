package fr.cvlaminck.notidroid.cloud.client.api.devices;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Representation of an user device that runs android.
 *
 * @since 0.1
 */
@JsonTypeName(value = AndroidUserDeviceResource.TYPE_NAME)
public class AndroidUserDeviceResource
        extends UserDeviceResource {
    public final static String TYPE_NAME = "android";

    /**
     * User-visible brand of this device.
     * May be null or empty.
     */
    @JsonProperty(value = "android.brand")
    private String brand;

    /**
     * Commercial name of this device.
     * Must not be null nor empty.
     */
    @NotNull
    @Size(min = 1)
    @JsonProperty(value = "android.model")
    private String model;

    /**
     * Hardware serial number.
     * May be null or empty.
     */
    @JsonProperty(value = "android.serial")
    private String serial;

    /**
     * Version of the operating system
     */
    @NotNull
    @Size(min = 1)
    @JsonProperty(value = "android.release")
    private String release;

    /**
     * Version of the api supported by the device
     */
    @Min(value = 1)
    @JsonProperty(value = "android.sdkInt")
    private int sdkInt;

    /**
     * Height of the screen in pixels
     */
    @Min(value = 1)
    @JsonProperty(value = "android.screenHeightInPixels")
    private int screenHeightInPixels;

    /**
     * With of the screen in pixels
     */
    @Min(value = 1)
    @JsonProperty(value = "android.screenWidthInPixels")
    private int screenWidthInPixels;

    /**
     * Pixel density of the screen in dpi
     */
    @JsonProperty(value = "android.screenDensity")
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

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public int getSdkInt() {
        return sdkInt;
    }

    public void setSdkInt(int sdkInt) {
        this.sdkInt = sdkInt;
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

    public int getScreenDensity() {
        return screenDensity;
    }

    public void setScreenDensity(int screenDensity) {
        this.screenDensity = screenDensity;
    }
}
