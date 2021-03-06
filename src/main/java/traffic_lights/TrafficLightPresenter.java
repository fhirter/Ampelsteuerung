package traffic_lights;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import util.Observer;
import util.Position;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author Schweizer Patrick, Fabian Hirter
 */

public class TrafficLightPresenter extends AnchorPane implements Observer {
    @FXML private Circle redTrafficLight;
    @FXML private Circle yellowTrafficLight;
    @FXML private Circle greenTrafficLight;


    private final TrafficLightInterface trafficLight;
    private final Map<TrafficLightState, Paint[]> stateColorMap  = new HashMap<>();

    public TrafficLightPresenter(TrafficLightInterface trafficLight, Position position) throws IOException {
        this.trafficLight = trafficLight;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/trafficLightView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();

        // todo: fix traffic light position

        setLayoutX(position.getX());
        setLayoutY(position.getY());
        setRotate(position.getAngle());

        double scaleFactor = 0.8;
        setScaleX(scaleFactor);
        setScaleY(scaleFactor);

        initStateColorMap();

        changeColor(trafficLight.getState());

    }

    @Override
    public void update() {
        TrafficLightState newState = trafficLight.getState();
        changeColor(newState);
    }

    /**
     * Redraw the color of the trafficLight
     *
     * Depending the state of the trafficLight the color of the lights are changed.
     *
     * @param
     */
    public void changeColor(TrafficLightState newState) {
        Paint[] colors = stateColorMap.get(newState);

        redTrafficLight.setFill(colors[0]);
        yellowTrafficLight.setFill(colors[1]);
        greenTrafficLight.setFill(colors[2]);
    }

    private void initStateColorMap() {
        final Paint stop = Paint.valueOf("#ff0000");
        final Paint off = Paint.valueOf("#ababab");
        final Paint standby = Paint.valueOf("#e8ff1f");
        final Paint go = Paint.valueOf("#05d721");

        stateColorMap.put(TrafficLightState.RED, new Paint[] {stop, off, off});
        stateColorMap.put(TrafficLightState.YELLOW_RED, new Paint[] {stop, standby, off});
        stateColorMap.put(TrafficLightState.YELLOW, new Paint[] {off, standby, off});
        stateColorMap.put(TrafficLightState.GREEN, new Paint[] {off, off, go});
        stateColorMap.put(TrafficLightState.DARK, new Paint[] {stop, off, off});
        stateColorMap.put(TrafficLightState.ALL_ON, new Paint[] {stop, standby, go});
    }

}
