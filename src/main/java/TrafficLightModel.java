public class TrafficLightModel extends Obserable
{
    double scaleFactor = 0;
    TrafficLightType type = TrafficLightType.CAR;
    TrafficLightState state = TrafficLightState.RED;

    /**
     * setScaleFactor(): Set the scale factor from the trafficLight.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     * @arg     scaleFactor: Scale factor from 0.1 to 1 for the size from the trafficLight
     */
    public void setScaleFactor(double scaleFactor)
    {
        if((scaleFactor < 0.1) || (scaleFactor > 1.0))
        {
            this.scaleFactor = 1.0;
        }else
        {
            this.scaleFactor = scaleFactor;
        }
        notifyObservers();
    }


    /**
     * getScaleFactor(): Returns the selected scale factor
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    20.11.2018
     * @return  double scaleFactor: Scale factor from 0.1 to 1 for the size from the trafficLight
     */
    public double getScaleFactor()
    {
        return scaleFactor;
    }


    /**
     * setType(): Change the type (CAR or PEDESTRIAN) for the trafficlight.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     * @arg     type: Type from the trafficLight
     */
    public void setType(TrafficLightType type)
    {
        this.type = type;
        notifyObservers();
    }


    /**
     * getType(): Returns the type for the trafficLight. (CAR or PEDESTRIAN)
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     * @return  type: Type from the trafficLight
     */
    public TrafficLightType getType()
    {
        return type;
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




