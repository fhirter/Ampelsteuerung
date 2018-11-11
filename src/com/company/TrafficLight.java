package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrafficLight  extends Application
{
    @Override
    public void start(Stage trafficLight) throws Exception
    {
        try
        {
        Parent TrafficLightGui = FXMLLoader.load(getClass().getResource("trafficLightGui.fxml"));
        trafficLight.setTitle("TrafficLightGui");
        trafficLight.setScene(new Scene(TrafficLightGui, 320, 200));
        trafficLight.show();
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    // TODO: 10.11.2018
    // Ist die Uebergabe von null bei launch() problematisch?
    /**
     * launchGui(): Start gui for trafficLight
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     */
    public void launchGui()
    {
        launch(null);
    }
}
