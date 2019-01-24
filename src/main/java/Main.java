import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Class Main: Mainmethode for the TEKO project "Ampelsteuerung".
 *
 *
 * @version 1.0
 * @autor   Class NIN
 * @date    04.11.2018
 */
public class Main extends Application
{
    private final int refPointCrossroadX = 200;
    private final int refPointCrossroadY = 330;
    private final Point2D ref = new Point2D(200,330);
    // reference point for whole crossroad
    private int getCountOfBasedChildren = 0;
    private double scaleFactorCrossroad = 1;




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

        Crossroad crossroad = new Crossroad(false,false,3);
        CrossroadController crossroadController = new CrossroadController(crossroad);
        crossroad.addObserver(crossroadController);

        /* GreenPlanet */
        //todo: Point2D (y) noch anpassen. Nicht als fixer Wert implementieren.
        GreenPlanetController greenPlanetController = new GreenPlanetController(crossroad, ref,new Point2D(0,-300));
        crossroadController.getChildren().add(greenPlanetController);

        /* Center */
        Point2D offset = new Point2D(300,0);      //offset point
        CenterPane centerPane = new CenterPane(crossroad, ref, offset);
        crossroadController.getChildren().add(centerPane);        //add
        List<DrivewayRoute> drivewayList = crossroad.getDrivewayRoutes();

        /*Driveway North*/
        DrivewayRouteController drivewayRouteControllerNorth = new DrivewayRouteController(drivewayList.get(0), ref, new Point2D(550,-300), 90);
        crossroadController.getChildren().add(drivewayRouteControllerNorth);
        drivewayList.get(0).addObserver(drivewayRouteControllerNorth);

       /*TrafficLightController trafficLightControllerNorthCar = new TrafficLightController(drivewayList.get(0).getTrafficLightCar(), ref, new Point2D(550,-300), 0);
        drivewayRouteControllerNorth.getChildren().add(trafficLightControllerNorthCar);
        drivewayList.get(0).getTrafficLightCar().addObserver(trafficLightControllerNorthCar);

        TrafficLightController trafficLightControllerNorthPedestrian = new TrafficLightController(drivewayList.get(0).getTrafficLightPedestrian(), ref, new Point2D(450,-250), 0);
        drivewayRouteControllerNorth.getChildren().add(trafficLightControllerNorthPedestrian);
        drivewayList.get(0).getTrafficLightCar().addObserver(trafficLightControllerNorthPedestrian);

        /*Driveway East*/
        DrivewayRouteController drivewayRouteControllerEast = new DrivewayRouteController(drivewayList.get(1), ref, new Point2D(0,0),0);
        crossroadController.getChildren().add(drivewayRouteControllerEast);
        drivewayList.get(1).addObserver(drivewayRouteControllerEast);

        /*TrafficLightController trafficLightControllerEastCar = new TrafficLightController(drivewayList.get(1).getTrafficLightCar(), ref, new Point2D(50,-50), 0);
        crossroadController.getChildren().add(trafficLightControllerEastCar);
        drivewayList.get(1).getTrafficLightCar().addObserver(trafficLightControllerEastCar);

        TrafficLightController trafficLightControllerEastPedestrian = new TrafficLightController(drivewayList.get(1).getTrafficLightPedestrian(), ref, new Point2D(60,-60), 0);
        crossroadController.getChildren().add(trafficLightControllerEastPedestrian);
        drivewayList.get(1).getTrafficLightCar().addObserver(trafficLightControllerEastPedestrian);


        /*Driveway West*/
        DrivewayRouteController drivewayRouteControllerWest = new DrivewayRouteController(drivewayList.get(2), ref, new Point2D(850,250),180);
        crossroadController.getChildren().add(drivewayRouteControllerWest);
        drivewayList.get(2).addObserver(drivewayRouteControllerWest);

