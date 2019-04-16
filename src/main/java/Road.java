/**
 * Class Road: Data model for the crossroad
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
     * Road: Constructor
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
     * Road: set Driveway Route Visiable
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     * @arg      visibility of Road
     */
    public void setVisibility(boolean visibility)
    {
        this.visibility = visibility;
        notifyObservers();
    }


    /**
     * Road: get state of PedestrianStripe
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
     * Road: get state of Velostripes
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
     * Road: get Visiable of Road
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
     * Road: set PedestrianStripe
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     * @arg      pedestrianStripe of Road
     */
    public void setPedestrianStripes(boolean pedestrianStripes)
    {
        this.pedestrianStripes = pedestrianStripes;
        notifyObservers();
    }

    /**
     * Road: set VeloStripes
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
     * Road: get the TrafficLightCar of Road
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
     * Road: get the TrafficLightPedestrian of Road
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








