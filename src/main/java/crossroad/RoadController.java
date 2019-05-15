package crossroad;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import traffic_lights.TrafficLight;
import traffic_lights.TrafficLightController;
import util.Observer;
import util.Position;

import java.io.IOException;

/**
 * @autor Schweizer Patrick, Grimm Raphael, Vogt Andreas, Reiter Daniel, Hirter Fabian
 * @since  02.08.2018
 *
 */

public class RoadController extends Group implements Observer {
    private final Point2D refTrafficLights = new Point2D(0, 0);
    @FXML private Group bicycleLane;
    @FXML private Line pedestrianStripes;
    @FXML private Group drivewayRoute;
    private Road road;
    private TrafficLightController trafficLightController;

    public RoadController(Road road, Point2D ref, Position offset) {
        this.road = road;
        this.road.addObserver(this);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/road.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // positioning
        getTransforms().add(new Rotate(offset.getAngle(), 0, 0));
        setLayoutX(ref.getX() + offset.getX());
        setLayoutY(ref.getY() + offset.getY());

        setScaleX(1);
        setScaleY(1);

        initializeTrafficLightController(road.getTrafficLight(), new Position(130, 145, 90));
    }


    private void initializeTrafficLightController(TrafficLight trafficLight, Position position) {
        trafficLightController = new TrafficLightController(trafficLight, refTrafficLights, position);
        getChildren().add(trafficLightController);
        trafficLight.addObserver(trafficLightController);
    }


    @Override
    public void update() {

        if (road.hasBicycleLane() == true) {
            bicycleLane.setVisible(true);
        } else {
            bicycleLane.setVisible(false);
        }

        if (road.hasPedestrianStripes() == true) {
            pedestrianStripes.setVisible(true);
        } else {
            pedestrianStripes.setVisible(false);
        }

        if (road.isVisible() == true) {
            drivewayRoute.setVisible(true);
        } else {
            drivewayRoute.setVisible(false);
        }
    }
}
