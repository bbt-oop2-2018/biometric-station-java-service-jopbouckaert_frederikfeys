/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricstationmqttdatatranslator;

import biometricstationservice.MqttBiometricStationService;
import com.google.gson.Gson;

/**
 *
 * @author jopbo_000
 */
public class BioMetricStationMqttDataTranslator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Gson gson = new Gson();
        MqttStringGenerator mqttStringGenerator = new MqttStringGenerator();
        MqttBiometricStationService biometricStationService = new MqttBiometricStationService("jop", "jopfrederik");
        String data;

        do {
            
                SensorDataTemperature sensordatatemperature;
                SensorDataHeartbeat sensordataheartbeat;
                SensorDataAccelero sensordataaccelero;
            SensorData sensordata = new SensorData(mqttStringGenerator.getTemperature(),
                    mqttStringGenerator.getHeartbeat(),
                    mqttStringGenerator.getXAccelero(),
                    mqttStringGenerator.getYAccelero(),
                    mqttStringGenerator.getZAccelero());
            
                sensordatatemperature = new SensorDataTemperature(sensordata.getTemperature());
                sensordataheartbeat = new SensorDataHeartbeat(sensordata.getHeartBeat());
                sensordataaccelero = new SensorDataAccelero(sensordata.getXAcellero(),sensordata.getYAcellero(),sensordata.getZAcellero());
            
            String temperatureJson = gson.toJson(sensordatatemperature);
            String heartbeaetJson = gson.toJson(sensordataheartbeat);
            String acceleroJson = gson.toJson(sensordataaccelero);
            biometricStationService.switchChannel("temperature");
            biometricStationService.sendMqttData(temperatureJson);
            System.out.println("Sent on MQTT: " + temperatureJson);
            biometricStationService.switchChannel("heartbeat");
            biometricStationService.sendMqttData(heartbeaetJson);
            System.out.println("Sent on MQTT: " + heartbeaetJson);
            biometricStationService.switchChannel("accelero");
            biometricStationService.sendMqttData(acceleroJson);
            System.out.println("Sent on MQTT: " + acceleroJson);
            Thread.sleep(500);
        } while (true);
    }

}
