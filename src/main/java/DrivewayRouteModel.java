import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.util.HashMap;

/**
 * Class DrivewayRouteModel: Data model for the crossroad
 *
 * @version 1.0
 * @autor   Class NIN
 * @date   14.11.2018
 */

public class DrivewayRouteModel extends Observable
{
    private HashMap<String, Boolean> settingsFromCheckBoxes;

    /**
     * Constructor DrivewayRouteModel: Constructor for the DrivewayModel
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   30.11.2018
     */
    public DrivewayRouteModel(HashMap<String, HashMap> settingsForCrossroad)
    {
        this.settingsFromCheckBoxes = settingsForCrossroad.get("checkboxes");
    }


    public HashMap<String, Boolean> getStateFromChckboxes()
    {
        return this.settingsFromCheckBoxes;
    }


/**
 * createTrafficLights(): Creates on the DrivewayRoute the appopriate trafficlights
 *
 *
 * @version 1.0
 * @autor   Schweizer Patrick
 * @date    30.12.2018
 * @arg     TrafficLightType: (Type from trafficLight which should be create)
 * @return  Node: Index from the TrafficLight Node. Can be needed in xxxx.getChild().add(Node);
 */
    public Node createTrafficLight(TrafficLightType trafficLightType) throws java.io.IOException
    {
        Node nodeTrafficLight;

        TrafficLightModel trafficLightModel = new TrafficLightModel(trafficLightType);
        TrafficLightController trafficLightController = new TrafficLightController(trafficLightModel);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));
        fxmlLoader.setController(trafficLightController);
        nodeTrafficLight = fxmlLoader.load();

        trafficLightModel.addObserver(trafficLightController);

        return nodeTrafficLight;
    }
}





