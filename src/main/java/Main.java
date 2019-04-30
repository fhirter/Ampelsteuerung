import animation.GameLoop;
import crossroad.Crossroad;
import crossroad.CrossroadController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @version 1.0
 * @autor Class NIN
 * @date 04.11.2018
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ampelsteuerung");

        final Crossroad crossroad = new Crossroad();
        final CrossroadController crossroadController = new CrossroadController(crossroad);

        final Scene scene = new Scene(crossroadController, 1100, 900);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);

        primaryStage.show();

        // Start Game Loop
        GameLoop gameLoop = new GameLoop(crossroad);
        gameLoop.start();
    }
}

