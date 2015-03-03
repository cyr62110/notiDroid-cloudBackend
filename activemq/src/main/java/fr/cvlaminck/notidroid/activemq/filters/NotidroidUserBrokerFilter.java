package fr.cvlaminck.notidroid.activemq.filters;

import fr.cvlaminck.notidroid.activemq.utils.ConnectionContextUtils;
import fr.cvlaminck.notidroid.cloud.prvt.api.mq.UserTopicUtils;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerFilter;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.command.ProducerInfo;

/**
 * This filter add some logic to handle user connection. It will not act on connection with the cloudBackend.
 * <p>
 * Every user has its own topic called 'users.{user's email encoded in base64Url}'. This topic is used by all notidroid clients
 * the user is connected to. To simplify the development of clients and made them independent of this internal implementation, client
 * app only pub/sub on a topic called 'users.me', this topic name is translated into the real topic name by this filter.
 * <p>
 * So this filter have those functions :
 * - Make sure that the client only pub/sub on the virtual 'users.me' destination
 * - Translate the virtual 'users.me' destination
 * - ...
 */
public class NotidroidUserBrokerFilter
        extends BrokerFilter {

    public final static String USER_TOPICS_PREFIX = UserTopicUtils.USER_TOPIC_PREFIX;

    public final static String VIRTUAL_USER_TOPIC = USER_TOPICS_PREFIX + "me";

    public NotidroidUserBrokerFilter(Broker next) {
        super(next);
    }

    @Override
    public void addProducer(ConnectionContext context, ProducerInfo info) throws Exception {
        //We check that the connection has been established by an user using a notidroid client application.
        //If not this filter will ignore this operation and forward to the next filter.
        if(!ConnectionContextUtils.isUser(context)) {
            super.addProducer(context, info);
            return;
        }

        /* if(!ConnectionContextUtils.canUserPublish(context))
            throw new SecurityException("Your are not allowed to publish a message."); */
        //When user connect their device using the MQTT protocol, the destination is null so we handle this.
        if(info.getDestination() == null) {
            super.addProducer(context, info);
            return;
        }

        //User can only pub/sub on the virtual 'users.me' topic.
        if (!info.getDestination().isTopic() && !info.getDestination().getPhysicalName().equals(VIRTUAL_USER_TOPIC))
            throw new SecurityException("Users are only allowed to pub/sub on the 'users.me' topic.");

        //We change the destination to match the real name of the topic reserved to this user
        info.setDestination(getUserTopic(context));

        super.addProducer(context, info);
    }

    @Override
    public Subscription addConsumer(ConnectionContext context, ConsumerInfo info) throws Exception {
        //We check that the connection has been established by an user using a notidroid client application.
        //If not this filter will ignore this operation and forward to the next filter.
        if(!ConnectionContextUtils.isUser(context))
            return super.addConsumer(context, info);

        //User can only pub/sub on the virtual 'users.me' topic.
        if (!info.getDestination().isTopic() && !info.getDestination().getPhysicalName().equals(VIRTUAL_USER_TOPIC))
            throw new SecurityException("Users are only allowed to pub/sub on the 'users.me' topic.");

        //We change the destination to match the real name of the topic reserved to this user
        info.setDestination(getUserTopic(context));

        return super.addConsumer(context, info);
    }

    private ActiveMQDestination getUserTopic(ConnectionContext context) {
        final String userEmailAddress = ConnectionContextUtils.userEmailAddress(context);
        return new ActiveMQTopic(UserTopicUtils.getUserTopic(userEmailAddress));
    }

}
