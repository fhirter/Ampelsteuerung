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
    private final TrafficLight trafficLight;
    private final Area stopArea;
    private boolean hasPedestrianStripes;
    private boolean hasBicycleLane;
    private boolean isVisible = true;

    public Road() {
        trafficLight = new TrafficLight();
        stopArea = new Area(10, 46, new Point2D(130,150));
    }

    public Area getStopArea() {
        return stopArea;
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








