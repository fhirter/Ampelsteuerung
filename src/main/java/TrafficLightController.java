import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class TrafficLightController implements Observer, Initializable
{
    @FXML   private Circle redLightTraffic;
    @FXML   private Circle yellowLightTraffic;
    @FXML   private Circle greenLightTraffic;
    @FXML   private Group symbolPedestrian;
    @FXML   private Group groupScaleFactor;

    final double scaleFactorCAR = 0.6;
    final double scaleFactorPEDESTRIAN = 0.4;
    final Paint redColor = Paint.valueOf("#ff0000");
    final Paint darkColor = Paint.valueOf("#ababab");
    final Paint yellowColor = Paint.valueOf("#e8ff1f");
    final Paint greenColor = Paint.valueOf("#05d721");

    private TrafficLightState actState;
    private TrafficLightModel model;
    private TrafficLightType type;
    private enum order {simulation, flashYellow, switchToRed, switchToGreen, timerStopp}

    private order operationTimer;

    private Timeline stateChangeTimer = new Timeline(new KeyFrame(
            Duration.millis(500),
            ae -> stateChangeTimerTick(operationTimer)));


    /**
     * TrafficLightController(): Constructor
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    27.11.2018
     * @arg
     */
    public TrafficLightController(TrafficLightModel trafficLightModel)
    {
        model = trafficLightModel;
    }

    /**
     * initialize(URL location, ResourceBundle resources): Initialize during startUp all settings from the trafficLight
     *      *
     * Is automatic called when fxmlLoader.load() ist called.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    27.11.2018
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type = model.getType();
        setType(type);

        if(type == TrafficLightType.CAR)
        {
            setScaleFactor(scaleFactorCAR);
        }else if(type == TrafficLightType.PEDESTRIAN)
        {
            setScaleFactor(scaleFactorPEDESTRIAN);
        }else
        {
            setScaleFactor(0.5);
        }
    }


    /**
     * update(): Obstacle where is registred into TrafficLightModel
     *
     * Is automatic called when something into trafficLightModel is changed.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    27.11.2018
     */
    @Override
    public void update()
    {
        TrafficLightState state = model.getState();

        stateChangeTimer.stop();

        if(state == TrafficLightState.RED)
        {
            stateChangeTimer(order.switchToRed);
        }else if(state == TrafficLightState.GREEN)
        {
            stateChangeTimer(order.switchToGreen);
        }else if(state == TrafficLightState.YELLOW)
        {
            stateChangeTimer(order.flashYellow);
        }else if(state == TrafficLightState.ALLON)
        {
            stateChangeTimer(order.simulation);
        }else
        {
            changeColor(state);
        }
    }


    /**
     * setScaleFactor(): Set the scaleFactor from the trafficLight.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    12.11.2018
     * @arg     enum type
     * @arg     double scaleFactor: (value: 0.1 to 1)
     */
    public void setScaleFactor(double scaleFactor)
    {
        groupScaleFactor.setScaleX(scaleFactor);
        groupScaleFactor.setScaleY(scaleFactor);
    }


    /**
     * setType(): Change the type for the trafficLight (CAR, PEDESTRIAN, BUS, etc).
     *
     * Set the graphical group for pedestrian visible or unvisible.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     TrafficLightType type: (value: enum TrafficLightType)
     */
    public void setType(TrafficLightType type)
    {
        if(type == TrafficLightType.CAR)
        {
            symbolPedestrian.setVisible(false);
        }else if(type == TrafficLightType.PEDESTRIAN)
        {
            symbolPedestrian.setVisible(true);
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
     * @arg     TrafficLightState newState: (value: enum TrafficLightState)
     */
    public void changeColor(TrafficLightState newState)
    {
        actState = newState;

        switch(actState) {
            case RED: {
                redLightTraffic.setFill(redColor);
                yellowLightTraffic.setFill(darkColor);
                greenLightTraffic.setFill(darkColor);
                break;
            }
            case YELLOWRED: {
                redLightTraffic.setFill(redColor);
                yellowLightTraffic.setFill(yellowColor);
                greenLightTraffic.setFill(darkColor);
                break;
            }
            case YELLOW: {
                redLightTraffic.setFill(darkColor);
                yellowLightTraffic.setFill(yellowColor);
                greenLightTraffic.setFill(darkColor);
                break;
            }
            case GREEN: {
                redLightTraffic.setFill(darkColor);
                yellowLightTraffic.setFill(darkColor);
                greenLightTraffic.setFill(greenColor);
                break;
            }
            case DARK: {
                redLightTraffic.setFill(darkColor);
                yellowLightTraffic.setFill(darkColor);
                greenLightTraffic.setFill(darkColor);
                break;
            }
            case ALLON: {
                redLightTraffic.setFill(redColor);
                yellowLightTraffic.setFill(yellowColor);
                greenLightTraffic.setFill(greenColor);
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
     * @return  TrafficLightState: (value: enum TrafficLightState)
     */
    public TrafficLightState getActState()
    {
        return actState;
    }


    /**
     * stateChangeTimer(): Simulate the trafficLight
     *
     * Initialize a new continous timer.
     * Every xxSeconds the method timerTick() is called.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     * @arg     String operation: (value: enum operation)
     */
    public void stateChangeTimer(order operation)
    {
        this.operationTimer = operation;
        if(operation != order.timerStopp)
        {
            stateChangeTimer.setCycleCount(Animation.INDEFINITE);
            stateChangeTimer.play();
        }
        else
        {
            stateChangeTimer.stop();
        }
    }


    /**
     * stateChangeTimerTick(): Change in combination with the timer the lights from the trafficLight
     *
     * Every xxSeconds the method stateChangeTimerTick() is called from the timer.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.11.2018
     */
    public void stateChangeTimerTick(order operationTimer)
    {
        int actState = 0;

        try {

            // Returns the from the actual trafficLight state the integer number from the enum
            actState = TrafficLightState.valueOf(getActState().toString()).ordinal();
            System.out.println("Act TrafficLight: " + actState + " " + TrafficLightState.values()[actState]);

            switch (operationTimer) {
                case simulation: {
                    actState++;
                    if(actState >= TrafficLightState.values().length)
                    {actState = 0;}
                    changeColor(TrafficLightState.values()[actState]);
                    break;
                }
                case flashYellow: {
                    if (!getActState().equals(TrafficLightState.YELLOW)) {
                        changeColor(TrafficLightState.YELLOW);
                    } else {
                        changeColor(TrafficLightState.DARK);
                    }
                    break;
                }
                case switchToRed: {
                    if (!getActState().equals(TrafficLightState.RED)) {
                        changeColor(TrafficLightState.values()[actState+1]);
                    } else {
                        stateChangeTimer.stop();
                    }
                    break;
                }
                case switchToGreen: {
                    if (!getActState().equals(TrafficLightState.GREEN)) {
                        changeColor(TrafficLightState.values()[actState-1]);
                    } else {
                        stateChangeTimer.stop();
                    }
                    break;
                }
                default: {
                    changeColor(TrafficLightState.DARK);
                    stateChangeTimer.stop();
                    break;
                }
            }
        }catch (Exception e)
        {
            changeColor(TrafficLightState.RED);
        }

    }
}
