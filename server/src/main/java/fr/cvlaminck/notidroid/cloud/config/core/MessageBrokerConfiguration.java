package fr.cvlaminck.notidroid.cloud.config.core;

import fr.cvlaminck.notidroid.cloud.config.runtime.ActiveMQRuntimeConfiguration;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * Configuration of the message broker used as base of our push-notification
 * framework.
 */
@Configuration
public class MessageBrokerConfiguration {

    /**
     * Connection factory for creating connection with the ActiveMQ message broker.
     * The URL of the broker is retrieved from the configuration file.
     */
    @Bean
    @Autowired
    public ActiveMQConnectionFactory activeMQConnectionFactory(ActiveMQRuntimeConfiguration runtimeConfiguration) {
        final ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(runtimeConfiguration.getConnectorUrl());
        activeMQConnectionFactory.setUserName(runtimeConfiguration.getUsername());
        activeMQConnectionFactory.setPassword(runtimeConfiguration.getPassword());
        return activeMQConnectionFactory;
    }

    /**
     * Connection pool to reduce the overhead of creating connection on the fly.
     * The number of connection cached is retrieved for the configuration file.
     */
    @Bean
    @Autowired
    public CachingConnectionFactory cachedConnectionFactory(ActiveMQRuntimeConfiguration runtimeConfiguration,
                                                            ActiveMQConnectionFactory connectionFactory) {
        final CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
        cachingConnectionFactory.setSessionCacheSize(runtimeConfiguration.getConnectionPoolSize());
        return cachingConnectionFactory;
    }

    @Bean
    @Autowired
    public JmsTemplate jmsTemplate(CachingConnectionFactory connectionFactory) {
        final JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(true); //We publish to topic instead of queue
        return jmsTemplate;
    }

}
