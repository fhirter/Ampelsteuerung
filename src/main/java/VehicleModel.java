import javafx.application.Platform;
import javafx.geometry.Point2D;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class VehicleModel extends Observable
{
    private final Point2D westStartPoint = new Point2D(300, 465);
    private final Point2D northStartPoint = new Point2D(615, 100);
    private final Point2D eastStartPoint = new Point2D(950, 440);
    private final Point2D southStartPoint = new Point2D(635, 800);
    MovedElement movedElement;
    private Point2D startCoordinates = new Point2D(0,0);
    private FixPoint startPoint;
    private FixPoint endPoint;
    private int startRotate;
    private int rotate;
    private double xPosition, yPosition;
    private Timer timerChangeState = new Timer();
    private int routePositionCounter = 0;
    private int speed;


    public VehicleModel(MovedElement movedElement, FixPoint startPoint)
    {
        this.movedElement = movedElement;
        this.startPoint = startPoint;
        this.endPoint = getRandomEndpoint();

        switch(startPoint)
        {
            case north:
            {
                startCoordinates = northStartPoint;
                startRotate = 180;
                break;
            }
            case west:
            {
                startCoordinates = westStartPoint;
                startRotate = 90;
                break;
            }
            case east:
            {
                startCoordinates = eastStartPoint;
                startRotate = 270;
                break;
            }
            case south:
            {
                startCoordinates = southStartPoint;
                startRotate = 0;
                break;
            }
        }

        switch(movedElement)
        {
            case Bicycle:
            {
                speed = 80;
                break;
            }
            default:
            {
                speed = 50;
                break;
            }
        }

        this.xPosition = startCoordinates.getX();
        this.yPosition = startCoordinates.getY();
        rotate = startRotate;
    }


    public FixPoint getRandomEndpoint()
    {
        int rndNumber = 0;
        Random random = new Random();
        rndNumber = random.nextInt(FixPoint.values().length);
        return FixPoint.values()[rndNumber];
    }


    public void startGameLoop()
    {
        timerChangeState.schedule(new TimerTask() {
                                      @Override
                                      public void run() {
                                          Platform.runLater(new Runnable() {
                                              @Override
                                              public void run() {
                                                  notifyObservers();
                                              }
                                          });
                                      }
                                  },
                0 /* ms delay */,
                speed /* ms period */);
    }


    public MovedElement getTypeOfMovedElements()
    {
        return this.movedElement;
    }


    public int getStartRotation()
    {
        return this.rotate;
    }


    public Point2D getStartPosition()
    {
        return startCoordinates;
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


    public int getNewRotation()
    {
        return this.rotate;
    }


    public Point2D getNewPosition()
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
            rotate = startRotate;
            xPosition = startCoordinates.getX();
            yPosition = startCoordinates.getY();
            this.endPoint = getRandomEndpoint();
            while(this.startPoint == this.endPoint){
                this.endPoint = getRandomEndpoint();}
            //timerChangeState.cancel();
        }
        return new Point2D(xPosition, yPosition);
    }
}
