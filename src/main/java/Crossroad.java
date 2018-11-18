

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;



public class Crossroad {

    Crossroad() {
        TrafficLight trafficLight = new TrafficLight(); // hier wird die Kreuzung initialisiert, d.h. alle Models werden anhand einer Konfiguration erstellt

        TrafficLightView trafficLightView = new TrafficLightView(trafficLight);     // der view wird dann anhand des models erstellt

        Driveway driveway = new Driveway();
        PrimaryStageView primaryStageView = new PrimaryStageView(driveway);
    }




    }

