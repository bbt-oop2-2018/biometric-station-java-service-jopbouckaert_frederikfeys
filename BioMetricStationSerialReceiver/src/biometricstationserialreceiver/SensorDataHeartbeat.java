package biometricstationserialreceiver;
/**
 *
 * @author jopbo_000
 */
public class SensorDataHeartbeat {
    
    private double heartbeat;
    private String dateTime;
    
    public SensorDataHeartbeat(double heartbeat,String dateTime){
     
        this.heartbeat = heartbeat;
        this.dateTime = dateTime;
    }
    
    public double getHeartbeat(){
        return heartbeat;
    }
    
    public String getDateTime(){
        return dateTime;
    }
    
}
