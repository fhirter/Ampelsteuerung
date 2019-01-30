import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class DrivewayRoute: Data model for the crossroad
 *
 * @version 1.0
 * @autor   Class NIN
 * @date   14.11.2018
 */

public class DrivewayRoute extends Observable
{
    private boolean pedestrianStripes;
    private boolean velostripes;

    private List<TrafficLight> trafficLightModelCars = new LinkedList<>();
    private List<TrafficLight> trafficLightModelPedestrians = new LinkedList<>();
    private List<TrafficLightController> trafficLightControllerCars = new LinkedList<>();
    private List<TrafficLightController> trafficLightControllerPedestrians = new LinkedList<>();

    private final Point2D refTrafficLights = new Point2D(0,0);


    public DrivewayRoute(boolean pedestrianStripes, boolean velostripes)
    {
        this.pedestrianStripes = pedestrianStripes;
        this.velostripes = velostripes;

        TrafficLight trafficLightCar = new TrafficLight(TrafficLightType.CAR);
        TrafficLightController trafficLightControllerCar = new TrafficLightController(trafficLightCar, refTrafficLights, new Point2D(130,145), 90);
        trafficLightCar.addObserver(trafficLightControllerCar);
        trafficLightModelCars.add(trafficLightCar);
        trafficLightControllerCars.add(trafficLightControllerCar);

        TrafficLight trafficLightPedestrian = new TrafficLight(TrafficLightType.PEDESTRIAN);
        TrafficLightController trafficLightControllerPedestrianLeft = new TrafficLightController(trafficLightPedestrian, refTrafficLights, new Point2D(170,-65), 0);
        trafficLightCar.addObserver(trafficLightControllerPedestrianLeft);
        trafficLightControllerPedestrians.add(trafficLightControllerPedestrianLeft);
        TrafficLightController trafficLightControllerPedestrianRight = new TrafficLightController(trafficLightPedestrian, refTrafficLights, new Point2D(240,155), 180);
        trafficLightCar.addObserver(trafficLightControllerPedestrianRight);
        trafficLightModelPedestrians.add(trafficLightPedestrian);
        trafficLightControllerPedestrians.add(trafficLightControllerPedestrianRight);
    }


    public boolean getPedestrianStripes()
    {
        return pedestrianStripes;
    }


    public boolean getVelostripes()
    {
        return velostripes;
    }


    public List<TrafficLight> getTrafficLightModelCar()
    {
        return trafficLightModelCars;
    }


    public List<TrafficLight> getTrafficLightModelPedestrian()
    {
        return trafficLightModelPedestrians;
    }


    public List<TrafficLightController> getTrafficLightControllerCar()
    {
        return trafficLightControllerCars;
    }


    public List<TrafficLightController> getTrafficLightControllerPedestrian()
    {
        return trafficLightControllerPedestrians;
    }
}








