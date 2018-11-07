package com.company;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import java.util.List;

public class Controller{

    public Button start;




    @FXML
    private ChoiceBox<String> setchoiceOfAlgorithm;
    @FXML
    private ChoiceBox<String> setnumberOfCrossing;



    public void handleSubmitButtonAction() {
        String message = "Your Choice:\n";

    System.out.println(message);
    }



    public void setSetchoiceOfAlgorithm(List<String> algorithm) {

        setchoiceOfAlgorithm.getItems().addAll(algorithm);
    }

    public void setSetnumberOfCrossing(List<String> crossing){

        setnumberOfCrossing.getItems().addAll(crossing);
    }


    public void handleButtonClick(){
        start.setText("ok");

    }

    public void newProject(MouseEvent mouseEvent) {
        // Erstellen eines neuen Projektes

    }
}
