package com.company;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrafficLight
{
    /**
     * launchGui(): Start gui for trafficLight
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     */
    public void launchGui() throws Exception
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));
            Parent TrafficLightGui = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("TrafficLightGui");
            stage.setScene(new Scene(TrafficLightGui));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
