package com.company;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Controller{

    public Button start;

    @FXML
    private ChoiceBox<String> setchoiceOfAlgorithm;

    public void setSetchoiceOfAlgorithm(List<String> nodes) {

        setchoiceOfAlgorithm.getItems().addAll(nodes);
    }


    public void handleButtonClick(){
        start.setText("ok");

    }

    public void newProject(MouseEvent mouseEvent) {
        // Erstellen eines neuen Projektes

    }
}
