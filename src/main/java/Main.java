import animation.GameLoop;
import crossroad.Crossroad;
import crossroad.CrossroadController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vehicles.Vehicle;


/**
 * @version 1.0
 * @autor Class NIN
 * @date 04.11.2018
 */
public class Main extends Application {


    private Crossroad crossroad;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ampelsteuerung");

        crossroad = new Crossroad();
        final CrossroadController crossroadController = new CrossroadController(crossroad);
        generateVehicles(1);

        final Scene scene = new Scene(crossroadController, 1100, 900);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);

        primaryStage.show();

        GameLoop gameLoop = new GameLoop(crossroad);
        gameLoop.start();
    }

    public void generateVehicles(int count) {
        for (int i = 0; i < count; i++) {
            Vehicle vehicle = new Vehicle(crossroad);
            crossroad.addVehicle(vehicle);
        }
    }
}

