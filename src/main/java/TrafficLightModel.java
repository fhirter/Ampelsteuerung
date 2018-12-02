public class TrafficLightModel extends Obserable
{
    private TrafficLightType type;
    private TrafficLightState state = TrafficLightState.RED;


    /**
     * TrafficLightModel(): Constructor. Define the type from the trafficLight
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    26.11.2018
     * @arg     TrafficLightType type: Type from the trafficLight (CAR, PEDESTRIAN, BUS, BYCICLE; ...)
     */
    public TrafficLightModel(TrafficLightType type)
    {
        this.type = type;
    }


    /**
     * getType(): Returns the type for the trafficLight. (CAR or PEDESTRIAN)
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     * @return  TrafficLightType: Type from the trafficLight
     */
    public TrafficLightType getType()
    {
        return this.type;
    }


    /**
     * setRed(): Change the color from the trafficLight to RED.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     */
    public void setRed()
    {
        state = TrafficLightState.RED;
        notifyObservers();
    }


    /**
     * setGreen(): Change the color from the trafficLight to GREEN.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     */
    public void setGreen()
    {
        state = TrafficLightState.GREEN;
        notifyObservers();
    }


    /**
     * setYellowRed(): Change the color from the trafficLight to YELLOW_RED.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     */
    public void setYellowRed()
    {
        state = TrafficLightState.YELLOWRED;
        notifyObservers();
    }


    /**
     * setYellow(): Change the color from the trafficLight to YELLOW.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     */
    public void setYellow()
    {
        state = TrafficLightState.YELLOW;
        notifyObservers();
    }


    /**
     * setDark(): Change the color from the trafficLight to DARK.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     */
    public void setDark()
    {
        state = TrafficLightState.DARK;
        notifyObservers();
    }


    /**
     * setAllOn(): Turns all lights on.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     */
    public void setAllOn()
    {
        state = TrafficLightState.ALLON;
        notifyObservers();
    }


    /**
     * getState(): Returns the state (color) from the trafficLight
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     * @return  state: State from the trafficLight
     */
    public TrafficLightState getState()
    {
        return state;
    }
}




