package biometricstationmqttdatatranslator;

import java.util.ArrayList;

/**
 *
 * @author jopbo_000
 */
public class SensorData {
    private double heartBeat;
    private double temperature;
    private double xAccelero;
    private double yAccelero;
    private double zAccelero;
    
    public SensorData(double temperature , double heartBeat , double xAccelero , double yAccelero , double zAccelero){
        
        this.temperature = temperature;
        this.heartBeat = heartBeat;
        this.xAccelero = xAccelero;
        this.yAccelero = yAccelero;
        this.zAccelero = zAccelero;
    }
    
    public double getTemperature() {
        return temperature;
    }
    
    public double getHeartBeat(){
        return heartBeat;
    }
    
    public double getXAcellero(){
        return xAccelero;
    }
        
    public double getYAcellero(){
        return yAccelero;
    }
    
    public double getZAcellero(){
        return zAccelero;
    }
    
    @Override
    public String toString(){
        return "Temperature: " + temperature + " | "+"Heartbeat: " + heartBeat + " | " + "X: " + xAccelero + " | " + "Y:" + yAccelero + " | " + "Z:" + zAccelero;
    }
}
