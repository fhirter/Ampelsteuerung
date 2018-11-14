package com.company;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PrimaryStageController {



    @FXML
    MenuItem exitSoftware;
    @FXML
    CheckBox checkboxvelostripes;
    @FXML
    CheckBox checkboxbusway;
    @FXML
    CheckBox checkboxtramway;
    @FXML
    Rectangle street;
    @FXML
    AnchorPane drivewayAnchor;
    @FXML
    private ChoiceBox<String> setchoiceOfAlgorithm;
    @FXML
    private ChoiceBox<String> setnumberOfCrossing;

    @FXML
    private void handleSubmitButtonAction() {
        String message = "Your Choice:\n";


        if (checkboxvelostripes.isSelected())
            message += "Velo Stripes TRUE\n";

        if (checkboxbusway.isSelected())
            message += "Bus Way TRUE\n";

        if (checkboxtramway.isSelected())
            message += "Tramway TRUE\n";


        System.out.println(message);
        System.out.println(setnumberOfCrossing.getValue());
        System.out.println(setchoiceOfAlgorithm.getValue());


        street.setVisible(true);

    }


    @FXML
    public void setSetchoiceOfAlgorithm(List<String> algorithm) {

        setchoiceOfAlgorithm.getItems().addAll(algorithm);
    }

    public void setSetnumberOfCrossing(List<String> crossing) {

        setnumberOfCrossing.getItems().addAll(crossing);
    }

    public void handleExitSoftware() {
        System.exit(0);

    }


    public void newProject(MouseEvent mouseEvent) throws IOException {
        // Erstellen eines neuen Projektes


    }
    public void openAboutWindow() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("aboutStage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startButtonConfig() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("driveway.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleStartTrafficLight(javafx.event.ActionEvent actionEvent)
    {
        TrafficLightView trafficLight = new TrafficLightView();
        try {
            trafficLight.launchGui();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}