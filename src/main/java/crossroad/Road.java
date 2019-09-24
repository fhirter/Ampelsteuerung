package crossroad;

import javafx.geometry.Point2D;
import traffic_lights.TrafficLight;
import traffic_lights.TrafficLightState;
import util.Area;
import util.Direction;
import util.Subject;
import util.Position;

/**
 *
 * package private class
 *
 * @autor Schweizer Patrick, Grimm Raphael, Vogt Andreas, Reiter Daniel, Hirter Fabian
 * @since  14.11.2018
 */
public class Road extends Subject {
    private final TrafficLight trafficLight;
    private final Area stopArea;

    private final Position position;
    private final Direction direction;

    private boolean isVisible = true;

    Road(Direction direction, Position position) {
        this.position = position;
        this.direction = direction;
        trafficLight = new TrafficLight();
        stopArea = new Area(10, 45, new Point2D(-270,27)); // relative to crossroad center
    }

    Area getStopArea() {
        return stopArea;
    }

    boolean isVisible() {
        return this.isVisible;
    }

    TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public boolean canIDrive(Position position) {
        boolean isRed = (trafficLight.getState() == TrafficLightState.RED);
        boolean isInside = stopArea.isInside(position);
        return !(isInside && isRed);
    }


    public Position getPosition() {
        return position;
    }
}








