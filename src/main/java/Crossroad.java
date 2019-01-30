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

    private boolean pedestrianStripes;
    private boolean velostripes;
    private Integer numberOfDriveways;
    private List<DrivewayRoute> drivewayRoutes = new LinkedList<>();
    private List<DrivewayRouteController> drivewayRouteControllers = new LinkedList<>();
    private static Point2D ref = Main.getRef();

    public Crossroad(boolean pedestrianStripes, boolean velostripes, int numberOfDriveways)
    {
        this.pedestrianStripes = pedestrianStripes;
        this.velostripes = velostripes;
        this.numberOfDriveways = numberOfDriveways;
        double x =0;
        int rot = 0;
        double y =0;
        for (int i = 0; i < numberOfDriveways; i++)
        {

            switch(i) {
                case 0:
                    x =0;
                    y = 0;
                    break;

                case 1:
                    x =550;
                    y = -300;
                    break;

                case 2:
                    x =850;
                    y = 250;
                    break;

                case 3:
                    x =300;
                    y = 550;
                    break;

            }

            DrivewayRoute drivewayRoute = new DrivewayRoute(pedestrianStripes,velostripes);
            DrivewayRouteController drivewayRouteController = new DrivewayRouteController(drivewayRoute, ref, new Point2D(x,y), rot);
            drivewayRoute.addObserver(drivewayRouteController);
            drivewayRouteController.getChildren().add(drivewayRoute.getTrafficLightControllerCar().get(0));
            drivewayRouteController.getChildren().add(drivewayRoute.getTrafficLightControllerPedestrian().get(0));
            drivewayRouteControllers.add(drivewayRouteController);
            drivewayRoutes.add(drivewayRoute);
            notifyObservers();

            rot += 90;


        }

    }

    public List<DrivewayRouteController> getDrivewayRouteControllers() {
        return drivewayRouteControllers;

    }

    public List<DrivewayRoute> getDrivewayRoutes() {
        return drivewayRoutes;
    }

    public String[] getAlgorithms() {
        return algorithms;
    }

    public Integer getNumberOfDriveways() {
        return numberOfDriveways;
    }

    public boolean getPedestrianStripes() {
        return pedestrianStripes;
    }

    public boolean getVelostripes() {
        return velostripes;
    }

    public void setPedestrianStripes(boolean pedestrianStripes) {
        this.pedestrianStripes = pedestrianStripes;
        notifyObservers();
    }

    public void setVelostripes(boolean velostripes) {
        this.velostripes = velostripes;
        notifyObservers();
    }

    public void setNumberOfDriveways(int numberOfDriveways) {
        this.numberOfDriveways = numberOfDriveways;
        notifyObservers();

}
}


