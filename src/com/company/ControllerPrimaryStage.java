package com.company;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Handler;

public class ControllerPrimaryStage {


    @FXML
    AnchorPane aboutPane;
    @FXML
    Pane driveway;
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
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}