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



    private List<TrafficLightController> trafficLightControllerCars = new LinkedList<>();
    private List<TrafficLightController> trafficLightControllerPedestrians = new LinkedList<>();



    private final Point2D refTrafficLights = new Point2D(200,330);


    public DrivewayRoute(boolean pedestrianStripes, boolean velostripes)
    {
        this.pedestrianStripes = pedestrianStripes;
        this.velostripes = velostripes;


        TrafficLight trafficLightCar = new TrafficLight(TrafficLightType.CAR);
        TrafficLightController trafficLightControllerCar = new TrafficLightController(trafficLightCar, refTrafficLights, new Point2D(350,50), 0);
        trafficLightCar.addObserver(trafficLightControllerCar);
        trafficLightControllerCars.add(trafficLightControllerCar);


        TrafficLight trafficLightPedestrian = new TrafficLight(TrafficLightType.PEDESTRIAN);
        TrafficLightController trafficLightControllerPedestrian = new TrafficLightController(trafficLightPedestrian, refTrafficLights, new Point2D(0,-500), 0);
        trafficLightCar.addObserver(trafficLightControllerPedestrian);
        trafficLightControllerPedestrians.add(trafficLightControllerPedestrian);
        trafficLightCar.setYellowFlash();
        trafficLightPedestrian.setYellowFlash();
        notifyObservers();

    }


    public boolean getPedestrianStripes() {
        return pedestrianStripes;
    }

    public boolean getVelostripes() {
        return velostripes;
    }

    public void setPedestrianStripes(boolean pedestrianStripes) {
        this.pedestrianStripes = pedestrianStripes;
        notifyObservers();
    }

    public void setVelostripes(boolean velostripes) {
        this.velostripes = velostripes;
        notifyObservers();
    }


    public List<TrafficLightController> getTrafficLightControllerCar() {
        return trafficLightControllerCars;
    }

    public List<TrafficLightController> getTrafficLightControllerPedestrian() {
        return trafficLightControllerPedestrians;
    }



}








