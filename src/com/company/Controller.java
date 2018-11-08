package com.company;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Handler;

public class Controller {

    public MenuItem exitSoftware;
    @FXML
    CheckBox checkboxvelostripes;
    @FXML
    CheckBox checkboxbusway;
    @FXML
    CheckBox checkboxtramway;


    @FXML
    private ChoiceBox<String> setchoiceOfAlgorithm;
    @FXML
    private ChoiceBox<String> setnumberOfCrossing;


    @FXML
    private void handlesetnumberOfCrossing() {
/*        String message = "Your Choice:\n";

    System.out.println(message);
    */
    }

    @FXML
    private void handleSubmitButtonAction() {
        String message = "Your Choice:\n";

        if (checkboxvelostripes.isSelected())
            message += "Velo Stripes TRUE";

            if (checkboxbusway.isSelected())
                message += "Bus Way TRUE";

                if (checkboxtramway.isSelected())
                    message += "Tramway TRUE";



                System.out.println(message);

            }
            @FXML
            public void setSetchoiceOfAlgorithm (List < String > algorithm) {

                setchoiceOfAlgorithm.getItems().addAll(algorithm);
            }

            public void setSetnumberOfCrossing (List < String > crossing) {

                setnumberOfCrossing.getItems().addAll(crossing);
            }

            public void handleButtonClick () {

            }


            public void newProject (MouseEvent mouseEvent){
                // Erstellen eines neuen Projektes

            }


        }

