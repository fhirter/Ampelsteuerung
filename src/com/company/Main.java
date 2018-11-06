package com.company;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        Parent root = FXMLLoader.load(getClass().getResource("primaryStage.fxml"));
        primaryStage.setTitle("Ampelsteuerung");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
        Algorithmus crossroadSimulate = new Algorithmus();

        System.out.println("Start project.");

    }

}
