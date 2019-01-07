import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class DrivewayRoute: Data model for the crossroad
 *
 * @version 1.0
 * @autor   Class NIN
 * @date   14.11.2018
 */

public class DrivewayRoute extends Observable
{

    private List<TrafficLight> trafficLights;
    private boolean pedestrianStripes;
    private boolean velostripes;

    public DrivewayRoute(boolean pedestrianStripes, boolean velostripes) {
        this.pedestrianStripes = pedestrianStripes;
        this.velostripes = velostripes;

    }







  /*  public HashMap<String, Boolean> getStateFromChckboxes()
    {
        return this.settingsFromCheckBoxes;
    }

*/
/**
 * createTrafficLights(): Creates on the DrivewayRoute the appopriate trafficlights
 *
 *
 * @version 1.0
 * @autor   Schweizer Patrick
 * @date    30.12.2018
 * @arg     TrafficLight: (Index from TrafficLight)
 * @return  Node: Index from the TrafficLight Node. Can be needed in xxxx.getChild().add(Node);
 */
    public Node createTrafficLight(TrafficLight trafficLight) throws java.io.IOException
    {
        Node nodeTrafficLight;

        TrafficLightController trafficLightController = new TrafficLightController(trafficLight);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));
        fxmlLoader.setController(trafficLightController);
        nodeTrafficLight = fxmlLoader.load();

        trafficLight.addObserver(trafficLightController);

        return nodeTrafficLight;
    }
}







