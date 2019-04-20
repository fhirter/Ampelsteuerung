import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;

/**
 * Class Main: Mainmethode for the TEKO project "Ampelsteuerung".
 *
 * @version 1.0
 * @autor Class NIN
 * @date 04.11.2018
 */
public class Main extends Application {
    private static final Point2D ref = new Point2D(650, 450);
    private CrossroadController crossroadController;

    public static Point2D getRef() {
        return ref;
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
        primaryStage.setTitle("Ampelsteuerung");

        Crossroad crossroad = new Crossroad(ref);
        crossroadController = new CrossroadController(crossroad);

        /* Center */
        CenterPane centerPane = new CenterPane(crossroad, ref);
        crossroadController.getChildren().add(centerPane);

        List<RoadController> drivewayControllerList = crossroadController.getDrivewayRouteControllers();
        for (int i = 0; i < drivewayControllerList.size(); i++) {
            crossroadController.getChildren().add(drivewayControllerList.get(i));
        }

        // turning area
//        int size = 180;
//        Rectangle r = new Rectangle(size,size);
//        r.setX(ref.getX()-size/2);
//        r.setY(ref.getY()-size/2);


//        crossroadController.getChildren().add(r);

        final Scene scene = new Scene(crossroadController, 1100, 900);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);

        primaryStage.show();

        /* Generate and start Vehicles */
        Vehicles vehicles = new Vehicles(crossroad, crossroadController, 1);
        GameLoop gameLoop = new GameLoop(vehicles);
        gameLoop.start();
    }
}

