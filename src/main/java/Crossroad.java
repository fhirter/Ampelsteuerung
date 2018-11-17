import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class Crossroad {
    Crossroad() {
        TrafficLight trafficLight = new TrafficLight(); // hier wird die Kreuzung initialisiert, d.h. alle Models werden anhand einer Konfiguration erstellt

        TrafficLightView trafficLightView = new TrafficLightView(trafficLight);     // der view wird dann anhand des models erstellt

        trafficLight.machWas();
    }

    public void generateDriveway () {
        Driveway DrivewayNord = new Driveway(false, false, false);
        Driveway DrivewayEast = new Driveway(false, false, false);
        Driveway DrivewaySouth = new Driveway(false, false, false);
        Driveway DrivewayWest = new Driveway(false, false, false);

    }

        public void PedestrianStripeVisible() throws Exception {
            try {
                Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("driveway.fxml"));
                Group GP = new Group(node);
                GP.setVisible(true);
            } catch (Exception e) {
            }
        }


    }

