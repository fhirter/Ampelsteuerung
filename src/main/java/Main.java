import animation.GameLoop;
import crossroad.Crossroad;
import crossroad.CrossroadController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Direction;
import vehicles.Vehicle;


/**
 * @version 1.0
 * @autor Schweizer Patrick, Grimm Raphael, Vogt Andreas, Reiter Daniel, Hirter Fabian
 * @date 04.11.2018
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ampelsteuerung");

        final Crossroad crossroad = new Crossroad();
        final CrossroadController crossroadController = new CrossroadController(crossroad);

        Vehicle vehicle1 = new Vehicle(Direction.EAST, Direction.WEST);
        Vehicle vehicle2 = new Vehicle(Direction.NORTH, Direction.WEST);
        Vehicle vehicle3 = new Vehicle(Direction.SOUTH, Direction.WEST);

        crossroad.addVehicle(vehicle1);
        crossroad.addVehicle(vehicle2);
        crossroad.addVehicle(vehicle3);

        final Scene scene = new Scene(crossroadController, 1100, 900);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);

        primaryStage.show();

        GameLoop gameLoop = new GameLoop(crossroad);
        gameLoop.start();
    }
}

