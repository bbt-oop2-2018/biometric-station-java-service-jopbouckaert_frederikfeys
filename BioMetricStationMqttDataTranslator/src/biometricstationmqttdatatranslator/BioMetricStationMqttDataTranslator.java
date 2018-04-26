/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricstationmqttdatatranslator;

/**
 *
 * @author jopbo_000
 */
public class BioMetricStationMqttDataTranslator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        MqttStringGenerator mqttStringGenerator = new MqttStringGenerator();
        String data;
        
        do{
        data = mqttStringGenerator.MqttStringGenerator();
        mqttStringGenerator.sendMqttData(data);
        System.out.println(data);
        Thread.sleep(300);
        }while(true);
    }
    
}
