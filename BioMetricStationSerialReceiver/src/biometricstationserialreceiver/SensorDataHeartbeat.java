/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricstationserialreceiver;

/**
 *
 * @author jopbo_000
 */
public class SensorDataHeartbeat {
    
    private double heartbeat;
    
    public SensorDataHeartbeat(double heartbeat){
        
        this.heartbeat = heartbeat;
    }
    
    public double getHeartbeat(){
        return heartbeat;
    }
    
}
