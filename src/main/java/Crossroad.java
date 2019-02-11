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
    private List<DrivewayRouteController> drivewayRouteControllers = new LinkedList<>();
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

        // FH
        List<Point2D> points = new LinkedList<>();
        points.add(new Point2D(0,0));
        points.add(new Point2D(550,-300));
        points.add(new Point2D(850,250));
        points.add(new Point2D(300,550));

        /* Loop to create all driveways */
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    /* West */
                    xPoint = 0;
                    yPoint = 0;
                    break;

                case 1:
                    /* North */
                    xPoint = 550;
                    yPoint = -300;
                    break;

                case 2:
                    /* East */
                    xPoint = 850;
                    yPoint = 250;
                    break;

                case 3:
                    /* South */
                    xPoint = 300;
                    yPoint = 550;
                    break;
            }

            /* create model from the driveway. Model from trafficLights are created into constructor from drivewayRoute */
            DrivewayRoute drivewayRoute = new DrivewayRoute();
            /* create controller */
            DrivewayRouteController drivewayRouteController = new DrivewayRouteController(drivewayRoute, ref,points.get(i), rotateRoute);
            /* add controller to observer from the createt model */
            drivewayRoute.addObserver(drivewayRouteController);

            // FH: drivewayRouteController in CrossroadController erstellen
            //
            drivewayRouteController.getChildren().add(drivewayRoute.getTrafficLightControllerCar().get(0));
            drivewayRouteController.getChildren().add(drivewayRoute.getTrafficLightControllerPedestrian().get(0));
            drivewayRouteController.getChildren().add(drivewayRoute.getTrafficLightControllerPedestrian().get(1));

            /* add driveway with trafficLight into the list from the "main" crossrad */
            drivewayRoutes.add(drivewayRoute);
            drivewayRouteControllers.add(drivewayRouteController);
            rotateRoute += 90;
        }
    }


    /**
     * Crossroad: get the DrivewayRouteController
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
     */
    public List<DrivewayRouteController> getDrivewayRouteControllers()
    {
        // FH: drivewayRoutes zurückgeben zum Zuweisen der Controller
        return drivewayRouteControllers;
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


