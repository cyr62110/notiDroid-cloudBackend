package fr.cvlaminck.notidroid.cloud.client.api.devices;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.validation.constraints.Null;

/**
 * Representation of a device own by a user. You will find information
 * about the device that will never change (resolution, etc...) but
 * also information about the specific device that the user owns.
 * (OS version, etc...)
 * <p>
 * All user device representations should inherits from this base
 * class.
 *
 * @since 0.1
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "deviceType")
@JsonSubTypes({
        @JsonSubTypes.Type(AndroidUserDeviceResource.class)
})
public abstract class UserDeviceResource {

    /**
     * Identifier of this device.
     * Client must not fill this field when sending device information to the server.
     */
    @Null
    private Long id;

    /**
     * Name given by the user to the device.
     * May be null.
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
