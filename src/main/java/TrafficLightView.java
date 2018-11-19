import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class TrafficLightView extends Node      // so können dann deine ampeln in die main gui eingebaut werden, dies habe ich aber nicht getestet
{
    private TrafficLightModel model;
    private TrafficLightController trafficLightController;

    public TrafficLightView(TrafficLightModel model)  {

        this.model = model;       // hier übergebe ich dir mal das datenmodell. anhand von diesem erstellst du dann das gui. in diesem konkreten fall wohl nur, ob fussgänger oder autoampel, der rest ist ja immer gleich

    }

    /**
     * getController(): Returns the instance from the controller (gui)
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    17.11.2018
     * @return  trafficLightController
     */
    public TrafficLightController getController()
    {
        return trafficLightController;
    }
}
