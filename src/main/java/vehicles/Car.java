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
    private int steeringAngle = 0;  // degree
    private Double step;
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

        if(context.canIDrive(position, currentRoad)) {
            step = secondsElapsedCapped * speed;  // [s*px/s] = [px]
        } else {
            step = 0.0;
            return;
        }

        if (currentDirection == destination) {
            driveStraight();
        } else if (context.canITurn(position)) {
            turn();
        } else {
            driveStraight();
        }

        notifyObservers();
    }

    public void driveStraight() {
        double forward = step;

        double angle = currentDirection.getAngle();

        int sin = getSin(angle);
        int cos = getCos(angle);

        position = position.add(cos*forward,sin*forward,0);
    }

    /**
     *
     * http://www.asawicki.info/Mirror/Car%20Physics%20for%20Games/Car%20Physics%20for%20Games.html
     *
     *
     * @autor Hirter Fabian
     */
    public void turn() {
        TurningDirection turningDirection = getTurningDirection();

        if(turningDirection == TurningDirection.LEFT) {
            steeringAngle = 55;  // degree
        }
        if(turningDirection == TurningDirection.RIGHT) {
            steeringAngle = 90;
        }

        double radius = wheelbase / (Math.sin(Math.toRadians(steeringAngle)));
        double dphi = Math.toDegrees(turningDirection.getSign() * step / radius); // [px/px]

        if (pivot == null) {
            pivot = calculatePivot(radius, turningDirection);
        }

        position = position.rotate(dphi, pivot);

        double destinationAngle = destination.getAngle();
        double eta = 1.0;

        // turn completed
        if (destinationAngle + eta > position.getAngle() && destinationAngle - eta < position.getAngle()) {
            currentDirection = destination;
            currentRoad = context.getRoad(destination);
            pivot = null;
        }
    }

    private Position getStartPosition(double angle) {
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
                return new Point2D(position.getX()-wheelbase/2, position.getY() - sign*radius);
            case NORTH:
                return new Point2D(position.getX() - sign*radius, position.getY()+wheelbase/2);
            case WEST:
                return new Point2D(position.getX()+wheelbase/2, position.getY() + sign*radius);
            case SOUTH:
                return new Point2D(position.getX() + sign*radius, position.getY()-wheelbase/2);
        }
        return null;
    }


    private TurningDirection getTurningDirection() {
        double currentAngle = currentDirection.getAngle();
        double destinationAngle = destination.getAngle();

        return   signMap.get(currentDirection).get(destination);

//        if(currentAngle > destinationAngle) {
//           // return 1;
//        }

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
