package fr.cvlaminck.notidroid.activemq.test;

import org.eclipse.paho.client.mqttv3.*;

import java.io.IOException;

/**
 *
 */
public class TestMQTT {

   public static void main(String[] args) throws MqttException, IOException {
       MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
       mqttConnectOptions.setCleanSession(true); //We do not want durable subscription
       mqttConnectOptions.setUserName("device-44");
       mqttConnectOptions.setPassword("97b26cfb-aa77-437c-9d8d-46b8b4deacbe".toCharArray());

       MqttClient mqttClient = new MqttClient("tcp://localhost:8989", MqttClient.generateClientId());
       mqttClient.connect(mqttConnectOptions);
       mqttClient.setCallback(mqttCallback);

       mqttClient.subscribe("users/me");

       System.in.read();
   }

    private static MqttCallback mqttCallback = new MqttCallback() {
        @Override
        public void connectionLost(Throwable cause) {
            cause.printStackTrace();
        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            System.out.println("Message received on topic : " + topic);
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {
            //TODO
        }
    };

}
