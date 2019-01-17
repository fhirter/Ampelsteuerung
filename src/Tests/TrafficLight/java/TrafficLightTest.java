import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class TrafficLightTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        TrafficLightTestModel testModel = new TrafficLightTestModel();

        primaryStage.setTitle("Testprozedure Ampel");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("trafficLightControl.fxml"));
        Pane paneLoader = loader.load();
        TrafficLightTestController testController = loader.getController();
        primaryStage.setScene(new Scene(paneLoader, 400, 200));
        testController.setModel(testModel);

        /* Create models for testing */
        /* 2x CAR (only 1 object: mirroring) */
        TrafficLight trafficLightCAR = new TrafficLight(TrafficLightType.CAR);
//        Node nodeTestProzedureCAR = createNewTrafficLight(trafficLightCAR);
//        Node nodeTestProzedureCARMirror = createNewTrafficLight(trafficLightCAR);
        /* 1x PEDESTRIAN (with own object) */
        TrafficLight trafficLightPEDESTRIAN = new TrafficLight(TrafficLightType.PEDESTRIAN);
//        Node nodeTestProzedurePEDESTRIAN = createNewTrafficLight(trafficLightPEDESTRIAN);


        /* Define place into GUI */
//        nodeTestProzedureCAR.setLayoutX(0);
//        nodeTestProzedureCAR.setLayoutY(0);
//        nodeTestProzedureCARMirror.setLayoutX(50);
//        nodeTestProzedureCARMirror.setLayoutY(0);
//        nodeTestProzedurePEDESTRIAN.setLayoutX(120);
//        nodeTestProzedurePEDESTRIAN.setLayoutY(0);


//        paneLoader.getChildren().add(nodeTestProzedureCAR);
//        paneLoader.getChildren().add(nodeTestProzedureCARMirror);
//        paneLoader.getChildren().add(nodeTestProzedurePEDESTRIAN);

        primaryStage.show();

        /* Add the models from testing */
        testModel.addModelsForTesting(trafficLightCAR);
        testModel.addModelsForTesting(trafficLightPEDESTRIAN);
    }


/*    private Node createNewTrafficLight(TrafficLight model) throws java.io.IOException
    {
        Node nodeTestProzedure;

        TrafficLightController trafficLightController = new TrafficLightController(model);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));
        fxmlLoader.setController(trafficLightController);
        nodeTestProzedure = fxmlLoader.load();

        model.addObserver(trafficLightController);

        return nodeTestProzedure;
    }
    */
}





//        TrafficLight trafficLightModel = new TrafficLight(TrafficLightType.CAR);
//        Node trafficLightNode = createNewTrafficLight(trafficLightModel);
//        trafficLightNode.setLayoutX(100);
//        trafficLightNode.setLayoutY(100);
//        borderPaneLoaderPrimaryStage.getChildren().add(trafficLightNode);

    /**
     * createNewTrafficLight(): Create a new node from the trafficLight
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    20.11.2018
     * @arg     TrafficLight: Instance form the model
     * @return  Node: Index from the Node fxmlLoader from trafficLight
     */
/*    private Node createNewTrafficLight(TrafficLight model) throws java.io.IOException
    {
        Node nodeTrafficLight;

        TrafficLightController trafficLightController = new TrafficLightController(model);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));
        fxmlLoader.setController(trafficLightController);
        nodeTrafficLight = fxmlLoader.load();

        model.addObserver(trafficLightController);

        return nodeTrafficLight;
    }

*/

