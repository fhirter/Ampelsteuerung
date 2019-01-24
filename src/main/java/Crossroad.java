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


    public Crossroad(boolean pedestrianStripes, boolean velostripes, int numberOfDriveways)
    {
        this.pedestrianStripes = pedestrianStripes;
        this.velostripes = velostripes;
        this.numberOfDriveways = numberOfDriveways;

        for (int i = 0; i < numberOfDriveways; i++)
        {
            drivewayRoutes.add(new DrivewayRoute(pedestrianStripes, velostripes));

        }

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


