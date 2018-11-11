package com.company;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TrafficLightGui
{
    @FXML   private Circle redLightTraffic;
    @FXML   private Circle yellowLightTraffic;
    @FXML   private Circle greenLightTraffic;
    @FXML   private RadioButton trafficLightRed;
    @FXML   private RadioButton trafficLightYellowRed;
    @FXML   private RadioButton trafficLightYellow;
    @FXML   private RadioButton trafficLightGreen;
    @FXML   private RadioButton trafficLightDark;
    @FXML   private RadioButton trafficLightAllOn;
    @FXML   private RadioButton trafficLightTypeCar;
    @FXML   private RadioButton trafficLightTypePedestrain;
    @FXML   private Group symbolPedestrain;
    @FXML   private CheckBox simulationOnOff;
    @FXML   private CheckBox flashYellow;
    @FXML   private CheckBox stateToRed;
    @FXML   private CheckBox stateToGreen;

    private enum trafficLightType {car, pedestrain};
    private enum trafficLightState {green, yellow, yellowRed, red, dark, allOn;};
    private trafficLightState actTrafficLightState;

    private Timeline trafficLightStateChangeTimer = new Timeline(new KeyFrame(
            Duration.millis(500),
            ae -> trafficLightStateChangeTimerTick()));
    private String trafficLightOrder = "";



    /***************************** Methodes where are called from GUI **************************************************************************

     /**
     * trafficLightSetType(): Called when the radioButtons (trafficLight type) are changed
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     ActionEvent actionEvent
     */
    public void trafficLightSetType(ActionEvent actionEvent)
    {
        if(trafficLightTypeCar.isSelected())
        {
            setTrafficLightType(trafficLightType.car);
        }
        else if(trafficLightTypePedestrain.isSelected())
        {
            setTrafficLightType(trafficLightType.pedestrain);
        }
    }


    /**
     * trafficLightChangeColor(): Called when the radioButtons (trafficLight state) are changed
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     ActionEvent actionEvent
     */
    public void trafficLightChangeColor(ActionEvent actionEvent)
    {
        if(trafficLightRed.isSelected())
        {
            changeTrafficLightColor(trafficLightState.red);
        }
        else if(trafficLightYellowRed.isSelected())
        {
            changeTrafficLightColor(trafficLightState.yellowRed);
        }
        else if(trafficLightYellow.isSelected())
        {
            changeTrafficLightColor(trafficLightState.yellow);
        }
        else if(trafficLightGreen.isSelected())
        {
            changeTrafficLightColor(trafficLightState.green);
        }
        else if(trafficLightDark.isSelected())
        {
            changeTrafficLightColor(trafficLightState.dark);
        }
        else if(trafficLightAllOn.isSelected())
        {
            changeTrafficLightColor(trafficLightState.allOn);
        }
    }


    /**
     * trafficLightyellowFlash(): Let the trafficLight flash with the yellow signal.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @return  enum actTrafficLightState
     */
    public void startChangeTrafficLightColor(ActionEvent actionEvent)
    {
        if(stateToRed.isSelected())
        {
            trafficLightStateChangeTimer("switchToRed");
        }
        else if(stateToGreen.isSelected())
        {
            trafficLightStateChangeTimer("switchToGreen");
        }
        else if(flashYellow.isSelected())
        {
            trafficLightStateChangeTimer("flashYellow");
        }
        else
        {
            trafficLightStateChangeTimer("stopp");
        }
    }


    /**
     * trafficLightRunSimulation(): Start or stops the simulation
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     ActionEvent actionEvent
     */
    public void trafficLightRunSimulation(ActionEvent actionEvent)
    {
        if(simulationOnOff.isSelected())
        {
            trafficLightStateChangeTimer("simulation");
        }
        else
        {
            trafficLightStateChangeTimer("stopp");
        }
    }


    /***************************** Own methodes from the class **************************************************************************

     /**
     * setTrafficLightType(): Change the type for the trafficlight.
     *
     * Set the graphical group for pedestrain visible or unvisible.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     enum trafficLightType
     */
    public void setTrafficLightType(trafficLightType newTrafficLightType)
    {
        switch(newTrafficLightType)
        {
            case car: {
                symbolPedestrain.setVisible(false);
                break;
            }
            case pedestrain: {
                symbolPedestrain.setVisible(true);
                break;
            }
        }
    }


    /**
     * changeTrafficLightColor(): Redraw the color from the trafficLight
     *
     * Depending the state from the trafficLight the color from the lights are changed.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     enum trafficLightState
     */
    public void changeTrafficLightColor(trafficLightState newTrafficLightState)
    {
        actTrafficLightState = newTrafficLightState;

        switch(actTrafficLightState) {
            case red: {
                redLightTraffic.setFill(Paint.valueOf("#ff0000"));
                yellowLightTraffic.setFill(Paint.valueOf("#ababab"));
                greenLightTraffic.setFill(Paint.valueOf("#ababab"));
                trafficLightRed.setSelected(true);
                break;
            }
            case yellowRed: {
                redLightTraffic.setFill(Paint.valueOf("#ff0000"));
                yellowLightTraffic.setFill(Paint.valueOf("#e8ff1f"));
                greenLightTraffic.setFill(Paint.valueOf("#ababab"));
                trafficLightYellowRed.setSelected(true);
                break;
            }
            case yellow: {
                redLightTraffic.setFill(Paint.valueOf("#ababab"));
                yellowLightTraffic.setFill(Paint.valueOf("#e8ff1f"));
                greenLightTraffic.setFill(Paint.valueOf("#ababab"));
                trafficLightYellow.setSelected(true);
                break;
            }
            case green: {
                redLightTraffic.setFill(Paint.valueOf("#ababab"));
                yellowLightTraffic.setFill(Paint.valueOf("#ababab"));
                greenLightTraffic.setFill(Paint.valueOf("#05d721"));
                trafficLightGreen.setSelected(true);
                break;
            }
            case dark: {
                redLightTraffic.setFill(Paint.valueOf("#ababab"));
                yellowLightTraffic.setFill(Paint.valueOf("#ababab"));
                greenLightTraffic.setFill(Paint.valueOf("#ababab"));
                trafficLightDark.setSelected(true);
                break;
            }
            case allOn: {
                redLightTraffic.setFill(Paint.valueOf("#ff0000"));
                yellowLightTraffic.setFill(Paint.valueOf("#e8ff1f"));
                greenLightTraffic.setFill(Paint.valueOf("#05d721"));
                trafficLightAllOn.setSelected(true);
                break;
            }
        }
    }

    /**
     * getActTrafficLightState(): Returns the actual state from the trafficLight
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @return  enum actTrafficLightState
     */
    public trafficLightState getActTrafficLightState()
    {
        return actTrafficLightState;
    }


    /**
     * trafficLightStateChangeTimer(): Simulate the trafficLight
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
    public void trafficLightStateChangeTimer(String operation)
    {
        if(!operation.equals("stopp"))
        {
            trafficLightStateChangeTimer.setCycleCount(Animation.INDEFINITE);
            trafficLightStateChangeTimer.play();
        }
        else
        {
            trafficLightStateChangeTimer.stop();
        }

        // Need the operation into the trafficLightStateChangeTimerTick function.
        trafficLightOrder = operation;
    }


    /**
     * trafficLightStateChangeTimerTick(): Change in combination with the timer the lights from the trafficLight
     *
     * Every xxSeconds the methode trafficLightStateChangeTimerTick() is called from the timer.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     */
    public void trafficLightStateChangeTimerTick()
    {
        int actStateTrafficLight = 0;

        try {

            // Returns the from the actual trafficLight state the integer number from the enum
            actStateTrafficLight = trafficLightState.valueOf(getActTrafficLightState().toString()).ordinal();
            System.out.println("Act TrafficLight: " + actStateTrafficLight + " " + trafficLightState.values()[actStateTrafficLight]);

            switch (trafficLightOrder) {
                case "simulation": {
                    actStateTrafficLight++;
                    if(actStateTrafficLight >= trafficLightState.values().length)
                    {actStateTrafficLight = 0;}
                    changeTrafficLightColor(trafficLightState.values()[actStateTrafficLight]);
                    break;
                }
                case "flashYellow": {
                    if (!getActTrafficLightState().equals(trafficLightState.yellow)) {
                        changeTrafficLightColor(trafficLightState.yellow);
                    } else {
                        changeTrafficLightColor(trafficLightState.dark);
                    }
                    break;
                }
                case "switchToRed": {
                    if (!getActTrafficLightState().equals(trafficLightState.red)) {
                        changeTrafficLightColor(trafficLightState.values()[actStateTrafficLight+1]);
                    } else {
                        trafficLightStateChangeTimer.stop();
                    }
                    break;
                }
                case "switchToGreen": {
                    if (!getActTrafficLightState().equals(trafficLightState.green)) {
                        changeTrafficLightColor(trafficLightState.values()[actStateTrafficLight-1]);
                    } else {
                        trafficLightStateChangeTimer.stop();
                    }
                    break;
                }
                default: {
                    changeTrafficLightColor(trafficLightState.dark);
                    trafficLightStateChangeTimer.stop();
                    break;
                }
            }
        }catch (Exception e)
        {
            changeTrafficLightColor(trafficLightState.red);
        }

    }
}

