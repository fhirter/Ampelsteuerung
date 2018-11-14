
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

import static java.awt.Color.green;
import static java.sql.Types.NULL;

public class TrafficLightController implements Observer
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
    @FXML   private Group trafficLightGuiGroup;
    @FXML   private CheckBox simulationOnOff;
    @FXML   private CheckBox flashYellow;
    @FXML   private CheckBox stateToRed;
    @FXML   private CheckBox stateToGreen;
    @FXML   private Slider scaleFactor;

    private enum trafficLightState {green, yellow, yellowRed, red, dark, allOn;};

    @Override
    public void update() {

    }

    /**
     * setTrafficLightScaleFactor(): Set the scale factor from the trafficLight.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    12.11.2018
     * @arg     enum trafficLightType
     * @arg     double scaleFactor
     */
    public void setTrafficLightScaleFactor(double scaleFactor)
    {
        if(scaleFactor > 0.1) {
            trafficLightGuiGroup.setScaleX(scaleFactor);
            trafficLightGuiGroup.setScaleY(scaleFactor);
        }
    }


    /***************************** Methodes where are called from GUI **************************************************************************

     /**
     * trafficLightSetType(): Called when the radioButtons (trafficLight type) are changed
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     ActionEvent actionEvent
     */
/*
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
*/

    /**
     * changeColor(): Redraw the color from the trafficLight
     *
     * Depending the state from the trafficLight the color from the lights are changed.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     enum trafficLightState
     */
    /*
    public void changeColor(TrafficLight.trafficLightState newTrafficLightState)
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
*/
    /**
     * changeTrafficLightScaleFactor(): Change the scale Factor from the GUI trafficLight
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     ActionEvent actionEvent
     */
    public void changeTrafficLightScaleFactor(MouseEvent mouseEvent)
    {
        setTrafficLightScaleFactor(scaleFactor.getValue());
    }


    /**
     * trafficLightChangeColor(): Called when the radioButtons (trafficLight state) are changed
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     ActionEvent actionEvent
     */
    /*
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
*/

    /**
     * startChangeTrafficLightColor(): Start the timer based change from the trafficLight.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     ActionEvent actionEvent
     */
    /*
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
            trafficLightStateChangeTimer("timerStopp");
        }
    }
*/

    /**
     * trafficLightRunSimulation(): Start or stops the simulation
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     ActionEvent actionEvent
     */

  /*
    public void trafficLightRunSimulation(ActionEvent actionEvent)
    {
        if(simulationOnOff.isSelected())
        {
            trafficLightStateChangeTimer("simulation");
        }
        else
        {
            trafficLightStateChangeTimer("timerStopp");
        }
    }

*/

}
