package fr.cvlaminck.notidroid.cloud.data.entities.devices.hardware.ios;

import fr.cvlaminck.notidroid.cloud.data.entities.devices.hardware.DeviceEntity;

/**
 * Information of an iOS device collected through the UIDevice class.
 *
 * @see https://developer.apple.com/library/ios/documentation/uikit/reference/UIDevice_Class/Reference/UIDevice.html
 */
public class iOSDeviceEntity extends DeviceEntity {

    private String name;

    private String systemName;

    private String systemVersion;

    private String model;

    private String localizedModel;

    //TODO : Handle the userInterfaceIdiom

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocalizedModel() {
        return localizedModel;
    }

    public void setLocalizedModel(String localizedModel) {
        this.localizedModel = localizedModel;
    }
}
