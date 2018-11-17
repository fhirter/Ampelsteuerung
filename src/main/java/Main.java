import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;


/**
 * Class Main: Mainmethode for the TEKO project "Ampelsteuerung".
 *
 * LONG DESCRIOTION
 *
 * @version 1.0
 * @autor   Class NIN
 * @date    04.11.2018
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Crossroad crossroad = new Crossroad();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("primaryStage.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Ampelsteuerung");

        PrimaryStageController controller = loader.getController();

        List<String> algorithm = new LinkedList<>();
        algorithm.add("Algorithm A");
        algorithm.add("Algorithm B");
        algorithm.add("Algorithm C");
        algorithm.add("Algorithm D");
        controller.setSetchoiceOfAlgorithm(algorithm);
        List<String> crossing = new LinkedList<>();
        crossing.add("3 Streets");
        crossing.add("4 Streets");
        crossing.add("5 Streets");
        controller.setSetnumberOfCrossing(crossing);

        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();


    }

    @Override
    public void stop(){

    }


    public static void main(String[] args)
    {
        launch(args);
        System.out.println("Start project.");

        //root.getChildern().add(circle);

    }

}


