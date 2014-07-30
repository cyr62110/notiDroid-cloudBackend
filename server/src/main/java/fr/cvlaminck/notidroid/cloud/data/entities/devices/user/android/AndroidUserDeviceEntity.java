package fr.cvlaminck.notidroid.cloud.data.entities.devices.user.android;

import fr.cvlaminck.notidroid.cloud.data.entities.devices.user.UserDeviceEntity;
import org.springframework.data.neo4j.annotation.Indexed;

/**
 * TODO
 */
public class AndroidUserDeviceEntity
        extends UserDeviceEntity {

    /**
     * Hardware serial number.
     * This number may be used to find this user device instance
     * after the user has formatted/updated its device and associated its account.
     * May be null or empty
     */
    @Indexed
    private String serial;

    /**
     * API version supported by the device.
     */
    private int sdkInt;

    /**
     * Version of the operating system.
     */
    private String release;

}
