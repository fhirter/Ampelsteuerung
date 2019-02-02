import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

import static java.lang.Thread.sleep;


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
    private static final Point2D ref = new Point2D(200,330);
    private CrossroadController crossroadController;

    public static Point2D getRef() {
        return ref;
    }


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

        Crossroad crossroad = new Crossroad();
        crossroadController = new CrossroadController(crossroad);

        /* GreenPlanet */
        GreenPlanetController greenPlanetController = new GreenPlanetController(crossroad, ref,new Point2D(0,-300));
        crossroadController.getChildren().add(greenPlanetController);

        /* Center */
        Point2D offset = new Point2D(300,0);      //offset point
        CenterPane centerPane = new CenterPane(crossroad, ref, offset);
        crossroadController.getChildren().add(centerPane);        //add
        crossroad.setCenterPaneModel(centerPane);

        List<DrivewayRouteController> drivewayControllerList = crossroad.getDrivewayRouteControllers();
        for (int i = 0; i < drivewayControllerList.size() ; i++) {
            crossroadController.getChildren().add(drivewayControllerList.get(i));
        }

        primaryStage.setScene(new Scene(crossroadController, 1100, 900));
        primaryStage.show();

        testFunction(crossroad);
    }


    public void testFunction(Crossroad crossroad) throws Exception
    {
        // todo: Fz koennen gezeichent werden und fahren an Ihr entsprechendes Ziel.
        // todo: Als GameLoop wurde ein Timer verwendet
        // todo: Radfahrer muss noc genauer platziert werden
        VehicleModel vehicleModel = new VehicleModel(MovedElements.Bus, StartAndEndpoint.south, StartAndEndpoint.north);
        VehicleController vehicleController = new VehicleController(vehicleModel);
        vehicleModel.addObserver(vehicleController);
        crossroadController.getChildren().add(vehicleController);


        VehicleModel vehicleModel2 = new VehicleModel(MovedElements.Bus, StartAndEndpoint.south, StartAndEndpoint.east);
        VehicleController vehicleController2 = new VehicleController(vehicleModel2);
        vehicleModel2.addObserver(vehicleController2);
        crossroadController.getChildren().add(vehicleController2);

        VehicleModel vehicleModel3 = new VehicleModel(MovedElements.Bus, StartAndEndpoint.south, StartAndEndpoint.west);
        VehicleController vehicleController3 = new VehicleController(vehicleModel3);
        vehicleModel3.addObserver(vehicleController3);
        crossroadController.getChildren().add(vehicleController3);




        VehicleModel vehicleModel4 = new VehicleModel(MovedElements.Bus, StartAndEndpoint.north, StartAndEndpoint.south);
        VehicleController vehicleController4 = new VehicleController(vehicleModel4);
        vehicleModel4.addObserver(vehicleController4);
        crossroadController.getChildren().add(vehicleController4);


        VehicleModel vehicleModel5 = new VehicleModel(MovedElements.Bus, StartAndEndpoint.north, StartAndEndpoint.east);
        VehicleController vehicleController5 = new VehicleController(vehicleModel5);
        vehicleModel5.addObserver(vehicleController5);
        crossroadController.getChildren().add(vehicleController5);

        VehicleModel vehicleModel6 = new VehicleModel(MovedElements.Bus, StartAndEndpoint.north, StartAndEndpoint.west);
        VehicleController vehicleController6 = new VehicleController(vehicleModel6);
        vehicleModel6.addObserver(vehicleController6);
        crossroadController.getChildren().add(vehicleController6);


        vehicleModel.startGameLoop();
        sleep(1000);
        vehicleModel2.startGameLoop();
        sleep(1000);
        vehicleModel3.startGameLoop();
        sleep(1000);
        vehicleModel4.startGameLoop();
        sleep(1000);
        vehicleModel5.startGameLoop();
        sleep(1000);
        vehicleModel6.startGameLoop();

        // todo: Zum testen ob die Amplen funktionieren. Muss noch geloescht werden
        crossroad.getDrivewayRoutes().get(0).getTrafficLightModelCar().get(0).setRed();
        crossroad.getDrivewayRoutes().get(1).getTrafficLightModelCar().get(0).setGreen();
        crossroad.getDrivewayRoutes().get(2).getTrafficLightModelCar().get(0).setYellowFlash();
        crossroad.getDrivewayRoutes().get(3).getTrafficLightModelCar().get(0).setSIMULATION();
        crossroad.getDrivewayRoutes().get(0).getTrafficLightModelPedestrian().get(0).setRed();
        crossroad.getDrivewayRoutes().get(1).getTrafficLightModelPedestrian().get(0).setYellowFlash();
        crossroad.getDrivewayRoutes().get(2).getTrafficLightModelPedestrian().get(0).setGreen();
        crossroad.getDrivewayRoutes().get(3).getTrafficLightModelPedestrian().get(0).setSIMULATION();
    }
}

