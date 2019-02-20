import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
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
    private static final Point2D ref = new Point2D(650,450);
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

        Crossroad crossroad = new Crossroad(ref);
        crossroadController = new CrossroadController(crossroad);

        /* GreenPlanet */
        GreenPlanetController greenPlanetController = new GreenPlanetController(crossroad, ref,new Point2D(0,-300));
        crossroadController.getChildren().add(greenPlanetController);

        /* Center */
        Point2D offset = new Point2D(0,0);      //offset point
        CenterPane centerPane = new CenterPane(crossroad, ref, offset);
        crossroadController.getChildren().add(centerPane);        //add

        List<DrivewayRouteController> drivewayControllerList = crossroadController.getDrivewayRouteControllers();
        for (int i = 0; i < drivewayControllerList.size() ; i++)
        {
            crossroadController.getChildren().add(drivewayControllerList.get(i));
        }

        // turning area
        int size = 180;
        Rectangle r = new Rectangle(size,size);
        r.setX(ref.getX()-size/2);
        r.setY(ref.getY()-size/2);


        crossroadController.getChildren().add(r);

        primaryStage.setScene(new Scene(crossroadController, 1100, 700));


        primaryStage.show();

        /* Generate and start Vehicles */
        Vehicles vehicles = new Vehicles(crossroad, crossroadController, 5);
        GameLoop gameLoop = new GameLoop(vehicles);
        gameLoop.start();

        //todo: Zum testen ob die Amplen funktionieren. Muss noch geloescht werden
        crossroad.getDrivewayRoutes().get(0).getTrafficLightModelCar().get(0).setSIMULATION();
        crossroad.getDrivewayRoutes().get(1).getTrafficLightModelCar().get(0).setGreen();
        crossroad.getDrivewayRoutes().get(2).getTrafficLightModelCar().get(0).setYellowFlash();
        crossroad.getDrivewayRoutes().get(3).getTrafficLightModelCar().get(0).setSIMULATION();
        crossroad.getDrivewayRoutes().get(0).getTrafficLightModelPedestrian().get(0).setRed();
        crossroad.getDrivewayRoutes().get(1).getTrafficLightModelPedestrian().get(0).setYellowFlash();
        crossroad.getDrivewayRoutes().get(2).getTrafficLightModelPedestrian().get(0).setGreen();
        crossroad.getDrivewayRoutes().get(3).getTrafficLightModelPedestrian().get(0).setSIMULATION();
    }
}

