package crossroad;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import traffic_lights.TrafficLightState;
import util.Direction;
import util.Position;
import vehicles.Vehicle;
import vehicles.VehicleController;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static javafx.collections.FXCollections.observableArrayList;


/**
 * Class crossroad.CrossroadController: Class for handling the PrimaryStage
 *
 * @version 1.0
 * @autor Class NIN
 * @date 30.11.2018
 */
public class CrossroadController extends BorderPane implements Initializable
{
    @FXML    private CheckBox checkboxvelostripes;
    @FXML    private CheckBox checkboxpedestrainStripes;

    @FXML    private RadioButton nordSetRed;
    @FXML    private RadioButton nordSetGreen;
    @FXML    private RadioButton westSetRed;
    @FXML    private RadioButton westSetGreen;
    @FXML    private RadioButton southSetRed;
    @FXML    private RadioButton southSetGreen;
    @FXML    private RadioButton eastSetRed;
    @FXML    private RadioButton eastSetGreen;
    @FXML    private Slider sldCountOfMovedElements;
    @FXML    private Label lblCountOfMovedElements;

    private List<RoadController> roadControllers = new LinkedList<>();

    private Crossroad crossroad;

    private int countOfMovedElements = 1;

    /**
     * crossroad.CrossroadController(): Constructor
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    27.11.2018
     * @arg     crossroad.Crossroad model: (Object form model class)
     */
    public CrossroadController(Crossroad model) {
        this.crossroad = model;

        Map<Direction, Position> offsets = new HashMap<>();

        int  roadWidth = crossroad.getRoadWidth();
        int roadLength = crossroad.getRoadLength();
        int centerSize = roadWidth;

        offsets.put(Direction.WEST, new Position(-roadLength-centerSize/2, -roadWidth/2,0));     // todo: get effective size
        offsets.put(Direction.NORTH, new Position(roadWidth/2, -roadLength-centerSize/2,90));
        offsets.put(Direction.EAST, new Position(roadLength+centerSize/2, roadWidth/2,180));
        offsets.put(Direction.SOUTH, new Position(-roadWidth/2, roadLength+centerSize/2,270));

        /* Loop to create all driveways */
        Direction[] directions = Direction.values();
        Direction direction;
        for (int i = 0; i < directions.length; i++) {
            /* create driveWayRouteController */
            direction = directions[i];
            RoadController roadController = new RoadController(crossroad.getRoad(direction), crossroad.getReferencePoint(), offsets.get(direction));
            crossroad.getRoad(direction).addObserver(roadController);
            roadControllers.add(roadController);
            getChildren().add(roadController);
        }

        FXMLLoader primaryStageLoader = new FXMLLoader(getClass().getResource("/primaryStage.fxml"));
        primaryStageLoader.setRoot(this);
        primaryStageLoader.setController(this);

        try {
            primaryStageLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load Center
        AnchorPane center = new AnchorPane();
        getChildren().add(center);

        FXMLLoader centerLoader = new FXMLLoader(getClass().getResource("/drivewayCenter.fxml"));
        centerLoader.setController(this);
        centerLoader.setRoot(center);
        try {
            centerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Point2D offset = new Point2D(-250/2,-250/2);  // todo: get effective height
        center.setLayoutX(crossroad.getReferencePoint().getX() + offset.getX());
        center.setLayoutY(crossroad.getReferencePoint().getY() + offset.getY());

        List<Vehicle> vehicles = crossroad.getVehicles();
        for(Vehicle vehicle:vehicles) {
            VehicleController vehicleController = new VehicleController(vehicle);
            vehicle.addObserver(vehicleController);
            getChildren().add(vehicleController);
        }
    }


    /**
     * initialize(URL location, ResourceBundle resources): Initialize during startUp all settings from the PrimaryStateController
     *
     * Is automatic called when fxmlLoader.load() ist called.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    10.12.2018
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> observableList;

        observableList = observableArrayList("3", "4");
    }


    @FXML
    public void mnuExitApplication(ActionEvent actionEvent) {
        System.exit(0);
    }


    /**
     * mnuOpenAboutWindow: Open a new Gui with project informations
     *
     * @version 1.0
     * @autor Schweizer Patrick
     * @date 11.12.2018
     * @arg ActionEvent actionEvent: ActionEvent from FXML
     */
    @FXML
    public void mnuOpenAboutWindow(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Projektinformationen");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("aboutStage.fxml"));
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sliderCountOfMovedElements()
    {
        this.countOfMovedElements = (int)sldCountOfMovedElements.getValue();
        lblCountOfMovedElements.setText("Count = " + String.valueOf(countOfMovedElements));
    }


    /**
     * startButtonConfig: Starts a new draw from a crossroad with the desired settings
     *
     * @version 1.0
     * @autor Schweizer Patrick
     * @date 11.12.2018
     * @arg ActionEvent actionEvent: ActionEvent from FXML
     */
    @FXML
    public void startButtonConfig(ActionEvent actionEvent) {
        try {
            for (int i = 0; i < crossroad.getRoadCount(); i++) {
                crossroad.setPedestrianStripes(checkboxpedestrainStripes.isSelected());
                crossroad.setVelostripes(checkboxvelostripes.isSelected());
            }

            if (crossroad.getRoadCount() == 3) {
                crossroad.getRoad(Direction.SOUTH).setVisibility(false);
            } else {
                crossroad.getRoad(Direction.SOUTH).setVisibility(true);
            }

        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * changeTrafficLightState: Change the state from the trafficLights
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    02.03.2019
     * @arg     ActionEvent actionEvent: ActionEvent from FXML
     */
    @FXML
    public void changeTrafficLightState(ActionEvent actionEvent)
    {
        if(nordSetRed.isSelected()){
            crossroad.setTrafficLightState(Direction.NORTH, TrafficLightState.RED);
        }else{
            crossroad.setTrafficLightState(Direction.NORTH, TrafficLightState.GREEN);
        }

        if(westSetRed.isSelected()){
            crossroad.setTrafficLightState(Direction.WEST, TrafficLightState.RED);
        }else{
            crossroad.setTrafficLightState(Direction.WEST, TrafficLightState.GREEN);
        }

        if(southSetRed.isSelected()){
            crossroad.setTrafficLightState(Direction.SOUTH, TrafficLightState.RED);
        }else{
            crossroad.setTrafficLightState(Direction.SOUTH, TrafficLightState.GREEN);
        }

        if(eastSetRed.isSelected()){
            crossroad.setTrafficLightState(Direction.EAST, TrafficLightState.RED);
        }else{
            crossroad.setTrafficLightState(Direction.EAST, TrafficLightState.GREEN);
        }
    }

    /**
     * crossroad.CrossroadController: get the DrivewayRouteController
     *
     * @version 1.0
     * @autor NIN Class
     * @date 02.08.2018
     */
    public List<RoadController> getRoadControllers() {
        return roadControllers;
    }
}





