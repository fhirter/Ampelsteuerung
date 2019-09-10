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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import traffic_lights.TrafficLightState;
import util.Area;
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
 * View Order 100: Street
 * View Order 90: Street Markings
 * View Order 80: Boardwalk
 * View Order 70: Areas
 * View Order 60: Vehicles
 *
 * @autor Schweizer Patrick, Grimm Raphael, Vogt Andreas, Reiter Daniel, Hirter Fabian
 * @since  30.11.2018
 */
public class CrossroadController extends BorderPane implements Observer {
    private final Point2D referencePoint = new Point2D(650, 450);

    @FXML private CheckBox checkboxvelostripes;
    @FXML private CheckBox checkboxpedestrainStripes;

    @FXML private Rectangle turningArea;

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

    private AnchorPane center;

    public CrossroadController(Crossroad crossroad) throws IOException {
        this.crossroad = crossroad;
        crossroad.addObserver(this);

        initializeRoadControllers();

        loadPrimaryStage();
        loadCenter();

        setTurningArea();

        setViewOrders();
    }

    private void setTurningArea() {
        Area turningArea = crossroad.getTurningArea();

        final int height = turningArea.getHeight();
        final int width = turningArea.getWidth();

        this.turningArea.setLayoutX(referencePoint.getX()+turningArea.getCenter().getX()-width/2);
        this.turningArea.setLayoutY(referencePoint.getY()+turningArea.getCenter().getY()-height/2);

        this.turningArea.setHeight(height);
        this.turningArea.setWidth(width);
    }

    private void setViewOrders() {
        turningArea.setViewOrder(100);      // todo: get rid of view order magic number

        for (int i = 0; i < roadControllers.size(); i++) {
            roadControllers.get(i).setViewOrder(70);
        }
        center.setViewOrder(100);
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
        center = new AnchorPane();
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
        center.setLayoutX(referencePoint.getX() + offset.getX());
        center.setLayoutY(referencePoint.getY() + offset.getY());
    }

    private void initializeRoadControllers() throws IOException {
        Direction[] directions = Direction.values();
        Direction direction;
        for (int i = 0; i < directions.length; i++) {
            direction = directions[i];
            Road road = crossroad.getRoad(direction);

            RoadController roadController = new RoadController(road, referencePoint);

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
        // called when new vehicle is added
        List<Vehicle> vehicles = crossroad.getVehicles();
        ObservableList<Node> children = getChildren();

        // in case of perfomance issues, this could help:
        // just add the last item in the vehicles list.
        // maybe add a counter
        // Vehicle vehicle0 = vehicles.get(vehicles.size()-1);

        for(Node node : children) {
            if(node instanceof VehicleController) {
                VehicleController vehicleController = (VehicleController) node;
                vehicles.remove(vehicleController.getVehicle());        // remove controllers from vehicles which are already in the list
            }
        }

        for (Vehicle vehicle : vehicles) {
            children.add(new VehicleController(vehicle, referencePoint));
        }
    }
}





