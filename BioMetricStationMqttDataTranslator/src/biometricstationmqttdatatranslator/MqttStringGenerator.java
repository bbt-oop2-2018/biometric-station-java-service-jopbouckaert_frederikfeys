/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricstationmqttdatatranslator;

import biometricstationservice.MqttBiometricStationService;
import helpers.RangedNumberGenerator;

/**
 *
 * @author jopbo_000
 */
public class MqttStringGenerator {
    
    private MqttBiometricStationService biometricStationService;
    
    private final int MAXIMUM_TEMPERATURE = 30;
    private final int MINIMUM_TEMPERATURE = -10;
    private final int MINIMUM_HEARTBEAT = 1;
    private final int MAXIMUM_HEARTBEAT = 300;
    private final int MINIMUM_ACCELERO = -100;
    private final int MAXIMUM_ACCELERO = +100;
    private RangedNumberGenerator temperatureData = new RangedNumberGenerator(MINIMUM_TEMPERATURE,MAXIMUM_TEMPERATURE);
    private RangedNumberGenerator heartBeatData = new RangedNumberGenerator(MINIMUM_HEARTBEAT,MAXIMUM_HEARTBEAT);
    private RangedNumberGenerator xAccelero = new RangedNumberGenerator(MINIMUM_ACCELERO,MAXIMUM_ACCELERO);
    private RangedNumberGenerator yAccelero = new RangedNumberGenerator(MINIMUM_ACCELERO,MAXIMUM_ACCELERO);
    private RangedNumberGenerator zAccelero = new RangedNumberGenerator(MINIMUM_ACCELERO,MAXIMUM_ACCELERO);


    
    
    public MqttStringGenerator(){
        biometricStationService = new MqttBiometricStationService("jop", "test");
    }
    
    public String MqttStringGenerator(){
    return ("ABCDE" + temperatureData + "|" + heartBeatData + "XXX" + xAccelero + "YYY" + yAccelero + "ZZZ" + zAccelero + "EDCBA");
    }
    
    
    public double getTemperature(){
        return temperatureData.generate();
    }
    
    public double getHeartbeat(){
        return heartBeatData.generate();
    }
        
    public double getXAccelero(){
        return xAccelero.generate();
    }
            
    public double getYAccelero(){
        return yAccelero.generate();
    }
    
    public double getZAccelero(){
        return zAccelero.generate();
    }
    
    public void sendMqttData(String mqttData){
    biometricStationService.sendMqttData(mqttData);
        
    }
}
