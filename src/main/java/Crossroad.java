import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class Crossroad: Mainmethode for the TEKO project "Ampelsteuerung".
 *
 *
 * @version 1.0
 * @autor   Class NIN
 * @date    04.11.2018
 */
public class Crossroad extends Application
{
    /**
     * crossroadStart: Start with a new Object from the crossroad.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    02.12.2018
     */
    public void crossroadStart()
    {
        launch(null);
    }

    /**
     * start(Stage primaryStage): Turns all lights on.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    20.11.2018
     * @arg     Stage primaryStage: Object from Stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Ampelsteuerung");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primaryStage.fxml"));
        BorderPane borderPaneLoader = loader.load();
        primaryStage.setScene(new Scene(borderPaneLoader, 500, 800));

        DrivewayModel drivewayModel = new DrivewayModel(true, true, true, true);
        Node drivewayNode = createDriveway(drivewayModel);
        borderPaneLoader.setCenter(drivewayNode);

        //todo Ampel ist Auskommentiert. Testen anschliessend loeschen
//        TrafficLightModel trafficLightModel = new TrafficLightModel(TrafficLightType.CAR);
//        Node trafficLightNode = createNewTrafficLight(trafficLightModel);
//        borderPaneLoader.getChildren().add(trafficLightNode);


        PrimaryStageController controller = loader.getController();
        List<String> algorithm = new LinkedList<>();
        algorithm.add("Algorithm A");
        algorithm.add("Algorithm B");
        algorithm.add("Algorithm C");
        algorithm.add("Algorithm D");
        controller.setSetchoiceOfAlgorithm(algorithm);
        List<String> crossing = new LinkedList<>();
        crossing.add("3 Streets");
        crossing.add("4 Streets");
        crossing.add("5 Streets");
        controller.setSetnumberOfCrossing(crossing);


        primaryStage.show();
    }


    private Node createDriveway(DrivewayModel model) throws IOException {

        //todo: Achtung: Beim Observer wird eine andere Instanz vom Controller uebergeben!

        Node nodeDriveway;

        DrivewayController drivewayController = new DrivewayController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("drivewayView.fxml"));
        nodeDriveway = fxmlLoader.load();

        model.addObserver(drivewayController);

        return nodeDriveway;
    }


    /**
     * createNewTrafficLight(): Implements a new Instance from trafficLight
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    20.11.2018
     * @arg     TrafficLightModel: Instance form the model
     * @return  Node: Index from the Node fxmlLoader from trafficLight
     */
    private Node createNewTrafficLight(TrafficLightModel model) throws java.io.IOException
    {
        Node nodeTrafficLight;

        TrafficLightController trafficLightController = new TrafficLightController(model);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));
        fxmlLoader.setController(trafficLightController);
        nodeTrafficLight = fxmlLoader.load();

        model.addObserver(trafficLightController);

        return nodeTrafficLight;
    }
}


