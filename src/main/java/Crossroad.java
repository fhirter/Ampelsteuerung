import java.util.LinkedList;
import java.util.List;

public class Crossroad extends Observable {

    private final String[] algorithms = {"Algorithm A",
            "Algorithm B",
            "Algorithm C",
            "Algorithm D",
            "Algorithm E"};
    private final Point2D referencePoint;

    private Integer numberOfDriveways = 4;
    private Map<Direction,DrivewayRoute> drivewayRoutes = new HashMap<>();
    private CenterPane centerPaneModel;
    private Crossroad crossroad;
    private CrossroadController crossroadController;
    private List<VehicleModel> vehicleModelList = new LinkedList<>();
    private List<VehicleController> vehicleControllerList = new LinkedList<>();
    private int counterTypeOfVehicles = 0;
    private int countOfMovedElements = 0;

    private final Map<Direction, Position> startPositions = new HashMap<>();

    private List<DrivewayRoute> drivewayRoutes = new LinkedList<>();

    private Area turningArea;

    private class Area {
        private int size;
        private Point2D center;


        public Area(int size, Point2D center){
            this.size = size;
            this.center = center;
        }; // just use default values

        public boolean isInside(Position position) {
            if(position.x > (center.getX()-size/2) && position.x < (center.getX()+size/2) ) {
                if(position.y > (center.getY()-size/2) && position.y < (center.getY()+size/2)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Crossroad: Constructor
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */

    public Crossroad(Point2D ref)
    {
        this.referencePoint = ref;
        turningArea = new Area(180, referencePoint);


        /* Loop to create all driveways */
        for (int i = 0; i < 4; i++) {
            DrivewayRoute drivewayRoute = new DrivewayRoute();
            Direction directionValue = Direction.values()[i];
            drivewayRoutes.put(directionValue, drivewayRoute);
            rotateRoute += 90;

            TrafficLight trafficLightCar = new TrafficLight(TrafficLightType.CAR);
            TrafficLight trafficLightPedestrian = new TrafficLight(TrafficLightType.PEDESTRIAN);
            Map<TrafficLightType, TrafficLight> trafficLights = new HashMap<>();
            trafficLights.put(TrafficLightType.CAR, trafficLightCar);
            trafficLights.put(TrafficLightType.PEDESTRIAN, trafficLightPedestrian);
            trafficLightsDirection.put(directionValue, trafficLights);

        }



        GameLoop gameLoop = new GameLoop(this);
        gameLoop.start();

        startPositions.put(Direction.WEST, new Position(300, 465, 90));
        startPositions.put(Direction.NORTH, new Position(615, 100, 180));
        startPositions.put(Direction.EAST, new Position(950, 450, 270));
        startPositions.put(Direction.SOUTH, new Position(635, 800,0));
    }

    /**
     * CrossRoad: get the algorithms
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public String[] getAlgorithms()
    {
        return algorithms;
    }
    /**
     * Crossroad: get Max of DrivewayRoutes
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public Integer getNumberOfDriveways() {
        return numberOfDriveways;
    }

    /**
     * Crossroad: get the Object DrivewayRoute
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public DrivewayRoute getDrivewayRoute(Direction direction) {
        return drivewayRoutes.get(direction);
    }


    /**
     * Crossroad: set the Center of Crossroad
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     * @arg centerPaneModel (Object of the Center)
     */
    public void setCenterPaneModel(CenterPane centerPaneModel)
    {
        this.centerPaneModel = centerPaneModel;
    }

    /**
     * Crossroad: set number of Driveways
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     * @arg centerPaneModel (Object of the Center)
     */
    public void setNumberOfDriveways(Integer numberOfDriveways){
        this.numberOfDriveways = numberOfDriveways;
        centerPaneModel.updateNumberOfCrossroad(numberOfDriveways);
    }


    /**
     * setStateFromTrafficLight: set number of Driveways
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    02.03.2019
     * @arg     fixpoint (orientation from the trafficLight (CAR))
     * @arg     trafficLightState (State from the trafficLight)
     */
    public void setStateFromTrafficLight(Direction fixpoint, TrafficLightState trafficLightState)
    {
        drivewayRoutes.get(fixpoint).getTrafficLightModelCar().setState(trafficLightState);
    }


    public void startMovedElements(CrossroadController crossroadController, int amountOfVehicles)
    {
        /* Generate and start MovedElements */
        this.crossroadController = crossroadController;
        this.countOfMovedElements = amountOfVehicles;
        generateVehicles(amountOfVehicles);
    }


    public void generateVehicles(int count)
    {
        if(vehicleModelList.size() != 0)
        {
            vehicleModelList.clear();
            for (int i = crossroadController.getChildren().size()-1; i > 0; i--)
            {
                if(vehicleControllerList.indexOf(crossroadController.getChildren().get(i)) != -1)
                {
                    crossroadController.getChildren().remove(i);
                }
            }
            vehicleControllerList.clear();
    public boolean canITurn(Position position) {
        if(turningArea.isInside(position)) {
            return true;
        }

        for(int i = 0; i < count; i++)
        {
            VehicleModel vehicleModel = new VehicleModel(this, getAllTypesOfVehicles(), getRandomStartpoint());
            VehicleController vehicleController = new VehicleController(vehicleModel);
            vehicleControllerList.add(vehicleController);
            vehicleModel.addObserver(vehicleController);

            crossroadController.getChildren().add(vehicleController);
            vehicleModelList.add(vehicleModel);
        }
    }


    private vehicleType getAllTypesOfVehicles()
    {
        counterTypeOfVehicles++;
        if(counterTypeOfVehicles >= vehicleType.values().length){
            counterTypeOfVehicles = 0;}
        return vehicleType.values()[counterTypeOfVehicles];
    }


    public Direction getRandomStartpoint()
    {
        int rndNumber = 0;
        Random random = new Random();
        rndNumber = random.nextInt(numberOfDriveways);
        return Direction.values()[rndNumber];
    }


    public void calculatePositions(float secondsElapsedCapped)
    {
        for (int i = 0; i < vehicleModelList.size(); i++)
        {
            vehicleModelList.get(i).setNewPosition(secondsElapsedCapped);
        }
    }

    public Position getStartPosition(Direction start) {
        return startPositions.get(start);
    }

    public boolean isDestinationValid(Direction destination) {
        if(numberOfDriveways == 3 && destination == Direction.SOUTH) {
            return false;
        } else {
            return true;
        }
    }
}


