import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;

import java.util.HashMap;
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
    private TrafficLightController trafficLightControllerCar;
    private TrafficLightController trafficLightControllerPedestrian;


    private final Point2D refTrafficLights = new Point2D(200,330);


    public DrivewayRoute(boolean pedestrianStripes, boolean velostripes)
    {
        this.pedestrianStripes = pedestrianStripes;
        this.velostripes = velostripes;

        createTrafficLightCar();
        createTrafficLightPedestrian();

    }


    public TrafficLight createTrafficLightCar () {
        TrafficLight trafficLightCar = new TrafficLight(TrafficLightType.CAR);
        TrafficLightController trafficLightControllerCar = new TrafficLightController(trafficLightCar, refTrafficLights, new Point2D(550, -300), 0);
        trafficLightCar.addObserver(trafficLightControllerCar);
        return trafficLightCar;
    }


    public TrafficLight createTrafficLightPedestrian () {
        TrafficLight trafficLightPedestrian = new TrafficLight(TrafficLightType.PEDESTRIAN);
        TrafficLightController trafficLightControllerPedestrian = new TrafficLightController(trafficLightPedestrian, refTrafficLights, new Point2D(450,-250), 0);
        trafficLightPedestrian.addObserver(trafficLightControllerPedestrian);
        return trafficLightPedestrian;
    }


    public boolean getPedestrianStripes() {


        return pedestrianStripes;
    }

    public boolean getVelostripes() {
        return velostripes;
    }

    public TrafficLightController getTrafficLightControllerCar (){
        return trafficLightControllerCar;
    }

    public TrafficLightController getTrafficLightControllerPedestrian (){
    return trafficLightControllerPedestrian;
}


}








