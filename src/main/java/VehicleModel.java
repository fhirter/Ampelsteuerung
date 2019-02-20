import java.util.*;

public class VehicleModel extends Observable
{
    private final Map<Direction, Position> startPoints = new HashMap<>();
    private final Crossroad crossroad;
    private VehicleType elementType;
    private Direction start;
    private Direction destination;
    private Direction currentDirection;

    private Position position;

    private int speed;  // pixels/second
    private int step;

    public VehicleModel(Crossroad crossroad, VehicleType elementType, Direction start)
    {
        this.crossroad = crossroad;
        this.elementType = elementType;
        this.start = start;

        setRandomDestination();

        startPoints.put(Direction.WEST, new Position(300, 465, 90));
        startPoints.put(Direction.NORTH, new Position(615, 100, 180));
        startPoints.put(Direction.EAST, new Position(950, 440, 270));
        startPoints.put(Direction.SOUTH, new Position(635, 800,0));

        currentDirection = start.getOpposite();

        position = new Position(startPoints.get(start));

        switch(elementType)
        {
            case Bicycle:
            {
                speed = 200;
                break;
            }
            default:
            {
                speed = 300;
                break;
            }
        }
    }


    public void setRandomDestination()
    {
        int rndNumber;

        // destination shouldn't be equal to start
        do
        {
            Random random = new Random();
            rndNumber = random.nextInt(Direction.values().length);
            destination = Direction.values()[rndNumber];
        } while (start == destination);
    }

    private void drive() {
        if(currentDirection == destination) {
            driveStraight();
        } else if(crossroad.canITurn(position) == true) {
            turn();
        } else {
            driveStraight();
        }
    }

    private void driveStraight() {
        switch (currentDirection) {
            case SOUTH:
                position.y += step;
                break;
            case NORTH:
                position.y -= step;
               break;
            case EAST:
                position.x += step;
                break;
            case WEST:
                position.x -= step;
                break;
        }
    }

    public VehicleType getType()
    {
        return this.elementType;
    }


    public Position getStartPosition()
    {
        return startPoints.get(start);
    }


    public void turn()
    {
       if(currentDirection == Direction.WEST && destination == Direction.NORTH) {
           turnRight();
       }

        if(currentDirection == Direction.SOUTH && destination == Direction.WEST) {
            turnRight();
        }

        if(currentDirection == Direction.EAST && destination == Direction.SOUTH) {
            turnRight();
        }

        if(currentDirection == Direction.NORTH && destination == Direction.EAST) {
            turnRight();
        }

        if(position.angle == destination.getAngle()) {  // transition complete
            currentDirection = destination;
        }


    }

    private void turnRight() {
        position.angle += 1;
    }


    public Position getPosition()
    {
        return position;
    }

    public void setNewPosition(float secondsElapsedCapped)
    {
        step = (int)(secondsElapsedCapped*speed);

        drive();

        if((position.x > 1100) || (position.y > 1020) || (position.x < 180) || (position.y < -80))
        {
            resetRoute();
        }

        notifyObservers();
    }

    private void resetRoute() {
        position = startPoints.get(start);
        setRandomDestination();
    }
}
