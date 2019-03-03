import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    private static final Point2D ref = new Point2D(200,330);
    private CrossroadController crossroadController;

    public static Point2D getRef()
    {
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

        List<DrivewayRouteController> drivewayControllerList = crossroadController.getDrivewayRouteControllers();
        for (int i = 0; i < drivewayControllerList.size() ; i++)
        {
            crossroadController.getChildren().add(drivewayControllerList.get(i));
        }

        /* Create a new Scene and show priamaryStage */
        primaryStage.setScene(new Scene(crossroadController, 1100, 900));
        primaryStage.show();

        /* Set all trafficLight to default red */
        crossroad.getDrivewayRoutes().get(0).getTrafficLightModelCar().get(0).setRed();
        crossroad.getDrivewayRoutes().get(1).getTrafficLightModelCar().get(0).setRed();
        crossroad.getDrivewayRoutes().get(2).getTrafficLightModelCar().get(0).setRed();
        crossroad.getDrivewayRoutes().get(3).getTrafficLightModelCar().get(0).setRed();
        crossroad.getDrivewayRoutes().get(0).getTrafficLightModelPedestrian().get(0).setRed();
        crossroad.getDrivewayRoutes().get(1).getTrafficLightModelPedestrian().get(0).setRed();
        crossroad.getDrivewayRoutes().get(2).getTrafficLightModelPedestrian().get(0).setRed();
        crossroad.getDrivewayRoutes().get(3).getTrafficLightModelPedestrian().get(0).setRed();
    }
}

