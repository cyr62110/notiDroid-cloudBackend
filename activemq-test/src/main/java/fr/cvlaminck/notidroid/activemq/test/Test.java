package fr.cvlaminck.notidroid.activemq.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by cyr62110 on 30/07/14.
 */
public class Test {

    public static void main(String[] args) throws JMSException {
        final ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:8888");
        final Connection connection = connectionFactory.createConnection("active-mq", "password2");
        Session session = null;
        MessageProducer messageProducer = null;
        MessageConsumer messageConsumer = null;
        try {
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            final Topic testTopic =  session.createTopic("test");
            TextMessage message = session.createTextMessage();
            message.setText("Hello World");

            //messageProducer = session.createProducer(testTopic);
            //messageProducer.send(message);

            messageConsumer = session.createConsumer(testTopic);
            TextMessage recvTextMessage = (TextMessage) messageConsumer.receive();

            System.out.println(recvTextMessage.getText());

        } catch (JMSException ex) { throw ex; }
        finally {
            if(messageProducer != null)
                messageProducer.close();
            if(session != null)
                session.close();
            connection.close();
        }
    }

}
