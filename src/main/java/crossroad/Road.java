package crossroad;

import javafx.geometry.Point2D;
import traffic_lights.TrafficLight;
import util.Area;
import util.Observable;
import util.Position;

/**
 *
 * @autor Schweizer Patrick, Grimm Raphael, Vogt Andreas, Reiter Daniel, Hirter Fabian
 * @since  14.11.2018
 */
public class Road extends Observable {
    private boolean hasPedestrianStripes;
    private boolean hasBicycleLane;

    private final TrafficLight trafficLight;
    private boolean isVisible = true;

    private final Area stopArea;

    public Road() {
        trafficLight = new TrafficLight();
        stopArea = new Area(100, new Point2D(100,100)); // todo: generate correct stop areas
    }

    public boolean hasPedestrianStripes() {
        return hasPedestrianStripes;
    }

    public boolean hasBicycleLane() {
        return hasBicycleLane;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setHasPedestrianStripes(boolean hasPedestrianStripes) {
        this.hasPedestrianStripes = hasPedestrianStripes;
        notifyObservers();
    }

    public void setHasBicycleLane(boolean hasBicycleLane) {
        this.hasBicycleLane = hasBicycleLane;
        notifyObservers();
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public boolean canIDrive(Position position) {
        return stopArea.isInside(position);
    }
}








