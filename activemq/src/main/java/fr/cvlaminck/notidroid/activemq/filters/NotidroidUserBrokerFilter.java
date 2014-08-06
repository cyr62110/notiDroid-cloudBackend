package fr.cvlaminck.notidroid.activemq.filters;

import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerFilter;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.region.Destination;
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

    public final static String USER_TOPICS_PREFIX = "users.";

    public final static String VIRTUAL_USER_TOPIC = USER_TOPICS_PREFIX + "me";

    public NotidroidUserBrokerFilter(Broker next) {
        super(next);
    }

    @Override
    public Destination addDestination(ConnectionContext context, ActiveMQDestination destination, boolean createIfTemporary) throws Exception {
        //Destination starting by ActiveMQ are ignored by the plugin
        if (destination.getPhysicalName().startsWith("ActiveMQ"))
            return super.addDestination(context, destination, createIfTemporary);

        //An user can only create its own topic. So we check that teh destination is the one reserved for our user.
        final String base64EncodedEmailAddress = null;
        if(!destination.isTopic() && !(USER_TOPICS_PREFIX + base64EncodedEmailAddress).equals(destination.getPhysicalName()))
            throw new IllegalStateException("Users are only allowed to pub/sub on the 'users.me' topic. Please check your configuration.");

        return super.addDestination(context, destination, createIfTemporary);
    }

    @Override
    public void addProducer(ConnectionContext context, ProducerInfo info) throws Exception {
        super.addProducer(context, info);
    }

    @Override
    public Subscription addConsumer(ConnectionContext context, ConsumerInfo info) throws Exception {
        //User can only pub/sub on the virtual 'users.me' topic.
        if (!info.getDestination().isTopic() && !info.getDestination().getPhysicalName().equals(VIRTUAL_USER_TOPIC))
            throw new SecurityException("Users are only allowed to pub/sub on the 'users.me' topic.");

        //We change the destination to match the real name of the topic reserved to this user
        final String base64EncodedEmailAddress = null;
        ActiveMQDestination physicalDestination = new ActiveMQTopic(USER_TOPICS_PREFIX + base64EncodedEmailAddress);
        info.setDestination(physicalDestination);

        return super.addConsumer(context, info);
    }

}
