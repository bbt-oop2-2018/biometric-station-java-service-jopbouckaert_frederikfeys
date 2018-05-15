package biometricstationmqttdatatranslator;

/**
 *
 * @author jopbo_000
 */
public class SensorDataAccelero {
    
    private double xAccelero;
    private double yAccelero;
    private double zAccelero;
    private String dateTime;
    
    public SensorDataAccelero(double xAccelero,double yAccelero,double zAccelero,String dateTime){
        this.xAccelero=xAccelero;
        this.yAccelero=yAccelero;
        this.zAccelero=zAccelero;
        this.dateTime = dateTime;
    }
    
    public double getXAccelero(){
        return xAccelero;
    }
    
    public double getYAccelero(){
        return yAccelero;
    }
        
    public double getZAccelero(){
        return zAccelero;
    }
    public String getDateTime(){
        return dateTime;
    }
}