        /*TrafficLightController trafficLightControllerWestCar = new TrafficLightController(drivewayList.get(2).getTrafficLightCar(), ref, new Point2D(650,200), 0);
        crossroadController.getChildren().add(trafficLightControllerWestCar);
        drivewayList.get(2).getTrafficLightCar().addObserver(trafficLightControllerWestCar);

        TrafficLightController trafficLightControllerWestPedestrian = new TrafficLightController(drivewayList.get(2).getTrafficLightPedestrian(), ref, new Point2D(550,150), 0);
        crossroadController.getChildren().add(trafficLightControllerWestPedestrian);
        drivewayList.get(2).getTrafficLightCar().addObserver(trafficLightControllerWestPedestrian);


        /*Driveway South*/
        if (crossroad.getNumberOfDriveways() == 4) {
            DrivewayRouteController drivewayRouteControllerSouth = new DrivewayRouteController(drivewayList.get(3), ref, new Point2D(300, 550), 270);
            crossroadController.getChildren().add(drivewayRouteControllerSouth);
            drivewayList.get(3).addObserver(drivewayRouteControllerSouth);

          /* TrafficLightController trafficLightControllerSouthCar = new TrafficLightController(drivewayList.get(3).getTrafficLightCar(), ref, new Point2D(200,450), 0);
            crossroadController.getChildren().add(trafficLightControllerSouthCar);
            drivewayList.get(3).getTrafficLightCar().addObserver(trafficLightControllerSouthCar);

            TrafficLightController trafficLightControllerSouthPedestrian = new TrafficLightController(drivewayList.get(3).getTrafficLightPedestrian(), ref, new Point2D(150,400), 0);
            crossroadController.getChildren().add(trafficLightControllerSouthPedestrian);
            drivewayList.get(3).getTrafficLightCar().addObserver(trafficLightControllerSouthPedestrian);
*/
        }


       // VehicleController vehicleController = new VehicleController(new VehicleModel(), ref, new Point2D(0,0), 0);
        //crossroadController.getChildren().add(vehicleController);




        //todo: Zum testen ob die Strasse funktioniert.
        /* Driveway Route East
        DrivewayRoute drivewayRouteEast = new DrivewayRoute(false,false);
        DrivewayRouteController drivewayRouteControllerEast = new DrivewayRouteController(drivewayRouteEast, ref, new Point2D(200,200));
        crossroadController.getChildren().add(drivewayRouteControllerEast);
        */





        //todo: Nur zum testen ob die Ampel funktioniert.
        //todo: Muss in DrivewayRoute / DrivewayRouteController verschoben werden
        //todo: WICHTIG!! Nachfragen wesshalb die Ampeln nicht mehr aktualisiert werden.
        //todo: --> Timer wird gestartet, jedoch blinkt die Ampel nicht
        /* TrafficLight */
       /* TrafficLight trafficLight = new TrafficLight(TrafficLightType.CAR);
        TrafficLightController trafficLightController = new TrafficLightController(trafficLight, ref, new Point2D(0,0));
        crossroadController.getChildren().add(trafficLightController);
        trafficLight.setYellowFlash();
*/


        primaryStage.setScene(new Scene(crossroadController, 1100, 900));

        primaryStage.show();
    }


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
//    public Node createTrafficLight(TrafficLight trafficLight) throws java.io.IOException
//    {
//        Node nodeTrafficLight;

//        TrafficLightController trafficLightController = new TrafficLightController(trafficLight);
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));
//        fxmlLoader.setController(trafficLightController);
//        nodeTrafficLight = fxmlLoader.load();

//        trafficLight.addObserver(trafficLightController);

//        return nodeTrafficLight;
//    }


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
     * crateGreenPlanet: Create a new node from the greenPlanet
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    02.01.2018
     * @return  Node: (Node from the greenPlanet)
     */
//    public Node crateGreenPlanet() throws java.io.IOException
//    {
//        Node nodeGreenPlanet = null;

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("greenPlanet.fxml"));
//        GreenPlanetController greenPlanetController = new GreenPlanetController();
//        fxmlLoader.setController(greenPlanetController);
//        nodeGreenPlanet = fxmlLoader.load();

//        return nodeGreenPlanet;
//    }

    /**
     * startConfigurationIsPressed: Store from selected main the configuration.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date   11.12.2018
     */
    public void startConfigurationIsPressed(HashMap<String, HashMap> settingsForCrossroad)
    {
        try {

//            configureAndDrawCrossroad(settingsForCrossroad);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

