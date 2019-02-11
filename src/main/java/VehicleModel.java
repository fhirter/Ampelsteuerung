import javafx.application.Platform;
import javafx.geometry.Point2D;

import java.util.*;

public class VehicleModel extends Observable
{
    /*
    private final Point2D westStartPoint = new Point2D(300, 465);
    private final Point2D northStartPoint = new Point2D(615, 100);
    private final Point2D eastStartPoint = new Point2D(950, 440);
    private final Point2D southStartPoint = new Point2D(635, 800);
    */

    private final Map<FixPoint, Position> startPoints = new HashMap<>();


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

    // FH: x und y Koordinaten und Winkel zusammenfassen
    private Position position;

    private class Position {
        int x, y, angle;

        public Position(int x, int y, int angle) {
            this.x = x;
            this.y = y;
            this.angle = angle;
        }


    }

    public VehicleModel(MovedElement movedElement, FixPoint startPoint)
    {
        this.movedElement = movedElement;
        this.startPoint = startPoint;
        this.endPoint = getRandomEndpoint();

        startPoints.put(FixPoint.west, new Position(300, 465, 90));
        startPoints.put(FixPoint.north,new Position(615, 100, 180));
        startPoints.put(FixPoint.east, new Position(950, 440, 270));
        startPoints.put(FixPoint.south, new Position(635, 800,0));

        position = startPoints.get(startPoint);

     /*   switch(startPoint)
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

*/
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

        /*
        this.xPosition = startCoordinates.getX();
        this.yPosition = startCoordinates.getY();

        rotate = startRotate;
*/

        this.xPosition = position.x;
        this.yPosition = position.y;
        rotate = position.angle;
    }


    public FixPoint getRandomEndpoint()
    {
        int rndNumber = 0;
        Random random = new Random();
        rndNumber = random.nextInt(FixPoint.values().length);
        return FixPoint.values()[rndNumber];
    }


    public void startGameLoop()     // FH: so hat jedes einzelne bewegende Objekt seinen eigenen GameLoop, das ist nicht sehr performant.
            // besser: die berechnung der Position findet anhand der Zeit statt.
            // die Klasse MovedElements startet einen Timer und übergibt allen bewegenden Objekten die seit dem letzten Frame verstrichene Zeit
            // notifyObservers() bei jeder änderung der Position und Rotation aufrufen
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
