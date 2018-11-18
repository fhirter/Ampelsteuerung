public class TrafficLightModel extends Obserable
{
    /**
     * setScaleFactor(): Set the scale factor from the trafficLight.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     * @arg     enum type
     * @arg     double scaleFactor
     */
    public void setScaleFactor(double scaleFactor)
    {
        if((scaleFactor < 0.1) || (scaleFactor > 1.0))
        {
            scaleFactor = 1.0;
        }
        notifyObservers("setScaleFactor", scaleFactor);
    }


    /**
     * settype(): Change the type for the trafficlight.
     *
     * Set the graphical group for pedestrian visible or unvisible.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     * @arg     enum type
     */
    public void setType(trafficLightType newType)
    {
        switch(newType)
        {
            case car: {
                notifyObservers("setType", false );
                break;
            }
            case pedestrian: {
                notifyObservers("setType", true );
                break;
            }
        }
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
        notifyObservers("changeColor", trafficLightState.red);
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
        notifyObservers("changeColor", trafficLightState.green);
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
        notifyObservers("changeColor", trafficLightState.yellow);
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
        notifyObservers("changeColor", trafficLightState.yellowRed);
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
        notifyObservers("changeColor", trafficLightState.dark);
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
        notifyObservers("changeColor", trafficLightState.allOn);
    }


    /**
     * getActState(): Returns the act state from the traffic light.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     * @returns trafficLightState: Act state from the trafficLight
     */
    public trafficLightState getActState()
    {
        return (trafficLightState) notifyObserversWithResponse("getActState");
    }
}




