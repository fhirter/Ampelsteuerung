package vehicles;

import com.sun.javafx.scene.paint.GradientUtils;
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

public class Vehicle extends Observable implements Driveable {
    private final Map<Direction, Position> startPoints = new HashMap<>();
    private final Crossroad crossroad;

    private Direction start;
    private Direction destination;
    private Direction currentDirection;

    private Position position;

    private Double lateral = 0.0;
    private Double forward = 0.0;

    private int speed = 100;  // pixels/second
    private int wheelbase = 70;
    private int steeringAngle = 0;  // degree

    private Double step;

    private int length = 80;
    private int width = 40;
    private Point2D pivot;

    public Vehicle(Crossroad crossroad) {
        this.crossroad = crossroad;

        setRandomStart();
        setRandomDestination();

        //debug
//        start = SOUTH;
//        destination = EAST;

        System.out.println("start:" + start + ", destination: " + destination);

        final Point2D ref = crossroad.getReferencePoint();

        startPoints.put(WEST, new Position(300, 465, 0));
        startPoints.put(NORTH, new Position(ref.getX()-10, ref.getY() - 400, 90));
        startPoints.put(EAST, new Position(950, 440, 180));
        startPoints.put(SOUTH, new Position(ref.getX()+10, ref.getY() + 400, 270));

        currentDirection = start.getOpposite();

        position = new Position(startPoints.get(start));
    }

    @Override
    public Position getPosition() {
        return position;
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
    public void drive() {
        if (currentDirection == destination) {
            driveStraight();
        } else if (crossroad.canITurn(position) == true) {
            turn();
        } else {
            driveStraight();
        }
    }

    @Override
    public void driveStraight() {
        forward = step;
        lateral = 0.0;
        mapDirection();
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

        double dphi = sign * step / radius; // rad   [px/px]

        if (pivot == null) {
            setPivot(radius, sign);
        }

        Point2D shift = position.subtract(pivot);       // translate for pivot center to be on 0,0

        double x = shift.getX();
        double y = shift.getY();

        double x1 = x*Math.cos(dphi) - y*Math.sin(dphi);        // then rotate
        double y1 = x*Math.sin(dphi) + y*Math.cos(dphi);

        Point2D newpoint = (new Point2D(x1,y1)).add(pivot);     // and shift back
        position = new Position(newpoint.getX(), newpoint.getY(), position.getAngle() + Math.toDegrees(dphi));

        double destinationAngle = destination.getAngle();
        double eta = 1.0;
        System.out.printf("current angle: "+position.getAngle());
        if (destinationAngle + eta > position.getAngle() && destinationAngle - eta < position.getAngle()) {
            currentDirection = destination;
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
        switch (currentDirection) {
            case SOUTH:
                if (destination == WEST)
                    return 1;
                if (destination == EAST)
                    return -1;
                break;
            case EAST:
                if (destination == SOUTH) {
                    return 1;
                }
                if (destination == NORTH) {
                    return -1;
                }
                break;
            case WEST:
                if (destination == NORTH) {
                    return 1;
                }
                if (destination == SOUTH) {
                    return -1;
                }
                break;
            case NORTH:
                if (destination == EAST)
                    return 1;
                if (destination == WEST)
                    return -1;
                break;
        }
        return 0;
    }

    public void setNewPosition(Double secondsElapsedCapped) {
        step = secondsElapsedCapped * speed;  // [s*px/s] = [px]

        drive();

        if ((position.getX() > 1100) || (position.getY() > 1020) || (position.getX() < 180) || (position.getY() < -80)) {
            //       resetRoute();
        }

        notifyObservers();
    }

    private void resetRoute() {
        position = startPoints.get(start);
        setRandomDestination();
    }
}
