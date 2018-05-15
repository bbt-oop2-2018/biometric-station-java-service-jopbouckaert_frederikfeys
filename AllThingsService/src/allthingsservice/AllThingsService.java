/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allthingsservice;

/**
 *
 * @author jopbo
 */



import java.util.Random;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class AllThingsService implements MqttCallback{
    
    private MqttClient client;
    private String clientId;
    private String server = "api.allthingstalk.io";
    private String username = "maker:4UYCwFUzTJ14m0lqFzUdDz4FocJROwAN1uuxJSg0";
    private String password = "abcde";
    private String deviceId = "9UWOYRaKIGoONwO368qnAPGX";

    public AllThingsService() {
        setupMqttClient();
    }
    public void set(String name, double state) {
        String topic = "device/" + deviceId + "/asset/" + name + "/state";
        String payload = "{\"value\":" + "\"" + state +"\"}";
        publish(payload, topic);
    }
        
    private void setupMqttClient() {
        Random rand = new Random();
        this.clientId = "vives_domotics_" + rand.nextInt();
        
        MqttConnectOptions connectionOptions = new MqttConnectOptions();
        connectionOptions.setUserName(username);
        connectionOptions.setPassword(password.toCharArray());
      
        try {
            client = new MqttClient("tcp://" + server + ":1883", clientId);
            client.connect(connectionOptions);
            client.setCallback(this);
            System.out.println("Connected to MQTT broker");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    
    private void publish(String payload, String topic) {
        try {
            MqttMessage message = new MqttMessage();
            message.setPayload(payload.getBytes());
            client.publish(topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Connection with MQTT server is lost");
    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken token) { }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // System.out.println("Message arrived: " + message + " @" + topic);
        // String payload = message.toString();
    }
}
