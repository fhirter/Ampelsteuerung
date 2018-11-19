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


    private final Paint redColor = Paint.valueOf("#ff0000");
    private final Paint darkColor = Paint.valueOf("#ababab");
    private final Paint yellowColor = Paint.valueOf("#e8ff1f");
    private final Paint greenColor = Paint.valueOf("#05d721");

    private TrafficLightState actState;
    private Timeline stateChangeTimer = new Timeline(new KeyFrame(
            Duration.millis(500),
            ae -> stateChangeTimerTick()));
    private String order = "";

    private TrafficLightModel model = new TrafficLightModel();


    /**
     * setModel(): Set the instance from the model (gui)
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    17.11.2018
     * @arg     TrafficLightModel
     */
    public void setModel(TrafficLightModel model)
    {
        this.model = model;
    }


    /**
     * update(): Obstacle where is registred into TrafficLightModel
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     */
    @Override
    public void update()

    {
        changeColor((TrafficLightState));

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
            model.setType(TrafficLightType.CAR);
        }
        else if(typePedestrian.isSelected())
        {
            model.setType(TrafficLightType.PEDESTRIAN);
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
        model.setScaleFactor(scaleFactor.getValue());
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
            model.setRed();
        }
        else if(yellowRed.isSelected())
        {
            model.setYellowRed();
        }
        else if(yellow.isSelected())
        {
            model.setYellow();
        }
        else if(green.isSelected())
        {
            model.setGreen();
        }
        else if(dark.isSelected())
        {
            model.setDark();
        }
        else if(allOn.isSelected())
        {
            model.setAllOn();
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
     * setScaleFactor(): Set the scale factor from the trafficLight.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    12.11.2018
     * @arg     enum type
     * @arg     double scaleFactor
     */
    private void setScaleFactor(double scaleFactor)
    {
        groupScaleFactor.setScaleX(scaleFactor);
        groupScaleFactor.setScaleY(scaleFactor);
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
    private void setType(Boolean type)
    {
        symbolPedestrian.setVisible(type);
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
    private void changeColor(TrafficLightState newState)
    {
        actState = newState;

        switch(actState) {
            case RED: {
                redLightTraffic.setFill(redColor);
                yellowLightTraffic.setFill(darkColor);
                greenLightTraffic.setFill(darkColor);
                red.setSelected(true);
                break;
            }
            case YELLOWRED: {
                redLightTraffic.setFill(redColor);
                yellowLightTraffic.setFill(yellowColor);
                greenLightTraffic.setFill(darkColor);
                yellowRed.setSelected(true);
                break;
            }
            case YELLOW: {
                redLightTraffic.setFill(darkColor);
                yellowLightTraffic.setFill(yellowColor);
                greenLightTraffic.setFill(darkColor);
                yellow.setSelected(true);
                break;
            }
            case GREEN: {
                redLightTraffic.setFill(darkColor);
                yellowLightTraffic.setFill(darkColor);
                greenLightTraffic.setFill(greenColor);
                green.setSelected(true);
                break;
            }
            case DARK: {
                redLightTraffic.setFill(darkColor);
                yellowLightTraffic.setFill(darkColor);
                greenLightTraffic.setFill(darkColor);
                dark.setSelected(true);
                break;
            }
            case ALLON: {
                redLightTraffic.setFill(redColor);
                yellowLightTraffic.setFill(yellowColor);
                greenLightTraffic.setFill(greenColor);
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
    private TrafficLightState getActState()
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
    private void stateChangeTimer(String operation)
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
    private void stateChangeTimerTick()
    {
        int actState;

        try {

            // Returns the from the actual trafficLight state the integer number from the enum
            actState = TrafficLightState.valueOf(getActState().toString()).ordinal();
            System.out.println("Act TrafficLight: " + actState + " " + TrafficLightState.values()[actState]);

            switch (order) {
                case "simulation": {
                    actState++;
                    if(actState >= TrafficLightState.values().length)
                    {actState = 0;}
                    changeColor(TrafficLightState.values()[actState]);
                    break;
                }
                case "flashYellow": {
                    if (!getActState().equals(TrafficLightState.YELLOW)) {
                        changeColor(TrafficLightState.YELLOW);
                    } else {
                        changeColor(TrafficLightState.DARK);
                    }
                    break;
                }
                case "switchToRed": {
                    if (!getActState().equals(TrafficLightState.RED)) {
                        changeColor(TrafficLightState.values()[actState+1]);
                    } else {
                        stateChangeTimer.stop();
                    }
                    break;
                }
                case "switchToGreen": {
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
