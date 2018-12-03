import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class Crossroad: Mainmethode for the TEKO project "Ampelsteuerung".
 *
 * LONG DESCRIOTION
 *
 * @version 1.0
 * @autor   Class NIN
 * @date    04.11.2018
 */
public class Crossroad extends Application {

    public static void main(String[] args) {
        launch(args);

        System.out.println("Start project.");

    }


    public Crossroad() {
    }

    /**
     * start(Stage primaryStage): Turns all lights on.
     *
     * @version 1.0
     * @autor Schweizer Patrick
     * @date 20.11.2018
     * @arg Stage primaryStage: Object from Stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        PrimaryStageController primaryStageController = new PrimaryStageController();


        primaryStage.setTitle("Ampelsteuerung");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primaryStage.fxml"));
        BorderPane borderPaneLoader = loader.load();


        DrivewayModel drivewayModel = new DrivewayModel(true, false, false, false);
        Node drivewayNode = createDriveway(drivewayModel);
        borderPaneLoader.setCenter(drivewayNode);


        primaryStage.setScene(new Scene(borderPaneLoader, 1000, 800));

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


        TrafficLightModel trafficLightModel = new TrafficLightModel(TrafficLightType.CAR);
        Node trafficLightNode = createNewTrafficLight(trafficLightModel);

        borderPaneLoader.getChildren().add(trafficLightNode);
        primaryStage.show();

//toDo: muss noch geloescht werden. Dient lediglich zum Testen
//        trafficLightModel.setYellow();
//        trafficLightModel.setGreen();
//        trafficLightModel.setRed();
//        trafficLightModel.setYellowRed();
//        trafficLightModel.setAllOn();
//        trafficLightModel.setDark();
    }

    private Node createDriveway(DrivewayModel model) throws IOException {

        Node nodeDriveway;

        DrivewayController drivewayController = new DrivewayController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("drivewayView.fxml"));
        nodeDriveway = fxmlLoader.load();
        fxmlLoader.setController(drivewayController);
        model.addObserver(drivewayController);



        return nodeDriveway;
    }


    @Override
    public void stop() {

    }

    /**
     * createNewTrafficLight(): Implements a new Instance from trafficLight
     *
     * @return Node: Index from the Node fxmlLoader from trafficLight
     * @version 1.0
     * @autor Schweizer Patrick
     * @date 20.11.2018
     * @arg TrafficLightModel: Instance form the model
     */
    private Node createNewTrafficLight(TrafficLightModel model) throws java.io.IOException {
        Node nodeTrafficLight;

        TrafficLightController trafficLightController = new TrafficLightController(model);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));
        fxmlLoader.setController(trafficLightController);
        nodeTrafficLight = fxmlLoader.load();

        model.addObserver(trafficLightController);

        return nodeTrafficLight;
    }


    }
