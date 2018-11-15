package com.company;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class TrafficLightController implements Observer
{
    @FXML   private Circle redLightTraffic;
    @FXML   private Circle yellowLightTraffic;
    @FXML   private Circle greenLightTraffic;
    @FXML   private RadioButton red;
    @FXML   private RadioButton yellowRed;
    @FXML   private RadioButton yellow;
    @FXML   private RadioButton green;
    @FXML   private RadioButton dark;
    @FXML   private RadioButton allOn;
    @FXML   private RadioButton typeCar;
    @FXML   private RadioButton typePedestrian;
    @FXML   private Group symbolPedestrian;
    @FXML   private Group groupScaleFactor;
    @FXML   private CheckBox simulationOnOff;
    @FXML   private CheckBox flashYellow;
    @FXML   private CheckBox stateToRed;
    @FXML   private CheckBox stateToGreen;
    @FXML   private Slider scaleFactor;


    private trafficLightState actState;
    private Timeline stateChangeTimer = new Timeline(new KeyFrame(
            Duration.millis(500),
            ae -> stateChangeTimerTick()));
    private String order = "";


    /**
     * TrafficLightController():
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    14.11.2018
     */
    public TrafficLightController()
    {

    }


    /**
     * update(): Obstacle where ist registred into TrafficLightModel
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    13.11.2018
     */
    @Override
    public void update()
    {
        System.out.println("Obstacle wurde aktiviert.");
    }


    /***************************** Methodes where are called from GUI **************************************************************************

     /**
     * setType(): Called when the radioButtons (type) are changed
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     ActionEvent actionEvent
     */
    public void changeType(ActionEvent actionEvent)
    {
        if(typeCar.isSelected())
        {
            setType(trafficLightType.car);
        }
        else if(typePedestrian.isSelected())
        {
            setType(trafficLightType.pedestrian);
        }
    }


    /**
     * changeScaleFactor(): Change the scale Factor from the trafficLight
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     ActionEvent actionEvent
     */
    public void changeScaleFactor(MouseEvent mouseEvent)
    {
        setScaleFactor(scaleFactor.getValue());
    }


    /**
     * changeColor(): Called when the radioButtons (state) are changed
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     ActionEvent actionEvent
     */
    public void changeColor(ActionEvent actionEvent)
    {
        if(red.isSelected())
        {
            changeColor(trafficLightState.red);
        }
        else if(yellowRed.isSelected())
        {
            changeColor(trafficLightState.yellowRed);
        }
        else if(yellow.isSelected())
        {
            changeColor(trafficLightState.yellow);
        }
        else if(green.isSelected())
        {
            changeColor(trafficLightState.green);
        }
        else if(dark.isSelected())
        {
            changeColor(trafficLightState.dark);
        }
        else if(allOn.isSelected())
        {
            changeColor(trafficLightState.allOn);
        }
    }


    /**
     * startChangeColor(): Start the timer based change from the trafficLight.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     ActionEvent actionEvent
     */
    public void startChangeColor(ActionEvent actionEvent)
    {
        if(stateToRed.isSelected())
        {
            stateChangeTimer("switchToRed");
        }
        else if(stateToGreen.isSelected())
        {
            stateChangeTimer("switchToGreen");
        }
        else if(flashYellow.isSelected())
        {
            stateChangeTimer("flashYellow");
        }
        else
        {
            stateChangeTimer("timerStopp");
        }
    }


    /**
     * runSimulation(): Start or stops the simulation
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     ActionEvent actionEvent
     */
    public void runSimulation(ActionEvent actionEvent)
    {
        if(simulationOnOff.isSelected())
        {
            stateChangeTimer("simulation");
        }
        else
        {
            stateChangeTimer("timerStopp");
        }
    }


    /***************************** Own methodes from the class **************************************************************************

     /**
     * setSettings(): setAllSettings for the trafficlight.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    12.11.2018
     * @arg     enum type
     * @arg     double scaleFactor
     */
    public void setSettings(trafficLightType newType, double scaleFactor)
    {
        setScaleFactor(scaleFactor);

        setType(newType);
    }


    /**
     * setScaleFactor(): Set the scale factor from the trafficLight.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    12.11.2018
     * @arg     enum type
     * @arg     double scaleFactor
     */
    public void setScaleFactor(double scaleFactor)
    {
        if(scaleFactor > 0.1) {
            groupScaleFactor.setScaleX(scaleFactor);
            groupScaleFactor.setScaleY(scaleFactor);
        }
    }


    /**
     * settype(): Change the type for the trafficlight.
     *
     * Set the graphical group for pedestrian visible or unvisible.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     enum type
     */
    public void setType(trafficLightType newType)
    {
        switch(newType)
        {
            case car: {
                symbolPedestrian.setVisible(false);
                break;
            }
            case pedestrian: {
                symbolPedestrian.setVisible(true);
                break;
            }
        }
    }


    /**
     * changeColor(): Redraw the color from the trafficLight
     *
     * Depending the state from the trafficLight the color from the lights are changed.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     enum state
     */
    public void changeColor(trafficLightState newState)
    {
        actState = newState;

        switch(actState) {
            case red: {
                redLightTraffic.setFill(Paint.valueOf("#ff0000"));
                yellowLightTraffic.setFill(Paint.valueOf("#ababab"));
                greenLightTraffic.setFill(Paint.valueOf("#ababab"));
                red.setSelected(true);
                break;
            }
            case yellowRed: {
                redLightTraffic.setFill(Paint.valueOf("#ff0000"));
                yellowLightTraffic.setFill(Paint.valueOf("#e8ff1f"));
                greenLightTraffic.setFill(Paint.valueOf("#ababab"));
                yellowRed.setSelected(true);
                break;
            }
            case yellow: {
                redLightTraffic.setFill(Paint.valueOf("#ababab"));
                yellowLightTraffic.setFill(Paint.valueOf("#e8ff1f"));
                greenLightTraffic.setFill(Paint.valueOf("#ababab"));
                yellow.setSelected(true);
                break;
            }
            case green: {
                redLightTraffic.setFill(Paint.valueOf("#ababab"));
                yellowLightTraffic.setFill(Paint.valueOf("#ababab"));
                greenLightTraffic.setFill(Paint.valueOf("#05d721"));
                green.setSelected(true);
                break;
            }
            case dark: {
                redLightTraffic.setFill(Paint.valueOf("#ababab"));
                yellowLightTraffic.setFill(Paint.valueOf("#ababab"));
                greenLightTraffic.setFill(Paint.valueOf("#ababab"));
                dark.setSelected(true);
                break;
            }
            case allOn: {
                redLightTraffic.setFill(Paint.valueOf("#ff0000"));
                yellowLightTraffic.setFill(Paint.valueOf("#e8ff1f"));
                greenLightTraffic.setFill(Paint.valueOf("#05d721"));
                allOn.setSelected(true);
                break;
            }
        }
    }

    /**
     * getActState(): Returns the actual state from the trafficLight
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @return  enum actState
     */
    public trafficLightState getActState()
    {
        return actState;
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
    public void stateChangeTimer(String operation)
    {
        if(!operation.equals("timerStopp"))
        {
            stateChangeTimer.setCycleCount(Animation.INDEFINITE);
            stateChangeTimer.play();
        }
        else
        {
            stateChangeTimer.stop();
        }

        // Need the operation into the trafficLightStateChangeTimerTick function.
        order = operation;
    }


    /**
     * stateChangeTimerTick(): Change in combination with the timer the lights from the trafficLight
     *
     * Every xxSeconds the methode trafficLightStateChangeTimerTick() is called from the timer.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     */
    public void stateChangeTimerTick()
    {
        int actState = 0;

        try {

            // Returns the from the actual trafficLight state the integer number from the enum
            actState = trafficLightState.valueOf(getActState().toString()).ordinal();
            System.out.println("Act TrafficLight: " + actState + " " + trafficLightState.values()[actState]);

            switch (order) {
                case "simulation": {
                    actState++;
                    if(actState >= trafficLightState.values().length)
                    {actState = 0;}
                    changeColor(trafficLightState.values()[actState]);
                    break;
                }
                case "flashYellow": {
                    if (!getActState().equals(trafficLightState.yellow)) {
                        changeColor(trafficLightState.yellow);
                    } else {
                        changeColor(trafficLightState.dark);
                    }
                    break;
                }
                case "switchToRed": {
                    if (!getActState().equals(trafficLightState.red)) {
                        changeColor(trafficLightState.values()[actState+1]);
                    } else {
                        stateChangeTimer.stop();
                    }
                    break;
                }
                case "switchToGreen": {
                    if (!getActState().equals(trafficLightState.green)) {
                        changeColor(trafficLightState.values()[actState-1]);
                    } else {
                        stateChangeTimer.stop();
                    }
                    break;
                }
                default: {
                    changeColor(trafficLightState.dark);
                    stateChangeTimer.stop();
                    break;
                }
            }
        }catch (Exception e)
        {
            changeColor(trafficLightState.red);
        }

    }
}
