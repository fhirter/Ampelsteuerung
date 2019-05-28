package crossroad;

import javafx.geometry.Point2D;
import traffic_lights.TrafficLight;
import traffic_lights.TrafficLightState;
import util.Direction;
import util.Observable;
import util.Position;
import vehicles.Vehicle;

import java.util.*;


/**
 *
 * @author Schweizer Patrick, Grimm Raphael, Vogt Andreas, Reiter Daniel, Hirter Fabian
 */
public class Crossroad extends Observable {
    private final Point2D referencePoint = new Point2D(650, 450);
    private final int roadWidth = 250;
    private final int roadLength = 300;
    private final Area turningArea = new Area(180, referencePoint);

    private Map<Direction, Road> roads = new HashMap<>();
    private List<Vehicle> vehicles = new LinkedList<>();

    public Crossroad() {
        generateRoads();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        notifyObservers();
    }

    public Point2D getReferencePoint() {
        return referencePoint;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public int getRoadWidth() {
        return roadWidth;
    }

    public int getRoadLength() {
        return roadLength;
    }

    public Road getRoad(Direction direction) {
        return roads.get(direction);
    }

    public void setTrafficLightState(Direction direction, TrafficLightState state) {
        roads.get(direction).getTrafficLight().setState(state);
    }

    public boolean canITurn(Position position) {
        return turningArea.isInside(position);
    }

    private void generateRoads() {
        Direction[] directions = Direction.values();
        for (int i = 0; i < directions.length; i++) {
            Road road = new Road();
            roads.put(directions[i], road);

            List<TrafficLight> trafficLights = new LinkedList<>();

            trafficLights.add(new TrafficLight());
        }
    }

    public void calculatePositions(Double secondsElapsedCapped) {
        for (int i = 0; i < vehicles.size(); i++) {
            vehicles.get(i).setNewPosition(secondsElapsedCapped);
        }
    }

    private class Area {
        private int size;
        private Point2D center;


        public Area(int size, Point2D center) {
            this.size = size;
            this.center = center;
        }

        public boolean isInside(Position position) {
            final double x = position.getX();
            final double y = position.getY();

            final double centerX = center.getX();
            final double centerY = center.getY();

            if (x > (centerX - size / 2) && x < (centerX + size / 2)) {
                if (y > (centerY - size / 2) && y < (centerY + size / 2)) {
                    return true;
                }
            }
            return false;
        }
    }
}


