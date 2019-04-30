package crossroad;

import javafx.geometry.Point2D;
import traffic_lights.TrafficLight;
import traffic_lights.TrafficLightState;
import traffic_lights.TrafficLightType;
import util.Direction;
import util.Observable;
import util.Position;
import vehicles.Vehicle;

import java.util.*;


public class Crossroad extends Observable {
    private final Point2D referencePoint = new Point2D(650, 450);
    private Integer roadCount = 4;
    private int roadWidth = 250;
    private int roadLength = 300;

    private Map<Direction, Road> roads = new HashMap<>();

    private Area turningArea = new Area(180, referencePoint);

    private List<Vehicle> vehicles = new LinkedList<>();

    private int vehicleCount = 1;

    private class Area {
        private int size;
        private Point2D center;


        public Area(int size, Point2D center) {
            this.size = size;
            this.center = center;
        }

        ; // just use default values

        public boolean isInside(Position position) {
            if (position.getX() > (center.getX() - size / 2) && position.getX() < (center.getX() + size / 2)) {
                if (position.getY() > (center.getY() - size / 2) && position.getY() < (center.getY() + size / 2)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * crossroad.Crossroad: Constructor
     *
     * @version 1.0
     * @autor NIN Class
     * @date 02.08.2018
     */

    public Crossroad() {
        // Loop to create all roads
        Direction[] directions = Direction.values();
        for (int i = 0; i < directions.length; i++) {
            Road road = new Road();
            roads.put(directions[i], road);

            Map<TrafficLightType, TrafficLight> trafficLights = new HashMap<>();

            trafficLights.put(TrafficLightType.CAR, new TrafficLight(TrafficLightType.CAR));
            trafficLights.put(TrafficLightType.PEDESTRIAN, new TrafficLight(TrafficLightType.PEDESTRIAN));
            //trafficLightsDirection.put(direction, trafficLights);

        }

        generateVehicles(vehicleCount);
    }

    public Point2D getReferencePoint() {
        return referencePoint;
    }


    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setPedestrianStripes(boolean selected) {
        Iterator<Road> it = roads.values().iterator();
        while (it.hasNext()) {
            it.next().setPedestrianStripes(selected);
        }
    }

    public void setVelostripes(boolean selected) {
        Iterator<Road> it = roads.values().iterator();
        while (it.hasNext()) {
            it.next().setVelostripes(selected);
        }
    }

    public int getRoadWidth() {
        return roadWidth;
    }

    public int getRoadLength() {
        return roadLength;
    }

    /**
     * @version 1.0
     * @autor NIN Class
     * @date 02.08.2018
     */
    public Integer getRoadCount() {
        return roadCount;
    }

    public Road getRoad(Direction direction) {
        return roads.get(direction);
    }

    public void setRoadCount(Integer numberOfDriveways) {
        this.roadCount = numberOfDriveways;
    }

    public void setTrafficLightState(Direction direction, TrafficLightState state) {
        roads.get(direction).getTrafficLightModelCar().setState(state);
    }

    public boolean canITurn(Position position) {
        return turningArea.isInside(position);
    }

    public void generateVehicles(int count) {
        for (int i = 0; i < count; i++) {
            Vehicle vehicle = new Vehicle(this);
            vehicles.add(vehicle);


        }
    }

    public void calculatePositions(Double secondsElapsedCapped) {
        for (int i = 0; i < vehicles.size(); i++) {
            vehicles.get(i).setNewPosition(secondsElapsedCapped);
        }
    }
}


