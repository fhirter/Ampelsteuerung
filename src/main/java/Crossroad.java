import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;


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
    private final int refPointCrossroadX = 200;
    private final int refPointCrossroadY = 330;
    private int getCountOfBasedChildren = 0;
    private double scaleFactorCrossroad = 1;

    private PrimaryStageModel primaryStageModel = new PrimaryStageModel(this);
    private BorderPane borderPaneLoaderPrimaryStage;


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

        PrimaryStageController primaryStageController = new PrimaryStageController(primaryStageModel);
        FXMLLoader fxmlLoaderPrimaryStage = new FXMLLoader(getClass().getResource("primaryStage.fxml"));
        fxmlLoaderPrimaryStage.setController(primaryStageController);
        borderPaneLoaderPrimaryStage = fxmlLoaderPrimaryStage.load();
        primaryStage.setScene(new Scene(borderPaneLoaderPrimaryStage, 1100, 900));
        /* Needed for redraw or remove from the Children from drivewayRoute or drivewayCenter */
        getCountOfBasedChildren = borderPaneLoaderPrimaryStage.getChildren().size();

        primaryStage.show();
    }


    /**
     * configureAndDrawCrossroad: draws the crossroad with the selected settings and scaleFactor
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    29.12.2018
     * @arg     HashMap<String, HashMap>: (HashMap where the selected settings from the crossroad are stored.)
     */
    public void configureAndDrawCrossroad(HashMap<String, HashMap> settingsForCrossroad) throws Exception
    {
        double lengthCrossroad;
        double widthCrossroad;
        HashMap<String, String> allgorithmusAndTypeFromCrossroad = settingsForCrossroad.get("allgorithmusAndType");
        Node nodeDrawCenter;
        Pane nodeDrawStreet0Degree, nodeDrawStreet90Degree, nodeDrawStreet180Degree, nodeDrawStreet270Degree;

        borderPaneLoaderPrimaryStage.getChildren().remove(getCountOfBasedChildren, borderPaneLoaderPrimaryStage.getChildren().size());
        System.out.println("Set configuration is Pressed.");

        nodeDrawStreet0Degree = createDrivewayRoute(settingsForCrossroad);
        nodeDrawStreet0Degree.setLayoutX(refPointCrossroadX);
        nodeDrawStreet0Degree.setLayoutY(refPointCrossroadY);
        lengthCrossroad = nodeDrawStreet0Degree.prefWidth(0) * scaleFactorCrossroad;
        widthCrossroad = nodeDrawStreet0Degree.prefHeight(0) * scaleFactorCrossroad;
        nodeDrawStreet0Degree.setRotate(0);
        nodeDrawStreet0Degree.setScaleX(scaleFactorCrossroad);
        nodeDrawStreet0Degree.setScaleY(scaleFactorCrossroad);

        /* Street with 90 Degree */
        nodeDrawStreet90Degree = createDrivewayRoute(settingsForCrossroad);
        nodeDrawStreet90Degree.setLayoutX(refPointCrossroadX + widthCrossroad + lengthCrossroad);
        nodeDrawStreet90Degree.setLayoutY(refPointCrossroadY - lengthCrossroad);
        nodeDrawStreet90Degree.setRotate(90);
        nodeDrawStreet90Degree.setScaleX(scaleFactorCrossroad);
        nodeDrawStreet90Degree.setScaleY(scaleFactorCrossroad);

        /* Street with 180 Degree */
        nodeDrawStreet180Degree = createDrivewayRoute(settingsForCrossroad);
        nodeDrawStreet180Degree.setLayoutX(refPointCrossroadX + lengthCrossroad + lengthCrossroad + widthCrossroad);
        nodeDrawStreet180Degree.setLayoutY(refPointCrossroadY + widthCrossroad);
        nodeDrawStreet180Degree.setRotate(180);
        nodeDrawStreet180Degree.setScaleX(scaleFactorCrossroad);
        nodeDrawStreet180Degree.setScaleY(scaleFactorCrossroad);

        /* Street with 270 Degree */
        nodeDrawStreet270Degree = createDrivewayRoute(settingsForCrossroad);
        nodeDrawStreet270Degree.setLayoutX(refPointCrossroadX + lengthCrossroad);
        nodeDrawStreet270Degree.setLayoutY(refPointCrossroadY + lengthCrossroad + widthCrossroad);
        nodeDrawStreet270Degree.setRotate(270);
        nodeDrawStreet270Degree.setScaleX(scaleFactorCrossroad);
        nodeDrawStreet270Degree.setScaleY(scaleFactorCrossroad);

        /* Center */
        nodeDrawCenter = createDrivewayCenter(settingsForCrossroad);
        nodeDrawCenter.setLayoutX(refPointCrossroadX + lengthCrossroad);
        nodeDrawCenter.setLayoutY(refPointCrossroadY);
        nodeDrawCenter.setScaleX(scaleFactorCrossroad);
        nodeDrawCenter.setScaleY(scaleFactorCrossroad);

        /* Draw the crossroad */
        borderPaneLoaderPrimaryStage.getChildren().add(nodeDrawCenter);
        borderPaneLoaderPrimaryStage.getChildren().add(nodeDrawStreet0Degree);
        borderPaneLoaderPrimaryStage.getChildren().add(nodeDrawStreet90Degree);
        borderPaneLoaderPrimaryStage.getChildren().add(nodeDrawStreet180Degree);
        if(allgorithmusAndTypeFromCrossroad.get("typeOfCrossroad").equals("4 Streets"))
        {
            borderPaneLoaderPrimaryStage.getChildren().add(nodeDrawStreet270Degree);
        }
    }


    /**
     * createDrivewayCenter: Create a new node from the drivewayRoute
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    28.12.2018
     * @arg     HashMap<String, HashMap>: (HashMap where the selected settings from the crossroad are stored.)
     * @return  Pane: (Pane from the nodes route and trafficLight)
     */
     private Pane createDrivewayRoute(HashMap<String, HashMap> settingsForCrossroad) throws IOException
     {
        HashMap<String, Boolean> settingsFromCheckBoxes = settingsForCrossroad.get("checkboxes");
        Pane paneDrivewayRoute = new Pane();
        Node nodeDrivewayRoute, nodeTrafficLightCAR, nodeTrafficLightPEDESTRIANLeft, nodeTrafficLightPEDESTRIANRight;

        /* Create Route */
        DrivewayRouteModel drivewayRouteModel = new DrivewayRouteModel(settingsForCrossroad);
        DrivewayRouteController drivewayRouteController = new DrivewayRouteController(drivewayRouteModel);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("drivewayRoute.fxml"));
        fxmlLoader.setController(drivewayRouteController);
        nodeDrivewayRoute = fxmlLoader.load();

        /* Create TrafficLights */
        nodeTrafficLightCAR = drivewayRouteModel.createTrafficLight(TrafficLightType.CAR);
        nodeTrafficLightCAR.setRotate(90);
        nodeTrafficLightCAR.setTranslateX(100);
        nodeTrafficLightCAR.setTranslateY(140);

        nodeTrafficLightPEDESTRIANLeft = drivewayRouteModel.createTrafficLight(TrafficLightType.PEDESTRIAN);
        nodeTrafficLightPEDESTRIANLeft.setRotate(90);
        nodeTrafficLightPEDESTRIANLeft.setTranslateX(230);
        nodeTrafficLightPEDESTRIANLeft.setTranslateY(140);

        nodeTrafficLightPEDESTRIANRight = drivewayRouteModel.createTrafficLight(TrafficLightType.PEDESTRIAN);
        nodeTrafficLightPEDESTRIANRight.setRotate(90);
        nodeTrafficLightPEDESTRIANRight.setTranslateX(230);
        nodeTrafficLightPEDESTRIANRight.setTranslateY(-50);

        /* Add for drawing route and trafficLights into Pane */
        paneDrivewayRoute.getChildren().add(nodeDrivewayRoute);
        paneDrivewayRoute.getChildren().add(nodeTrafficLightCAR);
        if(settingsFromCheckBoxes.get("pedestrainStripesCheckbox") == true)
        {
            paneDrivewayRoute.getChildren().add(nodeTrafficLightPEDESTRIANLeft);
            paneDrivewayRoute.getChildren().add(nodeTrafficLightPEDESTRIANRight);
        }
        return paneDrivewayRoute;
     }


    /**
     * createDrivewayCenter: Create a new node from the drivewayCenter
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    28.12.2018
     * @arg     HashMap<String, HashMap>: (HashMap where the selected settings from the crossroad are stored.)
     * @return  Node: (Node from the drivewayCenter)
     */
    private Node createDrivewayCenter(HashMap<String, HashMap> settingsForCrossroad) throws IOException {

        Node nodeDrivewayCenter;

        DrivewayCenterModel drivewayCenterModel = new DrivewayCenterModel(settingsForCrossroad);
        DrivewayCenterController drivewayCenterController = new DrivewayCenterController(drivewayCenterModel);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("drivewayCenter.fxml"));
        fxmlLoader.setController(drivewayCenterController);
        nodeDrivewayCenter = fxmlLoader.load();

        return nodeDrivewayCenter;
    }
}


