import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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

    private BorderPane borderPaneLoaderPrimaryStage;
    private HashMap<String, TrafficLightModel> crossroadControlMap = new HashMap<>();
    private Algorithmus algorithmus = new Algorithmus(crossroadControlMap);

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
        PrimaryStageModel primaryStageModel = new PrimaryStageModel(this);
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
        Node nodeDrawCenter, nodeGreenPlanet, nodeVehicle;
        Pane nodeStreet0Degree, nodeStreet90Degree, nodeStreet180Degree, nodeStreet270Degree;

        crossroadControlMap.clear();
        borderPaneLoaderPrimaryStage.getChildren().remove(getCountOfBasedChildren, borderPaneLoaderPrimaryStage.getChildren().size());
        System.out.println("Set configuration is Pressed.");

        /* Street with 0 Degree */
        nodeStreet0Degree = createDrivewayRoute("West", settingsForCrossroad);
        lengthCrossroad = nodeStreet0Degree.prefWidth(0) * scaleFactorCrossroad;
        widthCrossroad = nodeStreet0Degree.prefHeight(0) * scaleFactorCrossroad;
        setCrossroadLayout(nodeStreet0Degree, 0,0,0);

        /* Street with 90 Degree */
        nodeStreet90Degree = createDrivewayRoute("North",settingsForCrossroad);
        setCrossroadLayout(nodeStreet90Degree, (widthCrossroad + lengthCrossroad), (-1*lengthCrossroad),90);

        /* Street with 180 Degree */
        nodeStreet180Degree = createDrivewayRoute("East",settingsForCrossroad);
        setCrossroadLayout(nodeStreet180Degree, (lengthCrossroad + lengthCrossroad + widthCrossroad), widthCrossroad,180);

        /* Street with 270 Degree */
        nodeStreet270Degree = createDrivewayRoute("South",settingsForCrossroad);
        setCrossroadLayout(nodeStreet270Degree, lengthCrossroad, (lengthCrossroad + widthCrossroad),270);

        /* Center */
        nodeDrawCenter = createDrivewayCenter(settingsForCrossroad);
        setCrossroadLayout((Pane)nodeDrawCenter, lengthCrossroad, 0,0);

        /* GreenPlanet */
        nodeGreenPlanet = crateGreenPlanet();
        setCrossroadLayout((Pane)nodeGreenPlanet, 0, (-1*lengthCrossroad),0);

        /* Vehicles */
        nodeVehicle = createVehicle();
        nodeVehicle.setLayoutX(380);
        nodeVehicle.setLayoutY(440);
        nodeVehicle.setRotate(90);








        /* Draw crossroad */
        borderPaneLoaderPrimaryStage.getChildren().add(nodeGreenPlanet);
        borderPaneLoaderPrimaryStage.getChildren().add(nodeDrawCenter);
        borderPaneLoaderPrimaryStage.getChildren().add(nodeStreet0Degree);
        borderPaneLoaderPrimaryStage.getChildren().add(nodeStreet90Degree);
        borderPaneLoaderPrimaryStage.getChildren().add(nodeStreet180Degree);
        borderPaneLoaderPrimaryStage.getChildren().add(nodeVehicle);
        if(settingsForCrossroad.get("allgorithmusAndType").get("typeOfCrossroad").equals("4 Streets"))
        {
            borderPaneLoaderPrimaryStage.getChildren().add(nodeStreet270Degree);
        }

        //todo: Testfunktion fuer Allgorithmus der Ampeln! Schaltspiel muss gemaess Allgorithmus noch bearbeitet werden
        algorithmus.testfunktionAmpelspiel(settingsForCrossroad);
    }


    /**
     * createDrivewayRoute: Create a new Pane from the drivewayRoute with trafficLights and store
     *                      for control all trafficLights into "Allgorithmus" the RouteID into crossroadControlMap
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    01.01.2019
     * @arg     String: (Name or ID for storing the route with trafficLights into crossroadControlMap.)
     * @arg     HashMap<String, HashMap>: (HashMap where the selected settings from the crossroad are stored.)
     * @return  Pane: (Pane from the nodes route and trafficLight)
     */
     private Pane createDrivewayRoute(String RouteID, HashMap<String, HashMap> settingsForCrossroad) throws IOException
     {
        Pane paneDrivewayRoute = new Pane();
        Node nodeDrivewayRoute, nodeTrafficLightCAR, nodeTrafficLightPEDESTRIANLeft, nodeTrafficLightPEDESTRIANRight;

        /* Create Route */
        DrivewayRouteModel drivewayRouteModel = new DrivewayRouteModel(settingsForCrossroad);
        DrivewayRouteController drivewayRouteController = new DrivewayRouteController(drivewayRouteModel);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("drivewayRoute.fxml"));
        fxmlLoader.setController(drivewayRouteController);
        nodeDrivewayRoute = fxmlLoader.load();

        /* Create TrafficLights */
        TrafficLightModel trafficLightModelCAR = new TrafficLightModel(TrafficLightType.CAR);
        nodeTrafficLightCAR = drivewayRouteModel.createTrafficLight(trafficLightModelCAR);
        nodeTrafficLightCAR.setRotate(90);
        nodeTrafficLightCAR.setTranslateX(100);
        nodeTrafficLightCAR.setTranslateY(140);
        /* TrafficLight PEDESTRIAN Left */
        TrafficLightModel trafficLightModelPEDESTRIAN = new TrafficLightModel(TrafficLightType.PEDESTRIAN);
        nodeTrafficLightPEDESTRIANLeft = drivewayRouteModel.createTrafficLight(trafficLightModelPEDESTRIAN);
        nodeTrafficLightPEDESTRIANLeft.setRotate(90);
        nodeTrafficLightPEDESTRIANLeft.setTranslateX(230);
        nodeTrafficLightPEDESTRIANLeft.setTranslateY(140);
        /* TrafficLight PEDESTRIAN Right ----- MIRRORING FROM TRAFFICLIGHT LEFT! ----- */
        nodeTrafficLightPEDESTRIANRight = drivewayRouteModel.createTrafficLight(trafficLightModelPEDESTRIAN);
        nodeTrafficLightPEDESTRIANRight.setRotate(90);
        nodeTrafficLightPEDESTRIANRight.setTranslateX(230);
        nodeTrafficLightPEDESTRIANRight.setTranslateY(-50);

        /* Add for drawing route and trafficLights into Pane */
        paneDrivewayRoute.getChildren().add(nodeDrivewayRoute);
        paneDrivewayRoute.getChildren().add(nodeTrafficLightCAR);
        if(settingsForCrossroad.get("checkboxes").get("pedestrainStripesCheckbox").equals(true))
        {
            paneDrivewayRoute.getChildren().add(nodeTrafficLightPEDESTRIANLeft);
            paneDrivewayRoute.getChildren().add(nodeTrafficLightPEDESTRIANRight);
        }

        /* Add RouteIndex into crossroadControlMap for control all trafficLights */
        crossroadControlMap.put(RouteID + "_CAR", trafficLightModelCAR);
        crossroadControlMap.put(RouteID + "_PEDESTRIAN", trafficLightModelPEDESTRIAN);

        return paneDrivewayRoute;
     }


    /**
     * setCrossroadLayout: Sets the Layout, Rotation and Scalefactor for the nodes from crossroad
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    02.01.2018
     * @arg     Pane: (Pane or node from crossroad)
     * @arg     double layoutXOffset, layoutYOffset: (Layoutoffset for place (X/Y) the node into the GUI)
     * @arg     int degree: (Number of Rotation)

     */
    public void setCrossroadLayout(Pane nodeStreetxxDegree, double layoutXOffset, double layoutYOffset, int degree)
    {
        nodeStreetxxDegree.setLayoutX(refPointCrossroadX + layoutXOffset);
        nodeStreetxxDegree.setLayoutY(refPointCrossroadY + layoutYOffset);
        nodeStreetxxDegree.setRotate(degree);
        nodeStreetxxDegree.setScaleX(scaleFactorCrossroad);
        nodeStreetxxDegree.setScaleY(scaleFactorCrossroad);
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


    /**
     * crateGreenPlanet: Create a new node from the greenPlanet
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    02.01.2018
     * @return  Node: (Node from the greenPlanet)
     */
    public Node crateGreenPlanet() throws java.io.IOException
    {
        Node nodeGreenPlanet;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("greenPlanet.fxml"));
        GreenPlanetController greenPlanetController = new GreenPlanetController();
        fxmlLoader.setController(greenPlanetController);
        nodeGreenPlanet = fxmlLoader.load();

        return nodeGreenPlanet;
    }

    public Node createVehicle() throws java.io.IOException
    {
        Node nodeVehicle;
        VehicleModel vehicleModel = new VehicleModel();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vehicle.fxml"));
        VehicleController vehicleController = new VehicleController(vehicleModel);
        fxmlLoader.setController(vehicleController);
        nodeVehicle = fxmlLoader.load();

        return  nodeVehicle;
    }

}