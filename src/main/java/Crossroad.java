import java.util.LinkedList;
import java.util.List;

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

}


