package crossroad;

import javafx.geometry.Point2D;
import traffic_lights.TrafficLight;
import traffic_lights.TrafficLightState;
import util.Area;
import util.Direction;
import util.Observable;
import util.Position;
import vehicles.Vehicle;

import java.util.*;

/**
 * Crossroad Class. Holds 4 roads and any number of vehicles. Provides information if vehicles may change direction or should stop.
 *
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

    List<Vehicle> getVehicles() {
        return vehicles;
    }

    int getRoadWidth() {
        return roadWidth;
    }

    int getRoadLength() {
        return roadLength;
    }

    Road getRoad(Direction direction) {
        return roads.get(direction);
    }

    void setTrafficLightState(Direction direction, TrafficLightState state) {
        roads.get(direction).getTrafficLight().setState(state);
    }

    public boolean canITurn(Position position) {
        return turningArea.isInside(position);
    }

    public boolean canIDrive(Position position) {
        for (Map.Entry<Direction, Road> entry : roads.entrySet()) {
            if(entry.getValue().canIDrive(position)) {
                return true;
            }
        }
        return false;
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
            vehicles.get(i).drive(secondsElapsedCapped);
        }
    }

}


