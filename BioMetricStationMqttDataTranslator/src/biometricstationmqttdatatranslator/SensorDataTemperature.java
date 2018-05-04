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
public class SensorDataTemperature {
    
    private double temperature;
    
    public SensorDataTemperature(double temperature){
        this.temperature = temperature;
    }
    
    public double getTemperature(){
        return temperature;
    }
    
}
