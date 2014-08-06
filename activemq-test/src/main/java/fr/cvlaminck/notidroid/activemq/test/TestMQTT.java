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
       mqttConnectOptions.setPassword("df852a36-bc40-4840-a00d-4bb3fb58b15d".toCharArray());

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
