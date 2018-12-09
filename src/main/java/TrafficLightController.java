import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import java.net.URL;
import java.util.ResourceBundle;


public class TrafficLightController implements Observer, Initializable
{
    @FXML   private Circle redLightTraffic;
    @FXML   private Circle yellowLightTraffic;
    @FXML   private Circle greenLightTraffic;
    @FXML   private Group symbolPedestrian;
    @FXML   private Group groupScaleFactor;

    final float scaleFactorCAR = (float) 0.6;
    final float scaleFactorPEDESTRIAN = (float) 0.4;
    final Paint redColor = Paint.valueOf("#ff0000");
    final Paint darkColor = Paint.valueOf("#ababab");
    final Paint yellowColor = Paint.valueOf("#e8ff1f");
    final Paint greenColor = Paint.valueOf("#05d721");

    private TrafficLightModel model;


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
     *
     * Is automatic called when fxmlLoader.load() ist called.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    27.11.2018
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setTypeAndScale(model.getType());

        changeColor(TrafficLightState.RED);
    }


    /**
     * update(): Obstacle where is registred into TrafficLightModel
     *
     * Is automatic called when something into trafficLightModel is changed.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    08.12.2018
     */
    @Override
    public void update()
    {
        TrafficLightState state = model.getState();
        changeColor(state);
    }


    /**
     * setTypeAndScale(): Change the type and scale factor from the trafficLight (CAR, PEDESTRIAN, BUS, etc).
     *
     * Sets the graphical group for pedestrian visible or unvisible.
     * Change depended of the type the scale factor from the trafficLight.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    08.12.2018
     * @arg     TrafficLightType type: (value: enum TrafficLightType)
     */
    public void setTypeAndScale(TrafficLightType type)
    {
        if(type == TrafficLightType.CAR)
        {
            symbolPedestrian.setVisible(false);
            groupScaleFactor.setScaleX((double)scaleFactorCAR);
            groupScaleFactor.setScaleY((double)scaleFactorCAR);
        }else if(type == TrafficLightType.PEDESTRIAN)
        {
            symbolPedestrian.setVisible(true);
            groupScaleFactor.setScaleX((double)scaleFactorPEDESTRIAN);
            groupScaleFactor.setScaleY((double)scaleFactorPEDESTRIAN);
        }
    }


    /**
     * changeColor(): Redraw the color from the trafficLight
     *
     * Depending the state from the trafficLight the color from the lights are changed.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    08.12.2018
     * @arg     TrafficLightState newState: (value: enum TrafficLightState)
     */
    public void changeColor(TrafficLightState newState)
    {
        switch(newState) {
            case RED: {
                redLightTraffic.setFill(redColor);
                yellowLightTraffic.setFill(darkColor);
                greenLightTraffic.setFill(darkColor);
                break;
            }
            case YELLOW_RED: {
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
            case ALLOn: {
                redLightTraffic.setFill(redColor);
                yellowLightTraffic.setFill(yellowColor);
                greenLightTraffic.setFill(greenColor);
                break;
            }
        }
    }
}
