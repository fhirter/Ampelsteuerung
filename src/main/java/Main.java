import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
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


        FXMLLoader loader = new FXMLLoader(getClass().getResource("primaryStage.fxml"));
        BorderPane root = loader.load();

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


        Node node = getTrafficLight();
        Node drivewayNode = getDriveway();


        primaryStage.setScene(new Scene(root, 500, 800));
//        primaryStage.setScene(new Scene(root, 1920, 1080));
        root.getChildren().add(node);
        primaryStage.show();
        root.getChildren().add(drivewayNode);


    }

    private Node getDriveway() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("driveway.fxml"));

        Driveway driveway = new Driveway();
        driveway.addObserver(fxmlLoader.getController());
        return (Node) fxmlLoader.load();
    }




    private Node getTrafficLight() throws java.io.IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));

        TrafficLightModel trafficLight = new TrafficLightModel();
        trafficLight.addObserver(fxmlLoader.getController());
        return (Node) fxmlLoader.load();
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


