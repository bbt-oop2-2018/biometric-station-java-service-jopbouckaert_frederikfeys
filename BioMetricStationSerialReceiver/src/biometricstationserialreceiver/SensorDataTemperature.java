package biometricstationserialreceiver;



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
