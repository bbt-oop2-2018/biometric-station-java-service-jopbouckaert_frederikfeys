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
    private String dateTime;
    
    public SensorDataTemperature(double temperature,String dateTime){
        this.temperature = temperature;
        this.dateTime = dateTime;
    }
    
    public double getTemperature(){
        return temperature;
    }
    public String getDateTime(){
        return dateTime;
    }
    
}
