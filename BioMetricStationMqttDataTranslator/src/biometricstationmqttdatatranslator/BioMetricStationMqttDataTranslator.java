/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricstationmqttdatatranslator;

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
        String data;
        
        
        do{
        SensorData sensordata = new SensorData(mqttStringGenerator.getTemperature(),
                                    mqttStringGenerator.getHeartbeat(),
                                    mqttStringGenerator.getXAccelero(),
                                    mqttStringGenerator.getYAccelero(),
                                    mqttStringGenerator.getZAccelero());
        
        String DataJson = gson.toJson(sensordata);
        mqttStringGenerator.sendMqttData(DataJson);
        System.out.println(DataJson);
        Thread.sleep(300);
        }while(true);
    }
    
}