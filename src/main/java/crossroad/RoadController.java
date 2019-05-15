package crossroad;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import traffic_lights.TrafficLight;
import traffic_lights.TrafficLightController;
import util.Observer;
import util.Position;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @autor NIN Class
 * @date 02.08.2018
 *
 */

public class RoadController extends Group implements Initializable, Observer {
    @FXML private Group bicycleLane;
    @FXML private Line pedestrianStripes;
    @FXML private Group drivewayRoute;
    private final Point2D refTrafficLights = new Point2D(0, 0);
    private Road model;
    private TrafficLightController trafficLightController;

    public RoadController(Road road, Point2D ref, Position offset) {
        this.model = road;
        model.addObserver(this);

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
        // setRotate(offset.getAngle());
        setLayoutX(ref.getX() + offset.getX());
        setLayoutY(ref.getY() + offset.getY());

        setScaleX(1);
        setScaleY(1);

        trafficLightController = createTrafficLightController(road.getTrafficLightModelCar(), new Position(130, 145, 90));
    }


    private TrafficLightController createTrafficLightController(TrafficLight trafficLight, Position position) {
        TrafficLightController trafficLightControllerCar = new TrafficLightController(trafficLight, refTrafficLights, position);
        getChildren().add(trafficLightControllerCar);
        trafficLight.addObserver(trafficLightControllerCar);
        return trafficLightControllerCar;
    }


    /**
     * initialize(URL location, ResourceBundle resources): Initialize during startUp all settings from the drivewayRoute
     * <p>
     * Is automatic called when fxmlLoader.load() ist called.
     *
     * @version 1.0
     * @autor NIN Class
     * @date 10.02.2019
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        bicycleSripes.setVisible(false);
//        pedestrianStripes.setVisible(false);
//        model.setHasPedestrianStripes(false);
//        model.setHasBicycleLane(false);
    }


    /**
     * update(): Obstacle where is registred into crossroad.Road
     * <p>
     * Is automatic called when something into trafficLightModel is changed.
     *
     * @version 1.0
     * @autor Class NIN
     * @date 10.02.2019
     */
    @Override
    public void update() {

        if (model.hasBicycleLane() == true) {
            bicycleLane.setVisible(true);
        } else {
            bicycleLane.setVisible(false);
        }

        if (model.hasPedestrianStripes() == true) {
            pedestrianStripes.setVisible(true);
        } else {
            pedestrianStripes.setVisible(false);
        }

        if (model.isVisible() == true) {
            drivewayRoute.setVisible(true);
        } else {
            drivewayRoute.setVisible(false);
        }
    }
}
