package com.company;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;


/**
 * Class Main: Mainmethode for the TEKO project "Ampelsteuerung".
 *
 * LONG DESCRIOTION
 *
 * @version 1.0
 * @autor   Class NIN
 * @date    04.11.2018
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primaryStage.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Ampelsteuerung");

        Controller controller = loader.getController();

        List<String> nodes = new LinkedList<>();
        nodes.add("Algorithm A");
        nodes.add("Algorithm B");
        nodes.add("Algorithm C");
        nodes.add("Algorithm D");
        controller.setSetchoiceOfAlgorithm(nodes);

        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();


    }

    @Override
    public void stop(){

    }

    public static void main(String[] args)
    {
        launch(args);
        Algorithmus crossroadSimulate = new Algorithmus();

        System.out.println("Start project.");

    }

}
