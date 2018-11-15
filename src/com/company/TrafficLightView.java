package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrafficLightView extends Application
{
    TrafficLightController controller;
    /**
     * start(Stage stage) throws Exception: Start gui for trafficLight
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    15.11.2018
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));
        Parent root = loader.load();
        stage.setTitle("TrafficLight");

        controller = loader.getController();
        stage.setScene(new Scene(root, 364, 200));
        stage.show();
    }


    /**
     * launchGui(): Start gui for trafficLight
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     */
    public void launchGui(String args)
    {
        launch(args);
    }


    /**
     * getController(): Returns the objetct from the created controller.
     *
     * Returns for register into the obstacle the object from the created controller.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    15.11.2018
     */
    public TrafficLightController getController()
    {
        return controller;
    }
}
