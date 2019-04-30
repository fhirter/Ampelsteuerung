package crossroad;

import traffic_lights.TrafficLight;
import traffic_lights.TrafficLightType;
import util.Observable;

/**
 * Class crossroad.Road: Data model for the crossroad
 *
 * @version 1.0
 * @autor   Class NIN
 * @date   14.11.2018
 */
public class Road extends Observable
{
    private boolean pedestrianStripes;
    private boolean velostripes;


    private TrafficLight trafficLightModelCars;
    private TrafficLight trafficLightModelPedestrians;
    private boolean visibility = true;


    /**
     * crossroad.Road: Constructor
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public Road()
    {
        trafficLightModelCars = new TrafficLight(TrafficLightType.CAR);
        trafficLightModelPedestrians = new TrafficLight(TrafficLightType.PEDESTRIAN);
    }


    /**
     * crossroad.Road: set Driveway Route Visiable
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     * @arg      visibility of crossroad.Road
     */
    public void setVisibility(boolean visibility)
    {
        this.visibility = visibility;
        notifyObservers();
    }


    /**
     * crossroad.Road: get state of PedestrianStripe
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
     * crossroad.Road: get state of Velostripes
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
     * crossroad.Road: get Visiable of crossroad.Road
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
     * crossroad.Road: set PedestrianStripe
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     * @arg      pedestrianStripe of crossroad.Road
     */
    public void setPedestrianStripes(boolean pedestrianStripes)
    {
        this.pedestrianStripes = pedestrianStripes;
        notifyObservers();
    }

    /**
     * crossroad.Road: set VeloStripes
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
     * crossroad.Road: get the TrafficLightCar of crossroad.Road
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public TrafficLight getTrafficLightModelCar()
    {
        return trafficLightModelCars;
    }

    /**
     * crossroad.Road: get the TrafficLightPedestrian of crossroad.Road
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public TrafficLight getTrafficLightModelPedestrian()
    {
        return trafficLightModelPedestrians;
    }
}








