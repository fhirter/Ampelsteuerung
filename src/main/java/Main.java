import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;


/**
 * Class Main: Mainmethode for the TEKO project "Ampelsteuerung".
 *
 * LONG DESCRIOTION
 *
 * @version 1.0
 * @autor   Class NIN
 * @date    04.11.2018
 */
public class Main extends Application {

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
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Ampelsteuerung");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primaryStage.fxml"));
        BorderPane borderPaneLoader = loader.load();
        primaryStage.setScene(new Scene(borderPaneLoader, 500, 800));

        PrimaryStageController primaryStageController = loader.getController();
        List<String> algorithm = new LinkedList<>();
        algorithm.add("Algorithm A");
        algorithm.add("Algorithm B");
        algorithm.add("Algorithm C");
        algorithm.add("Algorithm D");
        primaryStageController.setSetchoiceOfAlgorithm(algorithm);
        List<String> crossing = new LinkedList<>();
        crossing.add("3 Streets");
        crossing.add("4 Streets");
        crossing.add("5 Streets");
        primaryStageController.setSetnumberOfCrossing(crossing);

        /*TrafficLight
        Node nodeTrafficLight;
        TrafficLightModel trafficLightModel = new TrafficLightModel();
        TrafficLightController trafficLightController = new TrafficLightController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));
        nodeTrafficLight = fxmlLoader.load();
        fxmlLoader.setController(trafficLightController);
        trafficLightController.setModel(trafficLightModel);
        trafficLightModel.addObserver(fxmlLoader.getController());
        borderPaneLoader.getChildren().add(nodeTrafficLight);
*/



        FXMLLoader loaderDriveway = new FXMLLoader(getClass().getResource("driveway.fxml"));
        loaderDriveway.setController(primaryStageController);

        DrivewayModel drivewayModel = new DrivewayModel();
        drivewayModel.addObserver(primaryStageController);

        Node drivewayNode = (Pane) FXMLLoader.load(getClass().getResource("driveway.fxml"));
        borderPaneLoader.setCenter(drivewayNode);

















        primaryStage.show();
    }


    @Override
    public void stop()
    {

    }


    /**
     * main(String[] args): Main routine,.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    20.11.2018
     */
    public static void main(String[] args)
    {
        launch(args);
        System.out.println("Start project.");
    }


    /**
     * createNewTrafficLight(): Implements a new Instance from trafficLight
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    20.11.2018
     * @arg     Node: Index from the Node fxmlLoader from trafficLight
     */
    private Node createNewTrafficLight() throws java.io.IOException
    {


        return null;



    }
}


