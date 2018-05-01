///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package biometricstationmqttdatatranslator;
//
///**
// *
// * @author jopbo_000
// */
//public class BioMetricStationMqttDataTranslator {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws InterruptedException {
//        MqttStringGenerator mqttStringGenerator = new MqttStringGenerator();
//        String data;
//        
//        do{
//        data = mqttStringGenerator.MqttStringGenerator();
//        mqttStringGenerator.sendMqttData(data);
//        System.out.println(data);
//        Thread.sleep(300);
//        }while(true);
//    }
//    
//}

package biometricstationmqttdatatranslator;

import biometricstationmqttdatatranslator.BioMetricStationStringParser;
import biometricstationmqttdatatranslator.SensorData;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author jopbo_000
 */
public class BioMetricStationMqttDataTranslator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MqttStringGenerator mqttStringGenerator = new MqttStringGenerator();
        
        BioMetricStationStringParser parser = new BioMetricStationStringParser();
        int MAX_BYTES = 64;

        SerialPort comPort = SerialPort.getCommPorts()[0];
        comPort.openPort();
        comPort.setBaudRate(115200);
        comPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return;
                }
                byte[] newData = new byte[MAX_BYTES];
                char[] message = new char[MAX_BYTES];
                
                
                int numRead = comPort.readBytes(newData, newData.length);
                //System.out.println("Read " + numRead + " bytes.");
                
                for(int i = 0; i<newData.length; i++){
                   message[i] = (char)(newData[i]);
                }
                
                String output = new String(message);
                if(output.contains("\n")){
                    //System.out.println("Found newline");
                    //System.out.println("Data: "+output);
                    SensorData data = parser.parse(output);
                    System.out.println(data);
                    mqttStringGenerator.sendMqttData(output);
                    System.out.println(output);
                    
                }

            }
        });
        
        

    }
}
