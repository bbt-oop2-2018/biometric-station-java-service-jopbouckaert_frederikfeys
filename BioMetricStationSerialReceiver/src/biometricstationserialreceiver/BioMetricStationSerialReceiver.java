package biometricstationserialreceiver;

import allthingsservice.AllThingsService;
import biometricstationservice.MqttBiometricStationService;
import com.google.gson.Gson;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nicod
 * @author jopbo_000
 */
public class BioMetricStationSerialReceiver {

    public static void main(String[] args) throws Exception {
        
        MqttBiometricStationService biometricStationService = new MqttBiometricStationService("jopfrederik", "jopfrederik");
        AllThingsService dashboard = new AllThingsService();
        Gson gson = new Gson();
        BioMetricStationStringParser parsedData = new BioMetricStationStringParser();
        SerialLineReceiver receiver = new SerialLineReceiver(0, 115200, false);
        
        
        receiver.setLineListener(new SerialPortLineListener() {
            @Override
            public void serialLineEvent(SerialData data) {
                SensorData sensordata;
                SensorDataTemperature sensordatatemperature;
                SensorDataHeartbeat sensordataheartbeat;
                SensorDataAccelero sensordataaccelero;
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                Date dateTime;

                System.out.println("Received data from the serial port: " + data.getDataAsString());
                sensordata = parsedData.parse(data.getDataAsString());
                if (!(sensordata == null)){
                sensordatatemperature = new SensorDataTemperature(sensordata.getTemperature(),dateFormat.format(dateTime = new Date()));
                sensordataheartbeat = new SensorDataHeartbeat(sensordata.getHeartBeat(),dateFormat.format(dateTime = new Date()));
                sensordataaccelero = new SensorDataAccelero(sensordata.getXAcellero(),sensordata.getYAcellero(),sensordata.getZAcellero(),dateFormat.format(dateTime = new Date()));
                dashboard.set("temperature", sensordata.getTemperature());
                dashboard.set("heartbeat", sensordata.getHeartBeat());
                dashboard.set("Xaccelero", sensordata.getXAcellero());
                dashboard.set("Yaccelero", sensordata.getYAcellero());
                dashboard.set("Zaccelero", sensordata.getZAcellero());
                
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

