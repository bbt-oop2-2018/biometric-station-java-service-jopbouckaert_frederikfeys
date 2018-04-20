/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorvisualizer;

import be.vives.oop.mqtt.chatservice.MqttBiometricStationService;
import helpers.RangedNumberGenerator;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author jopbo_000
 */
public class FXMLChartDisplayController implements Initializable {
    
    private MqttBiometricStationService biometricStationService;
    
    @FXML private LineChart temperatureChart;
    @FXML private LineChart heartBeatChart;
    private XYChart.Series temperatureValues[];
    private XYChart.Series heartbeatValues[];
    private final int NUMBER_OF_TEMPERATURE_SERIES = 1;
    private final int NUMBER_OF_HEARTBEAT_SERIES = 1;
    
    private int xValue = 0;
    private final int MAXIMUM_TEMPERATURE = 30;
    private final int MINIMUM_TEMPERATURE = -10;
    
    private final int MINIMUM_HEARTBEAT = 1;
    private final int MAXIMUM_HEARTBEAT = 300;
    
    
    

    
    
    private RangedNumberGenerator temperatureData = new RangedNumberGenerator(MINIMUM_TEMPERATURE,MAXIMUM_TEMPERATURE);
    private RangedNumberGenerator heartBeatData = new RangedNumberGenerator(MINIMUM_HEARTBEAT,MAXIMUM_HEARTBEAT);
    
    
    
    @FXML
    private void generateRandomDataHandler(ActionEvent event) {
        temperatureValues[0].getData().add(new XYChart.Data(xValue, temperatureData.generate()));
        heartbeatValues[0].getData().add(new XYChart.Data(xValue, heartBeatData.generate())); 
        xValue++;
        
        biometricStationService.sendMqttData("[" + "ABCDE" + "|" + temperatureData + "|" + heartBeatData + "|" + "EDCBA" + "]");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        biometricStationService = new MqttBiometricStationService("jop", "test");
        
        
        temperatureValues = new XYChart.Series[NUMBER_OF_TEMPERATURE_SERIES];
        heartbeatValues = new XYChart.Series[NUMBER_OF_HEARTBEAT_SERIES];

        
        temperatureValues[0] = createXYChartTemperature("Random temperature");
        heartbeatValues[0] = createXYChartHeartbeat("Random heartbeat");
        
        
        temperatureChart.getYAxis().setLabel("Temperature [celcius]");
        temperatureChart.getXAxis().setLabel("Measurement");
        
        heartBeatChart.getYAxis().setLabel("Heartbeat [BPM]");
        heartBeatChart.getXAxis().setLabel("Measurement");

    }


    private XYChart.Series createXYChartTemperature(String name){
        
        XYChart.Series series = new XYChart.Series();
        series.setName(name);
        temperatureChart.getData().add(series);
        return series;
        
    }
    
    
    private XYChart.Series createXYChartHeartbeat(String name){
        
        XYChart.Series series = new XYChart.Series();
        series.setName(name);
        heartBeatChart.getData().add(series);
        return series;
        
    }
    
}
