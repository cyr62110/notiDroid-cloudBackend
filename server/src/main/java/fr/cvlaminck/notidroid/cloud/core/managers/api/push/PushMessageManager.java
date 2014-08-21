package fr.cvlaminck.notidroid.cloud.core.managers.api.push;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.cvlaminck.notidroid.cloud.client.api.push.PushMessage;
import fr.cvlaminck.notidroid.cloud.client.api.push.events.Event;
import fr.cvlaminck.notidroid.cloud.data.entities.applications.ApplicationEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.hardware.DeviceEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;

import java.util.Collection;

/**
 * Manager allowing to push message to connected user clients using the message broker.
 * Clients may be mobile, web, desktop apps.
 */
public interface PushMessageManager {

    /**
     * Push a message to the topic reserved to the provided user.
     * This function will not check if targeted devices are owned by the selected device
     * nor if the application actually exists so you should
     * use create***Message functions to create the message before sending
     * it with this function.
     *
     * @param user User that should receive the message on all its connected clients.
     * @param pushMessage Message that will be sent.
     */
    void pushMessageTo(UserEntity user, PushMessage pushMessage);

    /**
     * Create a message containing a text.
     * @param app Application that must handle to the message, null if the message is for notidroid clients app.
     * @param targetedDevices Devices that must handle the message after they received it, null or empty means all connected devices.
     * @param text the text that should be sent.
     */
    PushMessage createTextMessage(ApplicationEntity app, DeviceEntity[] targetedDevices, String text);

    /**
     * Create a message containing an event.
     *  @param app Application that must handle to the message, null if the message is for notidroid clients app.
     * @param targetedDevices Devices that must handle the message after they received it, null or empty means all connected devices.
     * @param event the event that should be sent.
     */
    PushMessage createEventMessage(ApplicationEntity app, DeviceEntity[] targetedDevices, Event event);

}
