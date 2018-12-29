import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * Class Crossroad: Mainmethode for the TEKO project "Ampelsteuerung".
 *
 *
 * @version 1.0
 * @autor   Class NIN
 * @date    04.11.2018
 */
public class Crossroad extends Application
{
    final int refPointCrossroadX = 200;
    final int refPointCrossroadY = 330;
    double scaleFactorCrossroad = 1.0;

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
        double lengthCrossroad1;
        double widthCrossroad1;
        Node nodeDrawStreet0Degree, nodeDrawStreet90Degree, nodeDrawStreet180Degree, nodeDrawStreet270Degree, nodeDrawCenter;

        primaryStage.setTitle("Ampelsteuerung");

        PrimaryStageModel primaryStageModel = new PrimaryStageModel();
        PrimaryStageController primaryStageController = new PrimaryStageController(primaryStageModel);
        FXMLLoader fxmlLoaderPrimaryStage = new FXMLLoader(getClass().getResource("primaryStage.fxml"));
        fxmlLoaderPrimaryStage.setController(primaryStageController);
        BorderPane borderPaneLoaderPrimaryStage = fxmlLoaderPrimaryStage.load();
        primaryStage.setScene(new Scene(borderPaneLoaderPrimaryStage, 1100, 900));

        /* Place for drawing div. nodes from the crossroad */
        /* Street with 0 Degree */
        nodeDrawStreet0Degree = createDrivewayRoute();
        nodeDrawStreet0Degree.setLayoutX(refPointCrossroadX);
        nodeDrawStreet0Degree.setLayoutY(refPointCrossroadY);
        nodeDrawStreet0Degree.setRotate(0);
        lengthCrossroad1 = nodeDrawStreet0Degree.prefWidth(0);
        widthCrossroad1 = nodeDrawStreet0Degree.prefHeight(0);

        /* Street with 90 Degree */
        nodeDrawStreet90Degree = createDrivewayRoute();
        nodeDrawStreet90Degree.setLayoutX(refPointCrossroadX + lengthCrossroad1 + lengthCrossroad1 + widthCrossroad1);
        nodeDrawStreet90Degree.setLayoutY(refPointCrossroadY + widthCrossroad1);
        nodeDrawStreet90Degree.setRotate(180);

        /* Street with 180 Degree */
        nodeDrawStreet180Degree = createDrivewayRoute();
        nodeDrawStreet180Degree.setLayoutX(refPointCrossroadX + widthCrossroad1 + lengthCrossroad1);
        nodeDrawStreet180Degree.setLayoutY(refPointCrossroadY - lengthCrossroad1);
        nodeDrawStreet180Degree.setRotate(90);

        /* Street with 270 Degree */
        nodeDrawStreet270Degree = createDrivewayRoute();
        nodeDrawStreet270Degree.setLayoutX(refPointCrossroadX + lengthCrossroad1);
        nodeDrawStreet270Degree.setLayoutY(refPointCrossroadY + lengthCrossroad1 + widthCrossroad1);
        nodeDrawStreet270Degree.setRotate(270);

        /* Center */
        nodeDrawCenter = createDrivewayCenter();
        nodeDrawCenter.setLayoutX(refPointCrossroadX + lengthCrossroad1);
        nodeDrawCenter.setLayoutY(refPointCrossroadY);

        /* Draw the crossroad */
        borderPaneLoaderPrimaryStage.getChildren().add(nodeDrawCenter);
        borderPaneLoaderPrimaryStage.getChildren().add(nodeDrawStreet0Degree);
        borderPaneLoaderPrimaryStage.getChildren().add(nodeDrawStreet90Degree);
        borderPaneLoaderPrimaryStage.getChildren().add(nodeDrawStreet180Degree);
        borderPaneLoaderPrimaryStage.getChildren().add(nodeDrawStreet270Degree);
        primaryStage.show();
    }


    /**
     * createDrivewayCenter: Set the settings into the controller
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    28.12.2018
     * @return  Node: (Node from the drivewayDenter)
     */
     private Node createDrivewayRoute() throws IOException {

        Node nodeDrivewayRoute;

        DrivewayRouteController drivewayRouteController = new DrivewayRouteController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("drivewayRoute.fxml"));
        nodeDrivewayRoute = fxmlLoader.load();
        fxmlLoader.setController(drivewayRouteController);

        return nodeDrivewayRoute;
    }


    /**
     * createDrivewayCenter: Set the settings into the controller
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    28.12.2018
     * @return  Node: (Node from the drivewayDenter)
     */
    private Node createDrivewayCenter() throws IOException {

        Node nodeDrivewayCenter;

        DrivewayCenterController drivewayCenterController = new DrivewayCenterController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("drivewayCenter.fxml"));
        nodeDrivewayCenter = fxmlLoader.load();
        fxmlLoader.setController(drivewayCenterController);

        return nodeDrivewayCenter;
    }
}


