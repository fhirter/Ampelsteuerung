import java.util.*;

public class VehicleModel extends Observable
{
    private final Map<FixPoint, Position> startPoints = new HashMap<>();
    private MovedElement movedElement;
    private Crossroad crossroad;
    private FixPoint startPoint;
    private FixPoint endPoint;
    private int rotation;
    private int xPosition, yPosition;
    private float calcTurnAngle;
    private boolean routePositionFlag = false;
    private int verzoegerung;
    private Position position;
    private float speed;
    private TrafficLightState trafficLightState;


    public VehicleModel(Crossroad crossroad, MovedElement movedElement, FixPoint startPoint)
    {
        this.crossroad = crossroad;
        this.movedElement = movedElement;
        this.startPoint = startPoint;
        this.endPoint = getRandomEndpoint();


        startPoints.put(FixPoint.west, new Position(300, 465, 90));
        startPoints.put(FixPoint.north, new Position(615, 100, 180));
        startPoints.put(FixPoint.east, new Position(950, 450, 270));
        startPoints.put(FixPoint.south, new Position(635, 800,0));
        position = new Position(startPoints.get(startPoint).x, startPoints.get(startPoint).y, startPoints.get(startPoint).angle);
        this.xPosition = position.x;
        this.yPosition = position.y;
        this.rotation = position.angle;

        switch(movedElement)
        {
            case Bicycle:
            {
                speed = 3;
                break;
            }
            default:
            {
                speed = 4;
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


    public void calcRouteFromNorth(float secondsElapsedCapped)
    {
        float movedWay = secondsElapsedCapped * speed/(float)0.016;

        /* Check state from trafficLight */
        if((yPosition >= 180) && (yPosition <= 190))
        {
            trafficLightState = crossroad.getDrivewayRoutes().get(1).getTrafficLightModelCar().get(0).getState();
            if(trafficLightState == TrafficLightState.RED)
            {
                return;
            }
        }

        /* Definition from the point to begin of turn */
        if((yPosition >= 380) && (routePositionFlag == false))
        {
            routePositionFlag = true;
        }

        /* programming the routes */
        if(routePositionFlag == false)
        {
            yPosition += (int)movedWay;
        }
        else
        {
            if (endPoint == FixPoint.west) {
                if (rotation != 270) {
                    //turn over short side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.05;
                    xPosition = (int) ((615 - 90) + Math.cos(calcTurnAngle) * 90);
                    yPosition = (int) (380 + Math.sin(calcTurnAngle) * 90);
                    rotation += 2;
                } else if (rotation == 270) {
                    xPosition -= (int) movedWay;
                }
            } else if (endPoint == FixPoint.east) {
                if (rotation != 90) {
                    //turn over long side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.02;
                    xPosition = (int) ((615 + 85) - Math.cos(calcTurnAngle) * 85);
                    yPosition = (int) ((380) + Math.sin(calcTurnAngle) * 85);
                    rotation -= 1;
                } else if (rotation == 90) {
                    xPosition += (int) movedWay;
                }
            } else {
                yPosition += (int) movedWay;
            }
        }
    }


    public void calcRouteFromWest(float secondsElapsedCapped)
    {
        float movedWay = secondsElapsedCapped * speed/(float)0.016;

        /* Check state from trafficLight */
        if((xPosition >= 350) && (xPosition <= 360))
        {
            trafficLightState = crossroad.getDrivewayRoutes().get(0).getTrafficLightModelCar().get(0).getState();
            if(trafficLightState == TrafficLightState.RED)
            {
                return;
            }
        }

        /* Definition from the point to begin of turn */
        if((xPosition >= 550) && (routePositionFlag == false))
        {
            routePositionFlag = true;
        }

        /* programming the routes */
        if(routePositionFlag == false)
        {
            xPosition += (int)movedWay;
        }
        else
        {
            if (endPoint == FixPoint.north)
            {
                if(rotation != 0 )
                {
                    //turn over long side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.02;
                    xPosition = (int) (550 + Math.sin(calcTurnAngle) * 85);
                    yPosition = (int) ((465 - 85) + Math.cos(calcTurnAngle) * 85);
                    rotation -= 1;
                }
                else if(rotation == 0)
                {
                    yPosition -= (int)movedWay;
                }
            }
            else if (endPoint == FixPoint.south)
            {
                if(rotation != 180)
                {
                    //turn over short side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.05;
                    xPosition = (int)(550 + Math.sin(calcTurnAngle) * 90);
                    yPosition = (int)((465 + 90) - Math.cos(calcTurnAngle) * 90);
                    rotation += 2;
                }
                else if(rotation == 180)
                {
                    yPosition += (int)movedWay;
                }
            }
            else
            {
                xPosition += (int) movedWay;
            }
        }
    }

    public void calcRouteFromSouth(float secondsElapsedCapped)
    {
        float movedWay = secondsElapsedCapped * speed/(float)0.016;

        /* Check state from trafficLight */
        if((yPosition >= 720) && (yPosition <= 730))
        {
            trafficLightState = crossroad.getDrivewayRoutes().get(3).getTrafficLightModelCar().get(0).getState();
            if(trafficLightState == TrafficLightState.RED)
            {
                return;
            }
        }

        /* Definition from the point to begin of turn */
        if((yPosition <= 535) && (routePositionFlag == false))
        {
            routePositionFlag = true;
        }

        /* programming the routes */
        if(routePositionFlag == false)
        {
            yPosition -= (int)movedWay;
        }
        else
        {
            if (endPoint == FixPoint.west)
            {
                if(rotation != 270 )
                {
                    //turn over long side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.02;
                    xPosition = (int)((635 - 85) + Math.cos(calcTurnAngle) * 85);
                    yPosition = (int)(535 - Math.sin(calcTurnAngle) * 85);
                    if(rotation == 0)
                    {
                        rotation = 360;}
                    rotation -= 1;
                }
                else if(rotation == 270)
                {
                    xPosition -= (int)movedWay;
                }
            }
            else if (endPoint == FixPoint.east)
            {
                if(rotation != 90)
                {
                    //turn over short side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.05;
                    xPosition = (int)((635 + 90) - Math.cos(calcTurnAngle) * 90);
                    yPosition = (int)(535 - Math.sin(calcTurnAngle) * 90);
                    rotation += 2;
                }
                else if(rotation == 90)
                {
                    xPosition += (int)movedWay;
                }
            }
            else
            {
                yPosition -= (int) movedWay;
            }
        }
    }


    public void calcRouteFromEast(float secondsElapsedCapped)
    {
        float movedWay = secondsElapsedCapped * speed/(float)0.016;

        /* Check state from trafficLight */
        if((xPosition >= 890) && (xPosition <= 900))
        {
            trafficLightState = crossroad.getDrivewayRoutes().get(2).getTrafficLightModelCar().get(0).getState();
            if(trafficLightState == TrafficLightState.RED)
            {
                return;
            }
        }

        /* Definition from the point to begin of turn */
        if((xPosition <= 705) && (routePositionFlag == false))
        {
            routePositionFlag = true;
        }

        /* programming the routes */
        if(routePositionFlag == false)
        {
            xPosition -= (int)movedWay;
        }
        else
        {
            if (endPoint == FixPoint.north)
            {
                if(rotation != 360 )
                {
                    //turn over short side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.05;
                    xPosition = (int)(705 - Math.sin(calcTurnAngle) * 90);
                    yPosition = (int)((450 - 90) + Math.cos(calcTurnAngle) * 90);
                    rotation += 2;
                }
                else if(rotation == 360)
                {
                    yPosition -= (int)movedWay;
                }
            }
            else if (endPoint == FixPoint.south)
            {
                if(rotation != 180)
                {
                    //turn over long side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.02;
                    xPosition = (int)(705 - Math.sin(calcTurnAngle) * 85);
                    yPosition = (int)((450 + 85) - Math.cos(calcTurnAngle) * 85);
                    rotation -= 1;
                }
                else if(rotation == 180)
                {
                    yPosition += (int)movedWay;
                }
            }
            else
            {
                xPosition -= (int) movedWay;
            }
        }
    }


    public Position getNewPosition()
    {
        return position;
    }


    public void setNewPosition(float secondsElapsedCapped)
    {
        switch(startPoint)
        {
            case north: {
                calcRouteFromNorth(secondsElapsedCapped);
                break;
            }
            case west: {
                calcRouteFromWest(secondsElapsedCapped);
                break;
            }
            case east: {
                calcRouteFromEast(secondsElapsedCapped);
                break;
            }
            case south: {
                calcRouteFromSouth(secondsElapsedCapped);
                break;
            }
        }

        if((xPosition > 1100) || (yPosition > 1020) || (xPosition < 180) || (yPosition < -80))
        {
            routePositionFlag = false;
            calcTurnAngle = 0;
            xPosition = startPoints.get(startPoint).x;
            yPosition = startPoints.get(startPoint).y;
            rotation = startPoints.get(startPoint).angle;
            this.endPoint = getRandomEndpoint();
            while(startPoint == this.endPoint)
            {
                this.endPoint = getRandomEndpoint();
            }
        }

        position.x = xPosition;
        position.y = yPosition;
        position.angle = rotation;

        notifyObservers();
    }
}
