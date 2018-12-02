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
        TrafficLightModel trafficLightModelCAR = new TrafficLightModel(TrafficLightType.CAR);
        Node nodeTestProzedureCAR = createNewTrafficLight(trafficLightModelCAR);
        Node nodeTestProzedureCARMirror = createNewTrafficLight(trafficLightModelCAR);
        /* 1x PEDESTRIAN (with own object) */
        TrafficLightModel trafficLightModelPEDESTRIAN = new TrafficLightModel(TrafficLightType.PEDESTRIAN);
        Node nodeTestProzedurePEDESTRIAN = createNewTrafficLight(trafficLightModelPEDESTRIAN);


        /* Define place into GUI */
        nodeTestProzedureCAR.setLayoutX(0);
        nodeTestProzedureCAR.setLayoutY(0);
        nodeTestProzedureCARMirror.setLayoutX(50);
        nodeTestProzedureCARMirror.setLayoutY(0);
        nodeTestProzedurePEDESTRIAN.setLayoutX(120);
        nodeTestProzedurePEDESTRIAN.setLayoutY(0);


        paneLoader.getChildren().add(nodeTestProzedureCAR);
        paneLoader.getChildren().add(nodeTestProzedureCARMirror);
        paneLoader.getChildren().add(nodeTestProzedurePEDESTRIAN);
        primaryStage.show();

        /* Add the models from testing */
        testModel.setModelsForTesting(trafficLightModelCAR);
        testModel.setModelsForTesting(trafficLightModelPEDESTRIAN);
    }


    private Node createNewTrafficLight(TrafficLightModel model) throws java.io.IOException
    {
        Node nodeTestProzedure;

        TrafficLightController trafficLightController = new TrafficLightController(model);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));
        fxmlLoader.setController(trafficLightController);
        nodeTestProzedure = fxmlLoader.load();

        model.addObserver(trafficLightController);

        return nodeTestProzedure;
    }



//    @Test
    void testAdd()
    {
//        assertEquals(4, foo.add(2,5));
    }
}
