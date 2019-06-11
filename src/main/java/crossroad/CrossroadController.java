package crossroad;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import traffic_lights.TrafficLightState;
import util.Direction;
import util.Observer;
import util.Position;
import vehicles.Vehicle;
import vehicles.VehicleController;

import java.io.IOException;
import java.util.*;

import static javafx.collections.FXCollections.observableArrayList;


/**
 * Class crossroad.CrossroadController: Class for handling the PrimaryStage
 *
 * @autor Schweizer Patrick, Grimm Raphael, Vogt Andreas, Reiter Daniel, Hirter Fabian
 * @since  30.11.2018
 */
public class CrossroadController extends BorderPane implements Observer {
    @FXML private CheckBox checkboxvelostripes;
    @FXML private CheckBox checkboxpedestrainStripes;

    @FXML private RadioButton nordSetRed;
    @FXML private RadioButton nordSetGreen;
    @FXML private RadioButton westSetRed;
    @FXML private RadioButton westSetGreen;
    @FXML private RadioButton southSetRed;
    @FXML private RadioButton southSetGreen;
    @FXML private RadioButton eastSetRed;
    @FXML private RadioButton eastSetGreen;

    private List<RoadController> roadControllers = new LinkedList<>();

    private final Crossroad crossroad;
    private final Map<Direction, Position> directionPositionMap = new HashMap<>();

    public CrossroadController(Crossroad crossroad) throws IOException {
        this.crossroad = crossroad;
        crossroad.addObserver(this);

        initializeDirectionPositionMap();
        initializeRoadControllers();
        loadPrimaryStage();
        loadCenter();

    }

    private void initializeDirectionPositionMap() {
        int roadWidth = crossroad.getRoadWidth();
        int roadLength = crossroad.getRoadLength();
        int centerSize = roadWidth;

        directionPositionMap.put(Direction.WEST, new Position(-roadLength - centerSize / 2, -roadWidth / 2, 0));     // todo: get effective size
        directionPositionMap.put(Direction.NORTH, new Position(roadWidth / 2, -roadLength - centerSize / 2, 90));
        directionPositionMap.put(Direction.EAST, new Position(roadLength + centerSize / 2, roadWidth / 2, 180));
        directionPositionMap.put(Direction.SOUTH, new Position(-roadWidth / 2, roadLength + centerSize / 2, 270));
    }

    private void loadPrimaryStage() {
        FXMLLoader primaryStageLoader = new FXMLLoader(getClass().getResource("/primaryStage.fxml"));
        primaryStageLoader.setRoot(this);
        primaryStageLoader.setController(this);

        try {
            primaryStageLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCenter() {
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

        Point2D offset = new Point2D(-250 / 2, -250 / 2);  // todo: get effective height
        center.setLayoutX(this.crossroad.getReferencePoint().getX() + offset.getX());
        center.setLayoutY(this.crossroad.getReferencePoint().getY() + offset.getY());
    }

    private void initializeRoadControllers() throws IOException {
        Direction[] directions = Direction.values();
        Direction direction;
        for (int i = 0; i < directions.length; i++) {
            direction = directions[i];
            RoadController roadController = new RoadController(this.crossroad.getRoad(direction), this.crossroad.getReferencePoint(), directionPositionMap.get(direction));

            roadControllers.add(roadController);
            getChildren().add(roadController);      // add to GUI
        }
    }

    @FXML
    public void mnuExitApplication(ActionEvent actionEvent) {
        System.exit(0);
    }

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

    /**
     * changeTrafficLightState: Change the state of the trafficLights
     *
     * @param actionEvent ActionEvent from FXML
     */
    @FXML
    public void changeTrafficLightState(ActionEvent actionEvent) {
        if (nordSetRed.isSelected()) {
            crossroad.setTrafficLightState(Direction.NORTH, TrafficLightState.RED);
        } else {
            crossroad.setTrafficLightState(Direction.NORTH, TrafficLightState.GREEN);
        }

        if (westSetRed.isSelected()) {
            crossroad.setTrafficLightState(Direction.WEST, TrafficLightState.RED);
        } else {
            crossroad.setTrafficLightState(Direction.WEST, TrafficLightState.GREEN);
        }

        if (southSetRed.isSelected()) {
            crossroad.setTrafficLightState(Direction.SOUTH, TrafficLightState.RED);
        } else {
            crossroad.setTrafficLightState(Direction.SOUTH, TrafficLightState.GREEN);
        }

        if (eastSetRed.isSelected()) {
            crossroad.setTrafficLightState(Direction.EAST, TrafficLightState.RED);
        } else {
            crossroad.setTrafficLightState(Direction.EAST, TrafficLightState.GREEN);
        }
    }

    @Override
    public void update() {
        List<Vehicle> vehicles = crossroad.getVehicles();
        ObservableList<Node> children = getChildren();
        children.removeAll();

        for (Vehicle vehicle : vehicles) {
            children.add(new VehicleController(vehicle));
        }
    }
}





