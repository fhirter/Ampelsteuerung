package crossroad;

import javafx.geometry.Point2D;
import traffic_lights.TrafficLightState;
import util.Area;
import util.Direction;
import util.Subject;
import util.Position;
import vehicles.Car;

import java.util.*;

/**
 * Crossroad Class. Holds 4 roads and any number of cars. Provides information if cars may change direction or should stop.
 *
 *
 * @author Schweizer Patrick, Grimm Raphael, Vogt Andreas, Reiter Daniel, Hirter Fabian
 */
public class Crossroad extends Subject {


    private final int roadWidth = 250;
    private final int roadLength = 300;
    private final Area turningArea = new Area(180, new Point2D(0,0));

    private final Map<Direction, Position> roadDirectionPositionMap = new HashMap<>();

    private Map<Direction, Road> roads = new HashMap<>();
    private List<Car> cars = new LinkedList<>();

    public Crossroad() {
        initializeDirectionPositionMap();
        generateRoads();
    }

    public void addVehicle(Car car) {
        cars.add(car);
        car.setCrossroad(this);
        notifyObservers();
    }

    public List<Car> getCars() {
        LinkedList<Car> cars = new LinkedList<>(this.cars);
        return cars;
    }

    public Road getRoad(Direction direction) {
        return roads.get(direction);
    }

    void setTrafficLightState(Direction direction, TrafficLightState state) {
        roads.get(direction).getTrafficLight().setState(state);
    }

    private void initializeDirectionPositionMap() {
        roadDirectionPositionMap.put(Direction.WEST, new Position(-roadLength - roadWidth/2, -roadWidth/2, 0));
        roadDirectionPositionMap.put(Direction.NORTH, new Position(roadWidth/2, -roadLength - roadWidth/2, 90));
        roadDirectionPositionMap.put(Direction.EAST, new Position(roadLength + roadWidth/2, roadWidth/2, 180));
        roadDirectionPositionMap.put(Direction.SOUTH, new Position(-roadWidth/2, roadLength + roadWidth/2, 270));
    }

    public boolean canITurn(Position position) {
        return turningArea.isInside(position);
    }

    public boolean canIDrive(Position position, Road currentRoad) {
        double angle = currentRoad.getPosition().getAngle();
        Position relativePosition = position.rotate(-1*angle, new Point2D(0,0));
        return currentRoad.canIDrive(relativePosition);
    }


    public Area getTurningArea() {
        return turningArea;
    }

    private void generateRoads() {
        Direction[] directions = Direction.values();
        for (int i = 0; i < directions.length; i++) {
            Road road = new Road(directions[i], roadDirectionPositionMap.get(directions[i]));
            roads.put(directions[i], road);
        }
    }
    

    public void calculatePositions(Double secondsElapsedCapped) {
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).drive(secondsElapsedCapped);
        }
    }

}


