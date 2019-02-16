import java.util.LinkedList;
import java.util.List;


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
        trafficLightModelCars.add(trafficLightCar);
        TrafficLight trafficLightPedestrian = new TrafficLight(TrafficLightType.PEDESTRIAN);
        trafficLightModelPedestrians.add(trafficLightPedestrian);
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
    public void setPedestrianStripes(boolean pedestrianStripes)
    {
        this.pedestrianStripes = pedestrianStripes;
        notifyObservers();
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

    public void setVelostripes(boolean velostripes)
    {
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
}








