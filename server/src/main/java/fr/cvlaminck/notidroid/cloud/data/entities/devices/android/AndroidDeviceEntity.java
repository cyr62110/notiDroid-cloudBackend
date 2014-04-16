package fr.cvlaminck.notidroid.cloud.data.entities.devices.android;

import fr.cvlaminck.notidroid.cloud.data.entities.devices.DeviceEntity;

/**
 * All information that we can retrieve on a device from
 * the android.os.Build class.
 *
 * TODO: fill this class
 *
 * @see http://developer.android.com/reference/android/os/Build.html
 */
public class AndroidDeviceEntity extends DeviceEntity {

    public String model;

    @Override
    public String getDisplayName() {
        return model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}