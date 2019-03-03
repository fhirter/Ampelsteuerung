import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Crossroad extends Observable {

    private final String[] algorithms = {"Algorithm A",
            "Algorithm B",
            "Algorithm C",
            "Algorithm D",
            "Algorithm E"};

    private Integer numberOfDriveways = 4;
    private List<DrivewayRoute> drivewayRoutes = new LinkedList<>();
    private CenterPane centerPaneModel;
    private Crossroad crossroad;
    private CrossroadController crossroadController;
    private List<VehicleModel> vehicleModelList = new LinkedList<>();
    private List<VehicleController> vehicleControllerList = new LinkedList<>();
    private int counterTypeOfMovedElements = 0;
    private int countOfMovedElements = 0;


    /**
     * Crossroad: Constructor
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */

    public Crossroad()
    {
        double xPoint = 0;
        double yPoint = 0;
        int rotateRoute = 0;

        /* Loop to create all driveways */
        for (int i = 0; i < 4; i++) {
            DrivewayRoute drivewayRoute = new DrivewayRoute();
            drivewayRoutes.add(drivewayRoute);
            rotateRoute += 90;
        }

        GameLoop gameLoop = new GameLoop(this);
        gameLoop.start();
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
    public List<DrivewayRoute> getDrivewayRoutes()
    {
        return drivewayRoutes;
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
    public void setStateFromTrafficLight(FixPoint fixpoint, TrafficLightState trafficLightState)
    {
        if(FixPoint.north == fixpoint){
            if(TrafficLightState.RED == trafficLightState){
                drivewayRoutes.get(1).getTrafficLightModelCar().get(0).setRed();
            }else{
                drivewayRoutes.get(1).getTrafficLightModelCar().get(0).setGreen();
            }
        }

        if(FixPoint.west == fixpoint){
            if(TrafficLightState.RED == trafficLightState){
                drivewayRoutes.get(0).getTrafficLightModelCar().get(0).setRed();
            }else{
                drivewayRoutes.get(0).getTrafficLightModelCar().get(0).setGreen();
            }
        }

        if(FixPoint.south == fixpoint){
            if(TrafficLightState.RED == trafficLightState){
                drivewayRoutes.get(3).getTrafficLightModelCar().get(0).setRed();
            }else{
                drivewayRoutes.get(3).getTrafficLightModelCar().get(0).setGreen();
            }
        }

        if(FixPoint.east == fixpoint){
            if(TrafficLightState.RED == trafficLightState){
                drivewayRoutes.get(2).getTrafficLightModelCar().get(0).setRed();
            }else{
                drivewayRoutes.get(2).getTrafficLightModelCar().get(0).setGreen();
            }
        }
    }


    public void startMovedElements(CrossroadController crossroadController, int countOfMovedElements)
    {
        /* Generate and start MovedElements */
        this.crossroadController = crossroadController;
        this.countOfMovedElements = countOfMovedElements;
        generateMovedElements(countOfMovedElements);
    }


    public void generateMovedElements(int count)
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
        }

        for(int i = 0; i < count; i++)
        {
            VehicleModel vehicleModel = new VehicleModel(this, getAllTypesOfMovedElement(), getRandomStartpoint(), numberOfDriveways);
            VehicleController vehicleController = new VehicleController(vehicleModel);
            vehicleControllerList.add(vehicleController);
            vehicleModel.addObserver(vehicleController);

            crossroadController.getChildren().add(vehicleController);
            vehicleModelList.add(vehicleModel);
        }
    }


    private MovedElement getAllTypesOfMovedElement()
    {
        counterTypeOfMovedElements ++;
        if(counterTypeOfMovedElements >= MovedElement.values().length){
            counterTypeOfMovedElements = 0;}
        return MovedElement.values()[counterTypeOfMovedElements];
    }


    public FixPoint getRandomStartpoint()
    {
        int rndNumber = 0;
        Random random = new Random();
        rndNumber = random.nextInt(numberOfDriveways);
        return FixPoint.values()[rndNumber];
    }


    public void calculatePositions(float secondsElapsedCapped)
    {
        for (int i = 0; i < vehicleModelList.size(); i++)
        {
            vehicleModelList.get(i).setNewPosition(secondsElapsedCapped);
        }
    }
}


