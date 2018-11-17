package com.company;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.awt.*;

public class Crossroad {

    @FXML
    Checkbox pedestrainStripesCheckbox;
    @FXML
    Group pedestrainStripes;


    public void generateDriveway () {
        Driveway DrivewayNord = new Driveway(false, false, false);
        Driveway DrivewayEast = new Driveway(false, false, false);
        Driveway DrivewaySouth = new Driveway(false, false, false);
        Driveway DrivewayWest = new Driveway(false, false, false);

        DrivewayNord.SetPedestrainStripes(pedestrainStripesCheckbox.getState());

    }

    public void PedestrianStripeVisible() throws Exception {
        try {
            Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("driveway.fxml"));
            if (pedestrainStripesCheckbox.getState() == true)
                pedestrainStripes.setVisible(true);
            else {pedestrainStripes.setVisible(false);}

        } catch (Exception e) {
        }
    }


}

