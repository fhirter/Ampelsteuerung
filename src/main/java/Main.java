import animation.GameLoop;
import crossroad.Crossroad;
import crossroad.CrossroadPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Direction;
import vehicles.Car;


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
        final CrossroadPresenter crossroadPresenter = new CrossroadPresenter(crossroad);

        Car car1 = new Car(Direction.EAST, Direction.WEST);
        Car car2 = new Car(Direction.NORTH, Direction.WEST);
        Car car3 = new Car(Direction.SOUTH, Direction.WEST);

        crossroad.addVehicle(car1);
        crossroad.addVehicle(car2);
        crossroad.addVehicle(car3);

        final Scene scene = new Scene(crossroadPresenter, 1100, 900);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);

        primaryStage.show();

        GameLoop gameLoop = new GameLoop(crossroad);
        gameLoop.start();
    }
}

