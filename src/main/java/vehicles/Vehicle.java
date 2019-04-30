package vehicles;

import crossroad.Crossroad;
import javafx.geometry.Point2D;
import javafx.scene.transform.Rotate;
import util.Direction;
import util.Observable;
import util.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static util.Direction.*;

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
        start = NORTH;
        destination = EAST;

        System.out.println("start:" + start + ", destination: " + destination);

        final Point2D ref = crossroad.getReferencePoint();

        startPoints.put(WEST, new Position(300, 465, 0));
        startPoints.put(NORTH, new Position(ref.getX()-40, ref.getY()-400, 90));
        startPoints.put(EAST, new Position(950, 440, 180));
        startPoints.put(SOUTH, new Position(635, 800, 270));

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
        rndNumber = random.nextInt(values().length);
        start = values()[rndNumber];
    }

    public void setRandomDestination() {
        int rndNumber;

        // destination shouldn't be equal to start
        do {
            Random random = new Random();
            rndNumber = random.nextInt(values().length);
            destination = values()[rndNumber];
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


        double da = sign*angle;//+Math.toRadians(position.getAngle());  // rad

        double sehne = 2*radius*Math.sin(da/2);


        // polarcoordinates to cartesian
        double dx = 2*radius*Math.sin(da/2)*Math.cos(da);
        double dy = 2*radius*Math.sin(da/2)*Math.sin(da);


        // todo: here fix this!
        Point2D point = new Point2D(dx, dy);
        Rotate rotate = new Rotate(position.getAngle(),0,0);
        Point2D newpoint = rotate.deltaTransform(point);

        //Point2D newPos = rotationCenter.add(new Point2D(dx,dy));
//        position.angle += da;
        position = position.add(newpoint.getX(),newpoint.getY(),Math.toDegrees(da));

//        mapDirection();

        double destinationAngle = destination.getAngle();
        double eta = 2.0;

        if(position.getAngle() > 360) {
            position.setAngle(position.getAngle() - 360);
        }
        if(position.getAngle() < 0 ) {
            position.setAngle(position.getAngle() + 360);
        }

        if (destinationAngle+eta > position.getAngle()  && destinationAngle-eta < position.getAngle()) {
            currentDirection = destination;
        }

    }

    private int getSign() throws Exception {
        switch (currentDirection) {
            case SOUTH:
                if(destination == WEST)
                    return 1;
                if(destination == EAST)
                    return -1;
                break;
            case EAST:
                if(destination == SOUTH) {
                    return 1;
                }
                if(destination == NORTH) {
                    return -1;
                }
                break;
            case WEST:
                if(destination == NORTH) {
                    return 1;
                }
                if(destination == SOUTH) {
                    return -1;
                }
                break;
            case NORTH:
                if(destination == EAST)
                    return 1;
                if(destination == WEST)
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
