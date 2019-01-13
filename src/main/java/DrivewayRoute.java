import javafx.fxml.FXMLLoader;
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

    private TrafficLight trafficLightCar;
    private TrafficLight trafficLightPedestrian;
    private boolean pedestrianStripes;
    private boolean velostripes;
    private DrivewayRoute drivewayRoute;

    public DrivewayRoute(boolean pedestrianStripes, boolean velostripes) {
        this.pedestrianStripes = pedestrianStripes;
        this.velostripes = velostripes;
        createTrafficLightCar();
        createTrafficLightPedestrian();
    }

    public TrafficLight createTrafficLightCar () {
        TrafficLight trafficLightCAR = new TrafficLight(TrafficLightType.CAR);
        return trafficLightCAR;
    }

    public TrafficLight createTrafficLightPedestrian () {
        TrafficLight trafficLightPedestrian = new TrafficLight(TrafficLightType.PEDESTRIAN);
        return trafficLightPedestrian;
    }

    public TrafficLight getTrafficLightPedestrian() {
        return trafficLightPedestrian;
    }

    public TrafficLight getTrafficLightCar (){
        return trafficLightCar;
    }

    public DrivewayRoute getStateFromCheckboxes()
    {
        return this.drivewayRoute;
    }

}







