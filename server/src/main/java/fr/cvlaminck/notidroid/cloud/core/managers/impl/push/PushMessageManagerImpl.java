package fr.cvlaminck.notidroid.cloud.core.managers.impl.push;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.undercouch.bson4jackson.BsonFactory;
import fr.cvlaminck.notidroid.cloud.client.api.push.EventPushMessage;
import fr.cvlaminck.notidroid.cloud.client.api.push.PushMessage;
import fr.cvlaminck.notidroid.cloud.client.api.push.TextPushMessage;
import fr.cvlaminck.notidroid.cloud.client.api.push.events.Event;
import fr.cvlaminck.notidroid.cloud.core.managers.api.push.PushMessageManager;
import fr.cvlaminck.notidroid.cloud.data.entities.applications.ApplicationEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.hardware.DeviceEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import fr.cvlaminck.notidroid.cloud.prvt.api.mq.UserTopicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PushMessageManagerImpl
    implements PushMessageManager {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Jackson ObjectMapper converting the message into a BSON object so
     * we can include it into a Jms ByteArrayMessage
     */
    private ObjectMapper objectMapper = null;

    @PostConstruct
    public void init() {
        objectMapper = new ObjectMapper(new BsonFactory());
    }

    @Override
    public void pushMessageTo(UserEntity user, PushMessage pushMessage) {
        try {
            final String topic = getUserSpecificTopic(user);
            final byte[] message = objectMapper.writeValueAsBytes(pushMessage);

            jmsTemplate.convertAndSend(topic, message);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Cannot convert the message using Jackson", e);
        }
    }

    @Override
    public PushMessage createTextMessage(ApplicationEntity app, DeviceEntity[] targetedDevices, String text) {
        final TextPushMessage message = new TextPushMessage();
        createMessage(message, app, targetedDevices);
        message.setText(text);
        return message;
    }

    @Override
    public PushMessage createEventMessage(ApplicationEntity app, DeviceEntity[] targetedDevices, Event event) {
        final EventPushMessage message = new EventPushMessage();
        createMessage(message, app, targetedDevices);
        message.setEvent(event);
        return message;
    }

    /**
     * Return the topic reserved to clients connected with provider user credentials.
     */
    private String getUserSpecificTopic(UserEntity user) {
        if(user.getEmail() == null || user.getEmail().length() == 0)
            throw new IllegalArgumentException("User with id " + user.getId() + " does not have any email address");
        return UserTopicUtils.getUserTopic(user.getEmail());
    }

    private void createMessage(PushMessage pushMessage, ApplicationEntity app, DeviceEntity[] targetedDevices) {
        pushMessage.setAppId((app != null) ? app.getId() : PushMessage.NOTIDROID_CLIENT_ID);
        final long[] targetedDeviceIds;
        if(targetedDevices != null) {
            targetedDeviceIds = new long[targetedDevices.length];
            int i = 0;
            for(DeviceEntity device : targetedDevices)
                targetedDeviceIds[i++] = device.getId();
        } else
            targetedDeviceIds = new long[]{};
        pushMessage.setTo(targetedDeviceIds);
        pushMessage.setFrom(PushMessage.FROM_CLOUD_BACKEND);
    }

}
