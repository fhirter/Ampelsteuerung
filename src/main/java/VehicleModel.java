import java.util.*;

public class VehicleModel extends Observable
{
    private final Map<FixPoint, Position> startPoints = new HashMap<>();
    MovedElement movedElement;
    private FixPoint startPoint;
    private FixPoint endPoint;
    private int rotate;
    private int xPosition, yPosition;
    private int routePositionCounter = 0;
    private Position position;
    private double timestepForRedraw;
    private float secondsElapsedMultiplized;

    public VehicleModel(MovedElement movedElement, FixPoint startPoint)
    {
        this.movedElement = movedElement;
        this.startPoint = startPoint;
        this.endPoint = getRandomEndpoint();

        startPoints.put(FixPoint.west, new Position(300, 465, 90));
        startPoints.put(FixPoint.north, new Position(615, 100, 180));
        startPoints.put(FixPoint.east, new Position(950, 440, 270));
        startPoints.put(FixPoint.south, new Position(635, 800,0));
        position = new Position(startPoints.get(startPoint).x, startPoints.get(startPoint).y, startPoints.get(startPoint).angle);
        this.xPosition = position.x;
        this.yPosition = position.y;
        this.rotate = position.angle;

        switch(movedElement)
        {
            case Bicycle:
            {
                timestepForRedraw = 0.04;
                break;
            }
            default:
            {
                timestepForRedraw = 0.03;
                break;
            }
        }
    }


    public FixPoint getRandomEndpoint()
    {
        int rndNumber = 0;
        Random random = new Random();
        rndNumber = random.nextInt(FixPoint.values().length);
        return FixPoint.values()[rndNumber];
    }


    public MovedElement getTypeOfMovedElements()
    {
        return this.movedElement;
    }


    public Position getStartPosition()
    {
        return startPoints.get(startPoint);
    }


    public void calcNextPositionWestToEast()
    {
        xPosition += 5;
    }


    public void calcNextPositionEastToWest()
    {
        xPosition -= 5;
    }


    public void calcNextPositionNorthToSouth()
    {
        yPosition += 5;
    }


    public void calcNextPositionSouthToNord()
    {
        yPosition -= 5;
    }


    public void turn(int signXPosition, int signYPosition, int signRotate)
    {
        xPosition += signXPosition;
        yPosition += signYPosition;
        rotate += signRotate;
    }


    public void calcRouteFromNorth()
    {
        if(routePositionCounter <58)
        {
            calcNextPositionNorthToSouth();
        }
        else if((routePositionCounter >58) && (routePositionCounter <77))
        {
            if(endPoint == FixPoint.west)
            {
                turn(-7, 3, 5);
            } else if(endPoint == FixPoint.east)
            {
                turn(6, 4, -5);
            } else
            {
                calcNextPositionNorthToSouth();
            }
        }
        else if(routePositionCounter >77)
        {
            if(endPoint == FixPoint.west)
            {
                calcNextPositionEastToWest();
            } else if(endPoint == FixPoint.east)
            {
                calcNextPositionWestToEast();
            } else
            {
                calcNextPositionNorthToSouth();
            }
        }
        routePositionCounter++;
    }


    public void calcRouteFromWest()
    {
        if(routePositionCounter <58)
        {
            calcNextPositionWestToEast();
        }
        else if((routePositionCounter >58) && (routePositionCounter <77))
        {
            if(endPoint == FixPoint.north)
            {
                turn(3, -7, -5);
            } else if(endPoint == FixPoint.south)
            {
                turn(1, 8, 5);
            } else
            {
                calcNextPositionWestToEast();
            }
        }
        else if(routePositionCounter >77)
        {
            if(endPoint == FixPoint.north)
            {
                calcNextPositionSouthToNord();
            } else if(endPoint == FixPoint.south)
            {
                calcNextPositionNorthToSouth();
            } else
            {
                calcNextPositionWestToEast();
            }
        }
        routePositionCounter++;
    }


    public void calcRouteFromSouth()
    {
        if(routePositionCounter <58)
        {
            calcNextPositionSouthToNord();
        }
        else if((routePositionCounter >58) && (routePositionCounter <77))
        {
            if(endPoint == FixPoint.west)
            {
                turn(-4, -4, -5);
            } else if(endPoint == FixPoint.east)
            {
                turn(7, -2, 5);
            } else
            {
                calcNextPositionSouthToNord();
            }
        }
        else if(routePositionCounter >77)
        {
            if(endPoint == FixPoint.west)
            {
                calcNextPositionEastToWest();
            } else if(endPoint == FixPoint.east)
            {
                calcNextPositionWestToEast();
            } else
            {
                calcNextPositionSouthToNord();
            }
        }
        routePositionCounter++;
    }


    public void calcRouteFromEast()
    {
        if(routePositionCounter <58)
        {
            calcNextPositionEastToWest();
        }
        else if((routePositionCounter >58) && (routePositionCounter <77))
        {
            if(endPoint == FixPoint.north)
            {
                turn(-1, -6, 5);
            } else if(endPoint == FixPoint.south)
            {
                turn(-3, 7, -5);
            } else
            {
                calcNextPositionEastToWest();
            }
        }
        else if(routePositionCounter >77)
        {
            if(endPoint == FixPoint.north)
            {
                calcNextPositionSouthToNord();
            } else if(endPoint == FixPoint.south)
            {
                calcNextPositionNorthToSouth();
            } else
            {
                calcNextPositionEastToWest();
            }
        }
        routePositionCounter++;
    }


    public Position getNewPosition()
    {
        switch(startPoint)
        {
            case north: {
                calcRouteFromNorth();
                break;
            }
            case west: {
                calcRouteFromWest();
                break;
            }
            case east: {
                calcRouteFromEast();
                break;
            }
            case south: {
                calcRouteFromSouth();
                break;
            }
        }

        if((xPosition > 1100) || (yPosition > 1020) || (xPosition < 180) || (yPosition < -80))
        {
            routePositionCounter = 0;
            xPosition = startPoints.get(startPoint).x;
            yPosition = startPoints.get(startPoint).y;
            rotate = startPoints.get(startPoint).angle;
            this.endPoint = getRandomEndpoint();
            while(startPoint == this.endPoint)
            {
                this.endPoint = getRandomEndpoint();
            }
        }

        position.x = xPosition;
        position.y = yPosition;
        position.angle = rotate;
        return position;
    }

    public void setNewPosition(float secondsElapsedCapped)
    {
        secondsElapsedMultiplized += secondsElapsedCapped;

        if(secondsElapsedMultiplized >= timestepForRedraw)
        {
            secondsElapsedMultiplized = 0;
            notifyObservers();
        }
    }
}
