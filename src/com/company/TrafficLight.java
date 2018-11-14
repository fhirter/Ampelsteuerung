package com.company;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TrafficLight extends Obserable{


    private enum trafficLightType {car, pedestrian};
    private enum trafficLightState {green, yellow, yellowRed, red, dark, allOn;};
    private trafficLightState actTrafficLightState;
/*
    private Timeline trafficLightStateChangeTimer = new Timeline(new KeyFrame(
            Duration.millis(500),
        ae -> ChangeTimerTick()));

 */
    private String trafficLightOrder = "";
    private trafficLightType type;
    



    /**
     * setType(): Change the type for the trafficlight.
     *
     * Set the graphical group for pedestrian visible or unvisible.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     enum trafficLightType
     */
    public void setType(trafficLightType newTrafficLightType)
    {
        type = newTrafficLightType;
    }


    /**
     * getState(): Returns the actual state from the trafficLight
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @return  enum actTrafficLightState
     */
    public trafficLightState getState()
    {
        return actTrafficLightState;
    }


    /**
     * stateChangeTimer(): Simulate the trafficLight
     *
     * Initialize a new continous timer.
     * Every xxSeconds the methode timerTick() is called.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     operation: Operation order what the trafficLight must change
     */

    /*
    public void stateChangeTimer(String operation)
    {
        if(!operation.equals("timerStopp"))
        {
            trafficLightStateChangeTimer.setCycleCount(Animation.INDEFINITE);
            trafficLightStateChangeTimer.play();
        }
        else
        {
            trafficLightStateChangeTimer.stop();
        }

        // Need the operation into the ChangeTimerTick function.
        trafficLightOrder = operation;
    }

*/
    /**
     * ChangeTimerTick(): Change in combination with the timer the lights from the trafficLight
     *
     * Every xxSeconds the methode ChangeTimerTick() is called from the timer.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     */

/*
    public void ChangeTimerTick()
    {
        int actStateTrafficLight = 0;

        try {

            // Returns the from the actual trafficLight state the integer number from the enum
            actStateTrafficLight = trafficLightState.valueOf(getState().toString()).ordinal();
            System.out.println("Act TrafficLight: " + actStateTrafficLight + " " + trafficLightState.values()[actStateTrafficLight]);

            switch (trafficLightOrder) {
                case "simulation": {
                    actStateTrafficLight++;
                    if(actStateTrafficLight >= trafficLightState.values().length)
                    {actStateTrafficLight = 0;}
                    changeColor(trafficLightState.values()[actStateTrafficLight]);
                    break;
                }
                case "flashYellow": {
                    if (!getState().equals(trafficLightState.yellow)) {
                        changeColor(trafficLightState.yellow);
                    } else {
                        changeColor(trafficLightState.dark);
                    }
                    break;
                }
                case "switchToRed": {
                    if (!getState().equals(trafficLightState.red)) {
                        changeColor(trafficLightState.values()[actStateTrafficLight+1]);
                    } else {
                        trafficLightStateChangeTimer.stop();
                    }
                    break;
                }
                case "switchToGreen": {
                    if (!getState().equals(trafficLightState.green)) {
                        changeColor(trafficLightState.values()[actStateTrafficLight-1]);
                    } else {
                        trafficLightStateChangeTimer.stop();
                    }
                    break;
                }
                default: {
                    changeColor(trafficLightState.dark);
                    trafficLightStateChangeTimer.stop();
                    break;
                }
            }
        }catch (Exception e)
        {
            changeColor(trafficLightState.red);
        }

    }
    */
}
