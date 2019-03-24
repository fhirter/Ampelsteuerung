
public class VehicleModel extends Observable
{
    private Crossroad crossroad;

    private vehicleType vehicleType;
    private Direction start;
    private Direction destination;
    private Position position;
    private float speed;

    private float calcTurnAngle;
    private boolean isTurningInProgress = false;

    public VehicleModel(Crossroad crossroad, vehicleType vehicleType, Direction start)
    {
        this.crossroad = crossroad;
        this.vehicleType = vehicleType;
        this.start = start;

        position = new Position(crossroad.getStartPosition(start));

        findValidDestination();

        switch(vehicleType)
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

    public vehicleType getVehicleType()
    {
        return vehicleType;
    }


    public Position getPosition()
    {
        return position;
    }




    public void calcRouteFromNorth(float secondsElapsedCapped)
    {
        float movedWay = secondsElapsedCapped * speed/(float)0.016;

        /* Check state from trafficLight */
        if((position.y >= 180) && (position.y <= 190))
        {
            TrafficLightState trafficLightState = crossroad.getDrivewayRoute(Direction.NORTH).getTrafficLightModelCar().getState();
            if(trafficLightState == TrafficLightState.RED)
            {
                return;
            }
        }

        /* Definition from the point to begin of turn */
        if((position.y >= 380) && (isTurningInProgress == false))
        {
            isTurningInProgress = true;
        }

        /* programming the routes */
        if(isTurningInProgress == false)
        {
            position.y += (int)movedWay;
        }
        else
        {
            if (destination == Direction.WEST) {
                if (position.angle != 270) {
                    //turn over short side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.05;
                    position.x = (int) ((615 - 90) + Math.cos(calcTurnAngle) * 90);
                    position.y = (int) (380 + Math.sin(calcTurnAngle) * 90);
                    position.angle += 2;
                } else if (position.angle == 270) {
                    position.x -= (int) movedWay;
                }
            } else if (destination == Direction.EAST) {
                if (position.angle != 90) {
                    //turn over long side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.02;
                    position.x = (int) ((615 + 85) - Math.cos(calcTurnAngle) * 85);
                    position.y = (int) ((380) + Math.sin(calcTurnAngle) * 85);
                    position.angle -= 1;
                } else if (position.angle == 90) {
                    position.x += (int) movedWay;
                }
            } else {
                position.y += (int) movedWay;
            }
        }
    }


    public void calcRouteFromWest(float secondsElapsedCapped)
    {
        float movedWay = secondsElapsedCapped * speed/(float)0.016;

        /* Check state from trafficLight */
        if((position.x >= 350) && (position.x <= 360))
        {
            TrafficLightState trafficLightState = crossroad.getDrivewayRoute(Direction.WEST).getTrafficLightModelCar().getState();
            if(trafficLightState == TrafficLightState.RED)
            {
                return;
            }
        }

        /* Definition from the point to begin of turn */
        if((position.x >= 550) && (isTurningInProgress == false))
        {
            isTurningInProgress = true;
        }

        /* programming the routes */
        if(isTurningInProgress == false)
        {
            position.x += (int)movedWay;
        }
        else
        {
            if (destination == Direction.NORTH)
            {
                if(position.angle != 0 )
                {
                    //turn over long side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.02;
                    position.x = (int) (550 + Math.sin(calcTurnAngle) * 85);
                    position.y = (int) ((465 - 85) + Math.cos(calcTurnAngle) * 85);
                    position.angle -= 1;
                }
                else if(position.angle == 0)
                {
                    position.y -= (int)movedWay;
                }
            }
            else if (destination == Direction.SOUTH)
            {
                if(position.angle != 180)
                {
                    //turn over short side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.05;
                    position.x = (int)(550 + Math.sin(calcTurnAngle) * 90);
                    position.y = (int)((465 + 90) - Math.cos(calcTurnAngle) * 90);
                    position.angle += 2;
                }
                else if(position.angle == 180)
                {
                    position.y += (int)movedWay;
                }
            }
            else
            {
                position.x += (int) movedWay;
            }
        }
    }

    public void calcRouteFromSouth(float secondsElapsedCapped)
    {
        float movedWay = secondsElapsedCapped * speed/(float)0.016;

        /* Check state from trafficLight */
        if((position.y >= 720) && (position.y <= 730))
        {
            TrafficLightState trafficLightState = crossroad.getDrivewayRoute(Direction.SOUTH).getTrafficLightModelCar().getState();
            if(trafficLightState == TrafficLightState.RED)
            {
                return;
            }
        }

        /* Definition from the point to begin of turn */
        if((position.y <= 535) && (isTurningInProgress == false))
        {
            isTurningInProgress = true;
        }

        /* programming the routes */
        if(isTurningInProgress == false)
        {
            position.y -= (int)movedWay;
        }
        else
        {
            if (destination == Direction.WEST)
            {
                if(position.angle != 270 )
                {
                    //turn over long side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.02;
                    position.x = (int)((635 - 85) + Math.cos(calcTurnAngle) * 85);
                    position.y = (int)(535 - Math.sin(calcTurnAngle) * 85);
                    if(position.angle == 0)
                    {
                        position.angle = 360;}
                    position.angle -= 1;
                }
                else if(position.angle == 270)
                {
                    position.x -= (int)movedWay;
                }
            }
            else if (destination == Direction.EAST)
            {
                if(position.angle != 90)
                {
                    //turn over short side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.05;
                    position.x = (int)((635 + 90) - Math.cos(calcTurnAngle) * 90);
                    position.y = (int)(535 - Math.sin(calcTurnAngle) * 90);
                    position.angle += 2;
                }
                else if(position.angle == 90)
                {
                    position.x += (int)movedWay;
                }
            }
            else
            {
                position.y -= (int) movedWay;
            }
        }
    }


    public void calcRouteFromEast(float secondsElapsedCapped)
    {
        float movedWay = secondsElapsedCapped * speed/(float)0.016;

        /* Check state from trafficLight */
        if((position.x >= 890) && (position.x <= 900))
        {
            TrafficLightState trafficLightState = crossroad.getDrivewayRoute(Direction.EAST).getTrafficLightModelCar().getState();
            if(trafficLightState == TrafficLightState.RED)
            {
                return;
            }
        }

        /* Definition from the point to begin of turn */
        if((position.x <= 705) && (isTurningInProgress == false))
        {
            isTurningInProgress = true;
        }

        /* programming the routes */
        if(isTurningInProgress == false)
        {
            position.x -= (int)movedWay;
        }
        else
        {
            if (destination == Direction.NORTH)
            {
                if(position.angle != 360 )
                {
                    //turn over short side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.05;
                    position.x = (int)(705 - Math.sin(calcTurnAngle) * 90);
                    position.y = (int)((450 - 90) + Math.cos(calcTurnAngle) * 90);
                    position.angle += 2;
                }
                else if(position.angle == 360)
                {
                    position.y -= (int)movedWay;
                }
            }
            else if (destination == Direction.SOUTH)
            {
                if(position.angle != 180)
                {
                    //turn over long side
                    /* Formel
                        x = xcenter + cos (angle) * rad;
                        y = ycenter + sin (angle) * rad;
                    */
                    calcTurnAngle += 0.02;
                    position.x = (int)(705 - Math.sin(calcTurnAngle) * 85);
                    position.y = (int)((450 + 85) - Math.cos(calcTurnAngle) * 85);
                    position.angle -= 1;
                }
                else if(position.angle == 180)
                {
                    position.y += (int)movedWay;
                }
            }
            else
            {
                position.x -= (int) movedWay;
            }
        }
    }


    public void setNewPosition(float secondsElapsedCapped)
    {
        switch(start)
        {
            case NORTH: {
                calcRouteFromNorth(secondsElapsedCapped);
                break;
            }
            case WEST: {
                calcRouteFromWest(secondsElapsedCapped);
                break;
            }
            case EAST: {
                calcRouteFromEast(secondsElapsedCapped);
                break;
            }
            case SOUTH: {
                calcRouteFromSouth(secondsElapsedCapped);
                break;
            }
        }

        if((position.x > 1100) || (position.y > 1020) || (position.x < 180) || (position.y < -80))
        {
            isTurningInProgress = false;
            calcTurnAngle = 0;
            Position restoreStartPosition = new Position(crossroad.getStartPosition(start));
            position.x = restoreStartPosition.x;
            position.y = restoreStartPosition.y;
            position.angle = restoreStartPosition.angle;

            findValidDestination();
        }

        notifyObservers();
    }

    private void findValidDestination() {
        this.destination = Direction.getRandomDirection();

        while(start == this.destination || !crossroad.isDestinationValid(destination))
        {
            this.destination = Direction.getRandomDirection();
        }
    }
}
