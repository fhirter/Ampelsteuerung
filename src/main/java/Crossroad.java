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

        PrimaryStageModel primaryStageModel = new PrimaryStageModel();
        PrimaryStageController primaryStageController = new PrimaryStageController(primaryStageModel);
        FXMLLoader fxmlLoaderPrimaryStage = new FXMLLoader(getClass().getResource("primaryStage.fxml"));
        fxmlLoaderPrimaryStage.setController(primaryStageController);
        BorderPane borderPaneLoaderPrimaryStage = fxmlLoaderPrimaryStage.load();
        primaryStage.setScene(new Scene(borderPaneLoaderPrimaryStage, 800, 800));

        borderPaneLoaderPrimaryStage.setCenter(createDriveway());


        //todo Ampel ist Auskommentiert. Testen anschliessend loeschen
//        TrafficLightModel trafficLightModel = new TrafficLightModel(TrafficLightType.CAR);
//        Node trafficLightNode = createNewTrafficLight(trafficLightModel);
//        trafficLightNode.setLayoutX(100);
//        trafficLightNode.setLayoutY(100);
//        borderPaneLoaderPrimaryStage.getChildren().add(trafficLightNode);

        primaryStage.show();
    }


     private Node createDriveway() throws IOException {

        Node nodeDriveway;

        DrivewayController drivewayController = new DrivewayController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("drivewayView.fxml"));
        nodeDriveway = fxmlLoader.load();
        fxmlLoader.setController(drivewayController);
        DrivewayModel drivewayModel = new DrivewayModel(false, false, false, false);

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


