package vehicles;

import crossroad.Context;
import crossroad.Road;
import javafx.geometry.Point2D;
import util.Direction;
import util.Subject;
import util.Position;

import java.util.HashMap;
import java.util.Map;

import static util.Direction.*;

public class Car extends Subject implements Vehicle {
    private final Map<Direction, Position> startPoints = new HashMap<>();
    private final int wheelbase = 70;
    private final int length = 80;
    private final int width = 40;
    private Context context;
    private Direction start;
    private Direction destination;
    private Direction currentDirection;
    private Position position;
    private Road currentRoad;
    private int speed = 100;  // pixels/second

    private Point2D pivot;
    private Map<Direction, Map<Direction, TurningDirection>> signMap;

    public Car(Direction start, Direction destination) {
        initSignMap();

        this.start = start;
        this.destination = destination;

        System.out.println("start:" + start + ", destination: " + destination);
    }


    @Override
    public void setContext(Context context) {
        this.context = context;
        currentRoad = context.getRoad(start);

        currentDirection = start.getOpposite();

        position = getStartPosition(currentDirection.getAngle());

    }

    @Override
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
        if (currentDirection == destination) {
            driveStraight(secondsElapsedCapped);
        } else if (context.canITurn(position)) {
            turn(secondsElapsedCapped);
        } else {
            driveStraight(secondsElapsedCapped);
        }

        notifyObservers();
    }

    private double getStep(Double secondsElapsedCapped) {
        if(context.canIDrive(position, currentRoad)) {
            return secondsElapsedCapped * speed;  // [s*px/s] = [px]
        } else {
            return 0.0;
        }
    }

    public void driveStraight(Double secondsElapsedCapped) {
        double step = getStep(secondsElapsedCapped);
        double angle = currentDirection.getAngle();
        int sin = getSin(angle);
        int cos = getCos(angle);

        position = position.add(cos* step,sin* step,0);
    }

    /**
     * http://www.asawicki.info/Mirror/Car%20Physics%20for%20Games/Car%20Physics%20for%20Games.html
     *
     * @autor Hirter Fabian
     */
    public void turn(Double secondsElapsedCapped) {
        TurningDirection turningDirection = getTurningDirection();
        int steeringAngle = getSteeringAngle(turningDirection);
        double step = getStep(secondsElapsedCapped);

        double radius = getRadius(steeringAngle);
        double dphi = getDeltaPhi(turningDirection, radius, step);

        if (pivot == null) {
            pivot = calculatePivot(radius, turningDirection);
        }

        position = position.rotate(dphi, pivot);

        double destinationAngle = destination.getAngle();

        // turn completed
        double eta = 1.0;
        if (isTurnCompleted(destinationAngle, eta)) {
            currentDirection = destination;
            currentRoad = context.getRoad(destination);
            pivot = null;
        }
    }

    private boolean isTurnCompleted(double destinationAngle, double eta) {
        return destinationAngle + eta > position.getAngle() && destinationAngle - eta < position.getAngle();
    }

    private double getDeltaPhi(TurningDirection turningDirection, double radius, double step) {
        return Math.toDegrees(turningDirection.getSign() * step / radius);
    }

    private double getRadius(int steeringAngle) {
        return wheelbase / (Math.sin(Math.toRadians(steeringAngle)));
    }

    private int getSteeringAngle(TurningDirection turningDirection) {
        if(turningDirection == TurningDirection.LEFT) {
            return 55;  // degree
        }
        if(turningDirection == TurningDirection.RIGHT) {
            return 90;
        }
        return 0;
    }

    private Position getStartPosition(double angle) {
        // todo: remove magic numbers
        int sideOffset = width/2+7;
        int lengthOffset = 400;

        int sin = getSin(angle);
        int cos = getCos(angle);

        return new Position(-cos*lengthOffset + -sin*sideOffset,-sin*lengthOffset + cos*sideOffset, angle);
    }

    private int getCos(double angle) {
        return (int) Math.cos(2 * Math.PI * angle / 360);
    }

    private int getSin(double angle) {
        return (int) Math.sin(2 * Math.PI * angle / 360);
    }

    /**
     *
     * set rotation center according to current direction
     *
     * @param radius radius of rotation
     */
    private Point2D calculatePivot(double radius, TurningDirection turningDirection) {
        int sign = turningDirection.getSign();

        switch (currentDirection) {
            case EAST:
                return new Point2D(position.getX()-wheelbase/2.0, position.getY() - sign*radius);
            case NORTH:
                return new Point2D(position.getX() - sign*radius, position.getY()+wheelbase/2.0);
            case WEST:
                return new Point2D(position.getX()+wheelbase/2.0, position.getY() + sign*radius);
            case SOUTH:
                return new Point2D(position.getX() + sign*radius, position.getY()-wheelbase/2.0);
        }
        return null;
    }


    private TurningDirection getTurningDirection() {
        return   signMap.get(currentDirection).get(destination);
    }

    private void initSignMap() {
        signMap = new HashMap<>();

        signMap.put(SOUTH,new HashMap<>() {{put(WEST, TurningDirection.LEFT);put(EAST,TurningDirection.RIGHT);}});
        signMap.put(EAST,new HashMap<>() {{put(SOUTH,TurningDirection.LEFT);put(NORTH,TurningDirection.RIGHT);}});
        signMap.put(WEST,new HashMap<>() {{put(NORTH,TurningDirection.LEFT);put(SOUTH,TurningDirection.RIGHT);}});
        signMap.put(NORTH,new HashMap<>() {{put(EAST,TurningDirection.LEFT);put(WEST,TurningDirection.RIGHT);}});
    }

    public enum TurningDirection {
        LEFT, RIGHT;

        public int getSign() {
            return sign;
        }

        private int sign;

        static {
            RIGHT.sign = 1;
            LEFT.sign = -1;
        }


    }

}
