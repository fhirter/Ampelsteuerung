package com.company;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Handler;

public class Controller{

    public MenuItem exitSoftware;


    @FXML
    private ChoiceBox<String> setchoiceOfAlgorithm;
    @FXML
    private ChoiceBox<String> setnumberOfCrossing;


    public void handleSubmitButtonAction() {
/*        String message = "Your Choice:\n";

    System.out.println(message);
    */
    }



    public void setSetchoiceOfAlgorithm(List<String> algorithm) {

        setchoiceOfAlgorithm.getItems().addAll(algorithm);
    }

    public void setSetnumberOfCrossing(List<String> crossing){

        setnumberOfCrossing.getItems().addAll(crossing);
    }

    public void handleButtonClick(){

}


    public void newProject(MouseEvent mouseEvent) {
        // Erstellen eines neuen Projektes

    }



}
