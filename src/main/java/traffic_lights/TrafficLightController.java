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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 *
 * @author Schweizer Patrick
 */

public class TrafficLightController extends AnchorPane implements Observer {
    @FXML private Circle redLightTraffic;
    @FXML private Circle yellowLightTraffic;
    @FXML private Circle greenLightTraffic;
    @FXML private Group symbolPedestrian;
    @FXML private Group groupScaleFactor;

    private final TrafficLight trafficLight;

    public TrafficLightController(TrafficLight trafficLight, Point2D refTrafficLights, Position position) {
        this.trafficLight = trafficLight;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/trafficLightView.fxml"));
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

        double scaleFactor = 0.8;
        setScaleX(scaleFactor);
        setScaleY(scaleFactor);

        changeColor(trafficLight.getState());

    }

    @Override
    public void update() {
        TrafficLightState newState = trafficLight.getState();
        changeColor(newState);
    }

    /**
     * Redraw the color of the trafficLight
     * <p>
     * Depending the state of the trafficLight the color of the lights are changed.
     *
     * todo: rewrite state machine
     *
     * @param
     */
    public void changeColor(TrafficLightState newState) {
        final Paint stop = Paint.valueOf("#ff0000");
        final Paint off = Paint.valueOf("#ababab");
        final Paint standby = Paint.valueOf("#e8ff1f");
        final Paint go = Paint.valueOf("#05d721");

        switch (newState) {
            case RED: {
                redLightTraffic.setFill(stop);
                yellowLightTraffic.setFill(off);
                greenLightTraffic.setFill(off);
                break;
            }
            case YELLOW_RED: {
                redLightTraffic.setFill(stop);
                yellowLightTraffic.setFill(standby);
                greenLightTraffic.setFill(off);
                break;
            }
            case YELLOW: {
                redLightTraffic.setFill(off);
                yellowLightTraffic.setFill(standby);
                greenLightTraffic.setFill(off);
                break;
            }
            case GREEN: {
                redLightTraffic.setFill(off);
                yellowLightTraffic.setFill(off);
                greenLightTraffic.setFill(go);
                break;
            }
            case DARK: {
                redLightTraffic.setFill(off);
                yellowLightTraffic.setFill(off);
                greenLightTraffic.setFill(off);
                break;
            }
            case ALLOn: {
                redLightTraffic.setFill(stop);
                yellowLightTraffic.setFill(standby);
                greenLightTraffic.setFill(go);
                break;
            }
        }
    }

}
