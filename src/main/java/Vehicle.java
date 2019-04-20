import javafx.geometry.Point2D;

import java.util.*;

public class Vehicle extends Observable {
    private final Map<Direction, Position> startPoints = new HashMap<>();
    private final Crossroad crossroad;

    private Direction start;
    private Direction destination;
    private Direction currentDirection;

    private Position position;

    private Double lateral =0.0;
    private Double forward = 0.0;

    private int speed;  // pixels/second
    private Double step;
    private int wheelbase = 60;
    private int steeringAngle = 40;  // degree

    private int length = 80;
    private int width = 40;

    public Vehicle(Crossroad crossroad) {
        this.crossroad = crossroad;

        setRandomStart();
        setRandomDestination();

        //debug
        start = Direction.WEST;
        destination = Direction.SOUTH;

        System.out.println("start:" + start + ", destination: " + destination);

        final Point2D ref = crossroad.getReferencePoint();

        startPoints.put(Direction.WEST, new Position(300, 465, 0));
        startPoints.put(Direction.NORTH, new Position(ref.getX()-40, ref.getY()-400, 90));
        startPoints.put(Direction.EAST, new Position(950, 440, 180));
        startPoints.put(Direction.SOUTH, new Position(635, 800, 270));

        currentDirection = start.getOpposite();

        position = new Position(startPoints.get(start));

        speed = 100;
    }

    public Position getPosition() {
        return position;
    }

    public int getWheelbase() {
        return wheelbase;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public void setRandomStart() {
        int rndNumber = 0;
        Random random = new Random();
        rndNumber = random.nextInt(Direction.values().length);
        start = Direction.values()[rndNumber];
    }

    public void setRandomDestination() {
        int rndNumber;

        // destination shouldn't be equal to start
        do {
            Random random = new Random();
            rndNumber = random.nextInt(Direction.values().length);
            destination = Direction.values()[rndNumber];
        } while (start == destination);
    }

    private void drive() {
        if (currentDirection == destination) {
            driveStraight();
        } else if (crossroad.canITurn(position) == true) {
            turn();
        } else {
            driveStraight();
        }
    }

    private void driveStraight() {
        forward = step;
        lateral = 0.0;
        mapDirection();
    }

    private void mapDirection() {
        switch (currentDirection) {
            case SOUTH:
                position = position.add(-lateral,forward,0);
                break;
            case NORTH:
                position = position.add(lateral,-forward,0);
                break;
            case EAST:
                position = position.add(forward,lateral,0);
                break;
            case WEST:
                position = position.add(-forward,-lateral,0);
                break;
        }
    }

    public Position getStartPosition() {
        return startPoints.get(start);
    }


    public void turn() {
        //http://www.asawicki.info/Mirror/Car%20Physics%20for%20Games/Car%20Physics%20for%20Games.html
        int sign = 0;
        try {
            sign = getSign();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
//
//        double steering = Math.sin(Math.toRadians(steeringAngle))*step;
//        position.angle += sign * Math.toDegrees(Math.atan(steering / wheelbase));
//        lateral = Math.cos(Math.toRadians(position.angle)) * step;
//        forward = Math.sin(Math.toRadians(position.angle)) * step;

        double radius = wheelbase/Math.sin(Math.toRadians(steeringAngle));
        double angle = step/radius; //rad
//        Point2D rotationCenter = new Point2D(
//                Math.sin(Math.toRadians(position.angle))*radius,
//                Math.cos(Math.toRadians(position.angle))*radius
//
//        ).add(position);


        double newAngle = sign*angle+Math.toRadians(position.getAngle());  // rad

        // polarcoordinates to cartesian
        double dx = 2*radius*Math.sin(newAngle/2)*Math.cos(newAngle);
        double dy = 2*radius*Math.sin(newAngle/2)*Math.sin(newAngle);

        //Point2D newPos = rotationCenter.add(new Point2D(dx,dy));
//        position.angle += newAngle;
        position = position.add(dx,dy,Math.toDegrees(newAngle));

//        mapDirection();

        double destinationAngle = destination.getAngle();
        double eta = 2.0;

        if(position.angle > 360) {
            position.angle -= 360;
        }
        if(position.angle < 0 ) {
            position.angle += 360;
        }

        if (destinationAngle+eta > position.angle  && destinationAngle-eta < position.angle) {
            currentDirection = destination;
        }

    }

    private int getSign() throws Exception {
        switch (currentDirection) {
            case SOUTH:
                if(destination == Direction.WEST)
                    return 1;
                if(destination == Direction.EAST)
                    return -1;
                break;
            case EAST:
                if(destination == Direction.SOUTH) {
                    return 1;
                }
                if(destination == Direction.NORTH) {
                    return -1;
                }
                break;
            case WEST:
                if(destination == Direction.NORTH) {
                    return 1;
                }
                if(destination == Direction.SOUTH) {
                    return -1;
                }
                break;
            case NORTH:
                if(destination == Direction.EAST)
                    return 1;
                if(destination == Direction.WEST)
                    return -1;
                break;
        }
        return 0;
    }

    public void setNewPosition(Double secondsElapsedCapped) {
        step = secondsElapsedCapped * speed;

        drive();

        if ((position.getX() > 1100) || (position.getY() > 1020) || (position.getX() < 180) || (position.getY() < -80)) {
            resetRoute();
        }

        notifyObservers();
    }

    private void resetRoute() {
        position = startPoints.get(start);
        setRandomDestination();
    }
}
