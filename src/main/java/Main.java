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

//        crossroad.addVehicle(new Car(Direction.EAST, Direction.NORTH));
 //       crossroad.addVehicle(new Car(Direction.WEST, Direction.SOUTH));
//        crossroad.addVehicle(new Car(Direction.WEST, Direction.NORTH));
//        crossroad.addVehicle(new Car(Direction.WEST, Direction.SOUTH));


        Car car1 = new Car(Direction.EAST, Direction.WEST);
        Car car2 = new Car(Direction.WEST, Direction.EAST);
        Car car3 = new Car(Direction.SOUTH, Direction.NORTH);
        Car car4 = new Car(Direction.NORTH, Direction.SOUTH);
//
        crossroad.addVehicle(car1);
        crossroad.addVehicle(car2);
        crossroad.addVehicle(car3);
        crossroad.addVehicle(car4);

        final Scene scene = new Scene(crossroadPresenter, 1100, 900);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);

        primaryStage.show();

        GameLoop gameLoop = new GameLoop(crossroad);
        gameLoop.start();
    }
}

