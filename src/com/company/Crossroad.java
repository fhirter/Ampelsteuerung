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

    public void generateDriveway () {
        Driveway DrivewayNord = new Driveway(false, false, false);
        DrivewayNord.SetPedestrainStripes(pedestrainStripesCheckbox.getState());

        Driveway DrivewayEast = new Driveway(false, false, false);
        Driveway DrivewaySouth = new Driveway(false, false, false);
        Driveway DrivewayWest = new Driveway(false, false, false);

    }

        public void PedestrianStripeVisible() throws Exception {
            try {
                Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("driveway.fxml"));
                Group GP = new Group(node);
                GP.setVisible(pedestrainStripesCheckbox.getState());
            } catch (Exception e) {
            }
        }
}

