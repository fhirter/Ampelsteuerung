package crossroad;

import javafx.geometry.Point2D;
import traffic_lights.TrafficLightState;
import util.Area;
import util.Direction;
import util.Subject;
import util.Position;
import vehicles.Vehicle;

import java.util.*;

/**
 * Crossroad Class. Holds 4 roads and any number of cars. Provides information if cars may change direction or should stop.
 *
 *
 * @author Schweizer Patrick, Grimm Raphael, Vogt Andreas, Reiter Daniel, Hirter Fabian
 */
public class Crossroad extends Subject implements Context {

    private final Area turningArea = new Area(180, new Point2D(0,0));

    private final Map<Direction, Position> roadDirectionPositionMap = new HashMap<>();

    private Map<Direction, Road> roads = new HashMap<>();
    private List<Vehicle> vehicles = new LinkedList<>();

    public Crossroad() {
        generateRoads();
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        vehicle.setContext(this);
        notifyObservers();
    }

    @Override
    public List<Vehicle> getVehicles() {
        return new LinkedList<>(this.vehicles);
    }

    public Road getRoad(Direction direction) {
        return roads.get(direction);
    }

    public Map<Direction,Road> getRoads() {
        return new HashMap<>(roads);
    }

    void setTrafficLightState(Direction direction, TrafficLightState state) {
        roads.get(direction).getTrafficLight().setState(state);
    }

    @Override
    public boolean canITurn(Position position) {
        return turningArea.isInside(position);
    }

    @Override
    public boolean canIDrive(Position position, Road currentRoad) {
        double roadOrientation = currentRoad.getPosition().getAngle();

        Position relativePosition = new Position(position.subtract(currentRoad.getPosition()),0);

        relativePosition = relativePosition.rotate(-1*roadOrientation, new Point2D(0,0));
        return currentRoad.canIDrive(relativePosition);
    }

    @Override
    public Area getTurningArea() {
        return turningArea;
    }

    @Override
    public void calculatePositions(Double secondsElapsedCapped) {
        for (int i = 0; i < vehicles.size(); i++) {
            vehicles.get(i).drive(secondsElapsedCapped);
        }
    }

    private Position getRoadPosition(Direction direction, int width, int length) {
        //direction
        // East 0
        // South 90
        // West 180
        // North 270

        int angle = (int) direction.getAngle();

        int signY = (int) Math.round(-1*(Math.cos(2*Math.PI*(angle+90)/360) + Math.sin(2*Math.PI*(angle+90)/360)));      // -1,+1,+1,-1
        int signX = (int) (-Math.round(-1*(Math.cos(2*Math.PI*(angle)/360) + Math.sin(2*Math.PI*(angle)/360))));      // -1,-1,+1,+1

        return new Position(signX*width/2, signY*width/2, angle);
    }

    private void generateRoads() {
        //todo get effective road size
        int roadWidth = 250;
        int roadLength = 300;

        Direction[] directions = Direction.values();
        for (int i = 0; i < directions.length; i++) {
            Direction direction = directions[i];
            if(direction == direction) {
                Position position = getRoadPosition(direction, roadWidth, roadLength);
                Road road = new Road(directions[i], position);

                roads.put(directions[i], road);
            }
        }
    }

}


