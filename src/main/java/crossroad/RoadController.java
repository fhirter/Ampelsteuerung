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

public class RoadController extends Group implements Initializable, Observer
{
    @FXML   private Group bicycleLane;
    @FXML   private Line pedestrianStripes;
    @FXML   private Group drivewayRoute;
    private final Point2D refTrafficLights = new Point2D(0,0);
    private Road model;
    private TrafficLightController trafficLightControllerCar;
    private TrafficLightController trafficLightControllerPedestrianLeft;
    private TrafficLightController trafficLightControllerPedestrianRight;

    /**
     * DrivewayRouteController: Constructor
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     * @arg     road (Object form model class), ref (Referenze for all Objects) offset( Place for crossroad.Road) Rotate (Ankle°)
     */
    public RoadController(Road road, Point2D ref, Position offset)
    {
        this.model= road;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/road.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // positioning
        getTransforms().add(new Rotate(offset.getAngle(),0, 0));
       // setRotate(offset.getAngle());
        setLayoutX(ref.getX() + offset.getX());
        setLayoutY(ref.getY() + offset.getY());

        setScaleX(1);
        setScaleY(1);

        trafficLightControllerCar = createTrafficLightController(road.getTrafficLightModelCar(), new Position(130, 145, 90));
     //   trafficLightControllerPedestrianLeft = createTrafficLightController(road.getTrafficLightModelPedestrian(), new util.Position(170, -65, 0));
       // trafficLightControllerPedestrianRight = createTrafficLightController(road.getTrafficLightModelPedestrian(), new util.Position( 240, 155, 180));

//        trafficLightControllerPedestrianLeft.setVisible(false);
//        trafficLightControllerPedestrianRight.setVisible(false);
    }


    private TrafficLightController createTrafficLightController(TrafficLight trafficLightModelCar, Position position)
    {
        TrafficLightController trafficLightControllerCar = new TrafficLightController(trafficLightModelCar, refTrafficLights, position);
        getChildren().add(trafficLightControllerCar);
        trafficLightModelCar.addObserver(trafficLightControllerCar);
        return trafficLightControllerCar;
    }


    /**
     * initialize(URL location, ResourceBundle resources): Initialize during startUp all settings from the drivewayRoute
     *
     * Is automatic called when fxmlLoader.load() ist called.
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    10.02.2019
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
//        bicycleSripes.setVisible(false);
//        pedestrianStripes.setVisible(false);
//        model.setPedestrianStripes(false);
//        model.setVelostripes(false);
    }


    /**
     * update(): Obstacle where is registred into crossroad.Road
     *
     * Is automatic called when something into trafficLightModel is changed.
     *
     * @version 1.0
     * @autor   Class NIN
     * @date    10.02.2019
     */
    @Override
    public void update() {

        if (model.getVelostripes() == true) {
            bicycleLane.setVisible(true);
        } else {
            bicycleLane.setVisible(false);
        }

        if (model.getPedestrianStripes() == true) {
            pedestrianStripes.setVisible(true);
            trafficLightControllerPedestrianLeft.setVisible(false);
            trafficLightControllerPedestrianRight.setVisible(false);
        } else {
            pedestrianStripes.setVisible(false);
            trafficLightControllerPedestrianLeft.setVisible(false);
            trafficLightControllerPedestrianRight.setVisible(false);
        }

        if (model.getVisibility() == true) {
            drivewayRoute.setVisible(true);
        } else {
            drivewayRoute.setVisible(false);
        }
    }
}
