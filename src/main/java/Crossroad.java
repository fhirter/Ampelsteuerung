import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.List;

public class Crossroad extends Observable {

    private final String[] algorithms = {"Algorithm A",
            "Algorithm B",
            "Algorithm C",
            "Algorithm D",
            "Algorithm E"};


    private Integer numberOfDriveways;
    private List<DrivewayRoute> drivewayRoutes = new LinkedList<>();
    private List<DrivewayRouteController> drivewayRouteControllers = new LinkedList<>();
    private static Point2D ref = Main.getRef();


    public Crossroad(int numberOfDriveways)
    {

        this.numberOfDriveways = numberOfDriveways;
        double xPoint = 0;
        double yPoint = 0;
        int rotateRoute = 0;

        /* Loop to create all driveways */
        for (int i = 0; i < numberOfDriveways; i++) {
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
            DrivewayRouteController drivewayRouteController = new DrivewayRouteController(drivewayRoute, ref, new Point2D(xPoint, yPoint), rotateRoute);
            /* add controller to observer from the createt model */
            drivewayRoute.addObserver(drivewayRouteController);

            drivewayRouteController.getChildren().add(drivewayRoute.getTrafficLightControllerCar().get(0));
            drivewayRouteController.getChildren().add(drivewayRoute.getTrafficLightControllerPedestrian().get(0));
            drivewayRouteController.getChildren().add(drivewayRoute.getTrafficLightControllerPedestrian().get(1));

            /* add driveway with trafficLight into the list from the "main" crossrad */
            drivewayRoutes.add(drivewayRoute);
            drivewayRouteControllers.add(drivewayRouteController);
            rotateRoute += 90;
        }
    }

    public List<DrivewayRouteController> getDrivewayRouteControllers() {
        return drivewayRouteControllers;
    }

    public String[] getAlgorithms() {
        return algorithms;
    }

    public Integer getNumberOfDriveways() {
        return numberOfDriveways;
    }

    public void setNumberOfDriveways(int numberOfDriveways) {
        this.numberOfDriveways = numberOfDriveways;
        notifyObservers();
    }

    public List<DrivewayRoute> getDrivewayRoutes() {
        return drivewayRoutes;
    }
}


