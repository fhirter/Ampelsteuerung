import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Crossroad extends Observable {

    private final String[] algorithms = {"Algorithm A",
            "Algorithm B",
            "Algorithm C",
            "Algorithm D",
            "Algorithm E"};

    private final String[] possibleNumberOfDriveways = {"4", "3"};
    private List<DrivewayRoute> drivewayRoutes = new LinkedList<>();
    private static Point2D ref = Main.getRef();
    private CenterPane centerPaneModel;

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
     * Crossroad: get the number of Drivewasy
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public String[] getNumberOfDriveways() // FH: besser: int getMaxDrivewayCount() und int getMinDrivewayCount()
    {
        return possibleNumberOfDriveways;
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
    public void setNumberOfDriveways(String numberOfDriveways)
    {
        centerPaneModel.updateNumberOfCrossroad(numberOfDriveways);
        if(numberOfDriveways == "3") {
            drivewayRoutes.get(3).setVisibility(false);
        }else{
            drivewayRoutes.get(3).setVisibility(true);
        }
    }
}


