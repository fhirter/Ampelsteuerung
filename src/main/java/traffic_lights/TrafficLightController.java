package traffic_lights;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import util.Observer;
import util.Position;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class TrafficLightController extends AnchorPane implements Observer, Initializable
{
    @FXML   private Circle redLightTraffic;
    @FXML   private Circle yellowLightTraffic;
    @FXML   private Circle greenLightTraffic;
    @FXML   private Group symbolPedestrian;
    @FXML   private Group groupScaleFactor;

    final double scaleFactorCAR = 0.8;
    final double scaleFactorPEDESTRIAN = 0.6;
    final Paint redColor = Paint.valueOf("#ff0000");
    final Paint darkColor = Paint.valueOf("#ababab");
    final Paint yellowColor = Paint.valueOf("#e8ff1f");
    final Paint greenColor = Paint.valueOf("#05d721");

    private TrafficLight model;


    /**
     * traffic_lights.TrafficLightController(): Constructor
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    27.11.2018
     * @arg     traffic_lights.TrafficLight trafficLight: (Object form model class)
     */
    public TrafficLightController(TrafficLight model,Point2D refTrafficLights, Position position)
    {
        this.model = model;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayoutX(refTrafficLights.getX() + position.getX());
        setLayoutY(refTrafficLights.getY() + position.getY());
        setRotate(position.getAngle());

        if (model.getType() == TrafficLightType.CAR)
        {
            setScaleX(scaleFactorCAR);
            setScaleY(scaleFactorCAR);
        }
        else
        {
            setScaleX(scaleFactorPEDESTRIAN);
            setScaleY(scaleFactorPEDESTRIAN);
        }
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
     * update(): Obstacle where is registred into traffic_lights.TrafficLight
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
        TrafficLightState newState = model.getState();
        changeColor(newState);

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
     * @arg     traffic_lights.TrafficLightType type: (value: enum traffic_lights.TrafficLightType)
     */
    public void setTypeAndScale(TrafficLightType type)
    {
        if(type == TrafficLightType.CAR)
        {
            symbolPedestrian.setVisible(false);
            groupScaleFactor.setScaleX(scaleFactorCAR);
            groupScaleFactor.setScaleY(scaleFactorCAR);
        }else if(type == TrafficLightType.PEDESTRIAN)
        {
            symbolPedestrian.setVisible(true);
            groupScaleFactor.setScaleX(scaleFactorPEDESTRIAN);
            groupScaleFactor.setScaleY(scaleFactorPEDESTRIAN);
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
     * @arg     traffic_lights.TrafficLightState newState: (value: enum traffic_lights.TrafficLightState)
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
