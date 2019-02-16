import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class DrivewayRouteController extends AnchorPane implements Initializable, Observer
{
    @FXML   private AnchorPane bicycleSripes;
    @FXML   private Group pedestrianStripes;
    @FXML   private AnchorPane drivewayRoute;
    private final Point2D refTrafficLights = new Point2D(0,0);
    private DrivewayRoute model;
    private List<TrafficLightController> trafficLightControllerCars = new LinkedList<>();
    private List<TrafficLightController> trafficLightControllerPedestrians = new LinkedList<>();



    /**
     * DrivewayRouteController: Constructor
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     * @arg     drivewayRoute (Object form model class), ref (Referenze for all Objects) offset( Place for DrivewayRoute) Rotate (Ankle°)
     */
    public DrivewayRouteController(DrivewayRoute drivewayRoute, Point2D ref, Point2D offset, int Rotate)
    {
        this.model= drivewayRoute;



        FXMLLoader loader = new FXMLLoader(getClass().getResource("drivewayRoute.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // positioning
        setLayoutX(ref.getX() + offset.getX());
        setLayoutY(ref.getY() + offset.getY());
        setRotate(Rotate);
        setScaleX(1);
        setScaleY(1);

        TrafficLightController trafficLightControllerCar = new TrafficLightController(drivewayRoute.getTrafficLightModelCar().get(0),refTrafficLights, new Point2D(130,145), 90);
        TrafficLightController trafficLightControllerPedestrianLeft = new TrafficLightController(drivewayRoute.getTrafficLightModelPedestrian().get(0),refTrafficLights, new Point2D(170, -65), 0);
        TrafficLightController trafficLightControllerPedestrianRight = new TrafficLightController(drivewayRoute.getTrafficLightModelPedestrian().get(0),refTrafficLights, new Point2D(240, 155), 180);
        drivewayRoute.getTrafficLightModelCar().get(0).addObserver(trafficLightControllerCar);
        drivewayRoute.getTrafficLightModelPedestrian().get(0).addObserver(trafficLightControllerPedestrianLeft);
        drivewayRoute.getTrafficLightModelPedestrian().get(0).addObserver(trafficLightControllerPedestrianRight);
        trafficLightControllerCars.add(trafficLightControllerCar);
        trafficLightControllerPedestrians.add(trafficLightControllerPedestrianLeft);
        trafficLightControllerPedestrians.add(trafficLightControllerPedestrianRight);
        trafficLightControllerPedestrians.get(0).setVisible(false);
        trafficLightControllerPedestrians.get(1).setVisible(false);
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
        bicycleSripes.setVisible(false);
        pedestrianStripes.setVisible(false);
        model.setPedestrianStripes(false);
        model.setVelostripes(false);
    }


    /**
     * update(): Obstacle where is registred into DrivewayRoute
     *
     * Is automatic called when something into trafficLightModel is changed.
     *
     * @version 1.0
     * @autor   Class NIN
     * @date    10.02.2019
     */
    @Override
    public void update() {

        if(model.getVelostripes() == true)
        {
            bicycleSripes.setVisible(true);
        }
        else{
            bicycleSripes.setVisible(false);
        }

        if(model.getPedestrianStripes()== true)
        {
            pedestrianStripes.setVisible(true);
            trafficLightControllerPedestrians.get(0).setVisible(true);
            trafficLightControllerPedestrians.get(1).setVisible(true);
        }
        else
        {
            pedestrianStripes.setVisible(false);
            trafficLightControllerPedestrians.get(0).setVisible(false);
            trafficLightControllerPedestrians.get(1).setVisible(false);
        }

        if(model.getVisibility() == true)
        {
            drivewayRoute.setVisible(true);
        }
        else
        {
            drivewayRoute.setVisible(false);
        }
    }
/**
 * DrivewayRoute: get the TrafficLightControllerCar of DrivewayRoute
 *
 *
 * @version 1.0
 * @autor   NIN Class
 * @date    02.08.2018
 *
 */


    public List<TrafficLightController> getTrafficLightControllerCar()
    {
        return trafficLightControllerCars;
    }

    /**
     * DrivewayRouteController: get the TrafficLightControllerPedestrian of DrivewayRoute
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     */

    public List<TrafficLightController> getTrafficLightControllerPedestrian()
    {
        return trafficLightControllerPedestrians;
    }
}
