package fr.cvlaminck.notidroid.cloud.core.exceptions.devices;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fr.cvlaminck.notidroid.cloud.client.api.devices.UserDeviceResource;
import fr.cvlaminck.notidroid.cloud.core.exceptions.NotidroidException;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.hardware.DeviceEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.user.UserDeviceEntity;
import org.springframework.http.HttpStatus;

/**
 * Throw if there is no specific device manager available to handle
 * operations on a given type of device.
 */
public class NoSpecificDeviceManagerForTypeException
        extends NotidroidException {

    private static final String FORMATTED_MESSAGE = "Cannot handle devices of type '%s'. Server may be corrupted.";
    private static final String DEFAULT_MESSAGE = "Trying to execute an operation on an unsupported device type. Server may be corrupted.";

    public NoSpecificDeviceManagerForTypeException(Object device) {
        super(HttpStatus.INTERNAL_SERVER_ERROR,
                getMessage(device));
    }

    private static String getMessage(Object device) {
        if (device instanceof UserDeviceResource) {
            final JsonTypeName annotation = device.getClass().getAnnotation(JsonTypeName.class);
            return String.format(FORMATTED_MESSAGE, annotation.value());
        } else if (device instanceof DeviceEntity) {
            //TODO
            return DEFAULT_MESSAGE;
        } else if (device instanceof UserDeviceEntity) {
            //TODO
            return DEFAULT_MESSAGE;
        } else
            return DEFAULT_MESSAGE;
    }
}
