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
    private boolean visibility = true;

    /**
     * DrivewayRoute: Constructor
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public DrivewayRoute()
    {
        TrafficLight trafficLightCar = new TrafficLight(TrafficLightType.CAR);
        TrafficLightController trafficLightControllerCar = new TrafficLightController(trafficLightCar, refTrafficLights, new Point2D(130,145), 90);
        trafficLightCar.addObserver(trafficLightControllerCar);
        trafficLightModelCars.add(trafficLightCar);
        trafficLightControllerCars.add(trafficLightControllerCar);

        TrafficLight trafficLightPedestrian = new TrafficLight(TrafficLightType.PEDESTRIAN);
        TrafficLightController trafficLightControllerPedestrianLeft = new TrafficLightController(trafficLightPedestrian, refTrafficLights, new Point2D(170, -65), 0);
        trafficLightCar.addObserver(trafficLightControllerPedestrianLeft);
        trafficLightControllerPedestrians.add(trafficLightControllerPedestrianLeft);
        TrafficLightController trafficLightControllerPedestrianRight = new TrafficLightController(trafficLightPedestrian, refTrafficLights, new Point2D(240, 155), 180);
        trafficLightCar.addObserver(trafficLightControllerPedestrianRight);
        trafficLightModelPedestrians.add(trafficLightPedestrian);
        trafficLightControllerPedestrians.add(trafficLightControllerPedestrianRight);
    }


    /**
     * DrivewayRoute: set Driveway Route Visiable
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     * @arg      visibility of DrivewayRoute
     */
    public void setVisibility(boolean visibility)
    {
        this.visibility = visibility;
        notifyObservers();
    }
    /**
     * DrivewayRoute: get state of PedestrianStripe
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */

    public boolean getPedestrianStripes()
    {
        return pedestrianStripes;
    }

    /**
     * DrivewayRoute: get state of Velostripes
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public boolean getVelostripes()
    {
        return velostripes;
    }

    /**
     * DrivewayRoute: get Visiable of DrivewayRoute
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */

    public boolean getVisibility()
    {
        return this.visibility;
    }

    /**
     * DrivewayRoute: set PedestrianStripe
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     * @arg      pedestrianStripe of DrivewayRoute
     */
    public void setPedestrianStripes(boolean pedestrianStripes) {
        this.pedestrianStripes = pedestrianStripes;
        notifyObservers();

        trafficLightControllerPedestrians.get(0).setVisible(pedestrianStripes);
        trafficLightControllerPedestrians.get(1).setVisible(pedestrianStripes);
    }

    /**
     * DrivewayRoute: set VeloStripes
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     * @arg      pedestrianStripe of VeloStripes
     */

    public void setVelostripes(boolean velostripes) {
        this.velostripes = velostripes;
        notifyObservers();
    }

    /**
     * DrivewayRoute: get the TrafficLightCar of DrivewayRoute
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public List<TrafficLight> getTrafficLightModelCar()
    {
        return trafficLightModelCars;
    }

    /**
     * DrivewayRoute: get the TrafficLightPedestrian of DrivewayRoute
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public List<TrafficLight> getTrafficLightModelPedestrian()
    {
        return trafficLightModelPedestrians;
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
     * DrivewayRoute: get the TrafficLightControllerPedestrian of DrivewayRoute
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public List<TrafficLightController> getTrafficLightControllerPedestrian()
    {
        return trafficLightControllerPedestrians;
    }

}








