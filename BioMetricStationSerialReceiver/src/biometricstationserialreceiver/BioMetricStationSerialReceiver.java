///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package biometricstationserialreceiver;
//
///**
// *
// * @author jopbo_000
// */
//public class BioMetricStationSerialReceiver {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
//    
//}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricstationserialreceiver;

import biometricstationservice.MqttBiometricStationService;
import com.google.gson.Gson;

/**
 *
 * @author nicod
 */
public class BioMetricStationSerialReceiver {

    public static void main(String[] args) throws Exception {
        
        MqttBiometricStationService biometricStationService = new MqttBiometricStationService("jopfrederik", "jopfrederik");
        Gson gson = new Gson();
        BioMetricStationStringParser parsedData = new BioMetricStationStringParser();
        
//        String data;
        // First create an object of SerialLineReceiver using the non-default constructor
        // 0 or 1 = index of com port (not the COM number from windows!)
        // 9600 = baudrate
        // false = debugging, set to true to see more messages in the console
        SerialLineReceiver receiver = new SerialLineReceiver(0, 115200, false);
        
        // To receive data from the serial port device you need to register a listener.
        // This is an instance of SerialPortLineListener that has a method serialLineEvent().
        // This method is called from inside SerialLineReceiver every time the serial port
        // received a data stream that contains a newline (\n).
        // The data is past using an object of type SerialData. You can access the data as a String
        // or as an array of bytes.
        receiver.setLineListener(new SerialPortLineListener() {
            @Override
            public void serialLineEvent(SerialData data) {
                SensorData sensordata;
                SensorDataTemperature sensordatatemperature;
                SensorDataHeartbeat sensordataheartbeat;
                SensorDataAccelero sensordataaccelero;

                System.out.println("Received data from the serial port: " + data.getDataAsString());
                sensordata = parsedData.parse(data.getDataAsString());
                if (!(sensordata == null)){
                sensordatatemperature = new SensorDataTemperature(sensordata.getTemperature());
                sensordataheartbeat = new SensorDataHeartbeat(sensordata.getHeartBeat());
                sensordataaccelero = new SensorDataAccelero(sensordata.getXAcellero(),sensordata.getYAcellero(),sensordata.getZAcellero());
                
                String temperatureJson = gson.toJson(sensordatatemperature);
                String heartbeaetJson = gson.toJson(sensordataheartbeat);
                String acceleroJson = gson.toJson(sensordataaccelero);
                biometricStationService.switchChannel("temperature");
                biometricStationService.sendMqttData(temperatureJson);
                System.out.println("Sent on MQTT: " + temperatureJson);
                biometricStationService.switchChannel("heartbeat");
                biometricStationService.sendMqttData(heartbeaetJson);
                System.out.println("Sent on MQTT: " + heartbeaetJson);
                biometricStationService.switchChannel("accelero");
                biometricStationService.sendMqttData(acceleroJson);
                System.out.println("Sent on MQTT: " + acceleroJson); 
                }
                
            }
        });
    }
}

