import javafx.application.Platform;
import javafx.geometry.Point2D;

import java.util.Timer;
import java.util.TimerTask;

public class VehicleModel extends Observable
{
    private final Point2D westStartPoint = new Point2D(300, 465);
    private final Point2D northStartPoint = new Point2D(615, 100);
    private final Point2D eastStartPoint = new Point2D(950, 440);
    private final Point2D southStartPoint = new Point2D(635, 800);
    MovedElements movedElements;
    private Point2D startCoordinates = new Point2D(0,0);
    private StartAndEndpoint startPoint;
    private StartAndEndpoint endPoint;
    private int startRotate;
    private int rotate;
    private double xPosition, yPosition;
    private Timer timerChangeState = new Timer();
    private int routePositionCounter = 0;


    public VehicleModel(MovedElements movedElements, StartAndEndpoint startPoint, StartAndEndpoint endPoint)
    {
        this.movedElements = movedElements;
        this.startPoint = startPoint;
        this.endPoint = endPoint;

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
        this.xPosition = startCoordinates.getX();
        this.yPosition = startCoordinates.getY();
        rotate = startRotate;
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
                50 /* ms period */);
    }


    public MovedElements getTypeOfMovedElements()
    {
        return this.movedElements;
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
            if(endPoint == StartAndEndpoint.west)
            {
                turn(-7, 3, 5);
            } else if(endPoint == StartAndEndpoint.east)
            {
                turn(6, 4, -5);
            } else
            {
                calcNextPositionNorthToSouth();
            }
        }
        else if(routePositionCounter >77)
        {
            if(endPoint == StartAndEndpoint.west)
            {
                calcNextPositionEastToWest();
            } else if(endPoint == StartAndEndpoint.east)
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
            if(endPoint == StartAndEndpoint.north)
            {
                turn(3, -7, -5);
            } else if(endPoint == StartAndEndpoint.south)
            {
                turn(1, 8, 5);
            } else
            {
                calcNextPositionWestToEast();
            }
        }
        else if(routePositionCounter >77)
        {
            if(endPoint == StartAndEndpoint.north)
            {
                calcNextPositionSouthToNord();
            } else if(endPoint == StartAndEndpoint.south)
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
            if(endPoint == StartAndEndpoint.west)
            {
                turn(-4, -4, -5);
            } else if(endPoint == StartAndEndpoint.east)
            {
                turn(7, -2, 5);
            } else
            {
                calcNextPositionSouthToNord();
            }
        }
        else if(routePositionCounter >77)
        {
            if(endPoint == StartAndEndpoint.west)
            {
                calcNextPositionEastToWest();
            } else if(endPoint == StartAndEndpoint.east)
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
            if(endPoint == StartAndEndpoint.north)
            {
                turn(-1, -6, 5);
            } else if(endPoint == StartAndEndpoint.south)
            {
                turn(-3, 7, -5);
            } else
            {
                calcNextPositionEastToWest();
            }
        }
        else if(routePositionCounter >77)
        {
            if(endPoint == StartAndEndpoint.north)
            {
                calcNextPositionSouthToNord();
            } else if(endPoint == StartAndEndpoint.south)
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
            xPosition = startCoordinates.getX();
            yPosition = startCoordinates.getY();
            rotate = startRotate;
            //timerChangeState.cancel();
        }
        return new Point2D(xPosition, yPosition);
    }
}
