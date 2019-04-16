import javafx.geometry.Point2D;

import java.util.*;

public class Crossroad extends Observable {
    private final Point2D referencePoint;

    private Integer roadCount = 4;


    private int roadWidth = 250;
    private int roadLength = 300;

    private Map<Direction, Road> roads = new HashMap<>();
    private final Map<Direction, Position> startPositions = new HashMap<>();

    private Area turningArea;

    public void setPedestrianStripes(boolean selected) {
        Iterator<Road> it = roads.values().iterator();
        while (it.hasNext()) {
            it.next().setPedestrianStripes(selected);
        }
    }

    public void setVelostripes(boolean selected) {
       Iterator<Road> it = roads.values().iterator();
       while(it.hasNext()) {
           it.next().setVelostripes(selected);
       }
    }

    private class Area {
        private int size;
        private Point2D center;


        public Area(int size, Point2D center){
            this.size = size;
            this.center = center;
        }; // just use default values

        public boolean isInside(Position position) {
            if(position.x > (center.getX()-size/2) && position.x < (center.getX()+size/2) ) {
                if(position.y > (center.getY()-size/2) && position.y < (center.getY()+size/2)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Crossroad: Constructor
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */

    public Crossroad(Point2D ref)
    {
        this.referencePoint = ref;
        turningArea = new Area(180, referencePoint);

        /* Loop to create all roads */
        Direction[] directions =  Direction.values();
        for (int i = 0; i < directions.length; i++) {
            Road road = new Road();
            roads.put(directions[i], road);

            Map<TrafficLightType, TrafficLight> trafficLights = new HashMap<>();

            trafficLights.put(TrafficLightType.CAR, new TrafficLight(TrafficLightType.CAR));
            trafficLights.put(TrafficLightType.PEDESTRIAN, new TrafficLight(TrafficLightType.PEDESTRIAN));
            //trafficLightsDirection.put(direction, trafficLights);

        }

        startPositions.put(Direction.WEST, new Position(300, 465, 90));
        startPositions.put(Direction.NORTH, new Position(615, 100, 180));
        startPositions.put(Direction.EAST, new Position(950, 450, 270));
        startPositions.put(Direction.SOUTH, new Position(635, 800,0));
    }

    public int getRoadWidth() {
        return roadWidth;
    }

    public int getRoadLength() {
        return roadLength;
    }

    /**
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public Integer getRoadCount() {
        return roadCount;
    }

    public Road getRoad(Direction direction) {
        return roads.get(direction);
    }

    public void setRoadCount(Integer numberOfDriveways){
        this.roadCount = numberOfDriveways;
    }

    public void setTrafficLightState(Direction direction, TrafficLightState state)
    {
        roads.get(direction).getTrafficLightModelCar().setState(state);
    }

    public boolean canITurn(Position position) {
        return turningArea.isInside(position);
    }
}


