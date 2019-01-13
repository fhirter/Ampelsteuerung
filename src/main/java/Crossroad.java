import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Crossroad extends Observable {

    /*
    private final String[] allgorithmusTypes = {"Algorithm A",
            "Algorithm B",
            "Algorithm C",
            "Algorithm D",
            "Algorithm E"};

    private final String[] typeOfCrossroad = {"3 Streets",
            "4 Streets"};
*/

    private boolean pedestrianStripes;
    private boolean velostripes;
    private int numberOfCrossings;
    private List<DrivewayRoute> drivewayRoutes = new LinkedList<>();


    public Crossroad(boolean pedestrianStripes, boolean velostripes, int numberOfCrossings) {
        this.pedestrianStripes = pedestrianStripes;
        this.velostripes = velostripes;
        this.numberOfCrossings = numberOfCrossings;
        for (int i = 0; i < numberOfCrossings; i++) {
            drivewayRoutes.add(new DrivewayRoute(pedestrianStripes, velostripes));
        }
    }

    public List<DrivewayRoute> getDrivewayRoute() {
        return drivewayRoutes;
    }

}
