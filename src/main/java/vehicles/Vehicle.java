package vehicles;

import crossroad.Crossroad;
import crossroad.Road;
import javafx.geometry.Point2D;
import util.Direction;
import util.Observable;
import util.Position;

import java.util.HashMap;
import java.util.Map;

import static util.Direction.*;

public class Vehicle extends Observable implements Driveable {
    private final Map<Direction, Position> startPoints = new HashMap<>();
    private Crossroad crossroad;

    private Direction start;
    private Direction destination;
    private Direction currentDirection;

    private Position position;
    private Road currentRoad;

    private Double lateral = 0.0;
    private Double forward = 0.0;
    private int speed = 100;  // pixels/second
    private int wheelbase = 70;
    private int steeringAngle = 0;  // degree
    private Double step;
    private int length = 80;
    private int width = 40;
    private Point2D pivot;
    private Map<Direction, Map<Direction, Integer>> signMap;

    public Vehicle(Direction start, Direction destination) {
        initSignMap();

        this.start = start;
        this.destination = destination;

        System.out.println("start:" + start + ", destination: " + destination);
    }

    private void initPosition() {
        currentRoad = crossroad.getRoad(start);

        startPoints.put(WEST, new Position(-400, 10, 0));
        startPoints.put(NORTH, new Position(-10, -400, 90));
        startPoints.put(EAST, new Position(400, -10, 180));
        startPoints.put(SOUTH, new Position(10, 400, 270));

        currentDirection = start.getOpposite();

        position = new Position(startPoints.get(start));
    }

    public void setCrossroad(Crossroad crossroad) {
        this.crossroad = crossroad;

        initPosition();
    }

    public void setSpeed(int speed) {
        if(speed > 0) {
            this.speed = speed;
        } else {
            this.speed = 0;
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setStart(Direction start) {
        this.start = start;
    }

    @Override
    public void setDestination(Direction destination) {
        this.destination = destination;
    }

    public int getWheelbase() {
        return wheelbase;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void drive(Double secondsElapsedCapped) {

        if(crossroad.canIDrive(position, currentRoad)) {
            step = secondsElapsedCapped * speed;  // [s*px/s] = [px]
        } else {
            step = 0.0;
            return;
        }

        if (currentDirection == destination) {
            driveStraight();
        } else if (crossroad.canITurn(position)) {
            turn();
        } else {
            driveStraight();
        }

        notifyObservers();
    }

    @Override
    public void driveStraight() {
        forward = step;
        lateral = 0.0;
        mapDirection();
    }

//    public Direction getRandomDirection() {
//        int rndNumber = 0;
//        Random random = new Random();
//        rndNumber = random.nextInt(values().length);
//        return values()[rndNumber];
//    }


    public Position getStartPosition() {
        return startPoints.get(start);
    }


    private void mapDirection() {
        switch (currentDirection) {
            case SOUTH:
                position = position.add(-lateral, forward, 0);
                break;
            case NORTH:
                position = position.add(lateral, -forward, 0);
                break;
            case EAST:
                position = position.add(forward, lateral, 0);
                break;
            case WEST:
                position = position.add(-forward, -lateral, 0);
                break;
        }
    }

    /**
     *
     * http://www.asawicki.info/Mirror/Car%20Physics%20for%20Games/Car%20Physics%20for%20Games.html
     *
     *
     * @autor Hirter Fabian
     */
    public void turn() {
        int sign = getSign();

        if(sign == -1) {
            steeringAngle = 45;  // degree
        }
        if(sign == 1) {
            steeringAngle = 60;
        }

        double radius = wheelbase / (Math.sin(Math.toRadians(steeringAngle)));

        double dphi = Math.toDegrees(sign * step / radius); // [px/px]

        if (pivot == null) {
            setPivot(radius, sign);
        }

        position = position.rotate(dphi, pivot);

        double destinationAngle = destination.getAngle();
        double eta = 1.0;

        // turn completed
        if (destinationAngle + eta > position.getAngle() && destinationAngle - eta < position.getAngle()) {
            currentDirection = destination;
            currentRoad = crossroad.getRoad(destination);
            pivot = null;
        }
    }



    /**
     *
     * set rotation center according to current direction
     *
     * @param radius radius of rotation
     */
    private void setPivot(double radius, int sign) {
        switch (currentDirection) {
            case SOUTH:
                pivot = new Point2D(position.getX() - sign*radius, position.getY());
                break;
            case EAST:
                pivot = new Point2D(position.getX(), position.getY() + sign*radius);
                break;
            case WEST:
                pivot = new Point2D(position.getX(), position.getY() - sign*radius);
                break;
            case NORTH:
                pivot = new Point2D(position.getX() + sign*radius, position.getY());
                break;
        }
    }


    private int getSign() {
        Integer integer = signMap.get(currentDirection).get(destination);
        return integer;
    }

    private void initSignMap() {
        signMap = new HashMap<>();

        signMap.put(SOUTH,new HashMap<>() {{put(WEST,1);put(EAST,-1);}});
        signMap.put(EAST,new HashMap<>() {{put(SOUTH,1);put(NORTH,-1);}});
        signMap.put(WEST,new HashMap<>() {{put(NORTH,1);put(SOUTH,-1);}});
        signMap.put(NORTH,new HashMap<>() {{put(EAST,1);put(WEST,-1);}});
    }


}
