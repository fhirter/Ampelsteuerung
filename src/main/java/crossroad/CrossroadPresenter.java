package crossroad;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import vehicles.Vehicle;
import vehicles.VehiclePresenter;

import java.io.IOException;
import java.util.*;

/**
 * Class crossroad.CrossroadPresenter: Class for handling the PrimaryStage
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
public class CrossroadPresenter extends BorderPane implements Observer {
    private final Point2D referencePoint = new Point2D(650, 450);

    @FXML private CheckBox checkboxvelostripes;
    @FXML private CheckBox checkboxpedestrainStripes;

    @FXML private Rectangle turningArea;

    @FXML private RadioButton north_red;
    @FXML private RadioButton north_green;
    @FXML private RadioButton west_red;
    @FXML private RadioButton west_green;
    @FXML private RadioButton south_red;
    @FXML private RadioButton south_green;
    @FXML private RadioButton east_red;
    @FXML private RadioButton east_green;

    private List<RoadPresenter> roadPresenters = new LinkedList<>();

    private final Crossroad crossroad;

    private AnchorPane center;

    public CrossroadPresenter(Crossroad crossroad) throws IOException {
        this.crossroad = crossroad;
        crossroad.addObserver(this);

        initializeRoadPresenters();

        loadPrimaryStage();
        loadCenter();

        setTurningArea();

        setViewOrders();

        initEventListeners();
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

        for (int i = 0; i < roadPresenters.size(); i++) {
            roadPresenters.get(i).setViewOrder(70);
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

    private void initializeRoadPresenters() throws IOException {
        Map<Direction,Road> roads = crossroad.getRoads();

        for(Road road : roads.values()) {
            RoadPresenter roadPresenter = new RoadPresenter(road, referencePoint);
            roadPresenters.add(roadPresenter);
            getChildren().add(roadPresenter);      // add to GUI
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

    private void initEventListeners() {
        Map<String, Direction> idDirectionMap = new HashMap<>();
        Map<String, TrafficLightState> idStateMap = new HashMap<>();

        idDirectionMap.put("north", Direction.NORTH);
        idDirectionMap.put("west", Direction.WEST);
        idDirectionMap.put("south", Direction.SOUTH);
        idDirectionMap.put("east", Direction.EAST);

        idStateMap.put("red", TrafficLightState.RED);
        idStateMap.put("green", TrafficLightState.GREEN);

        EventHandler<ActionEvent> handler = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RadioButton source = (RadioButton) actionEvent.getSource();

                String[] id = source.getId().split("_");

                changeTrafficLightState(idDirectionMap.get(id[0]), idStateMap.get(id[1]));
            }
        };

        // todo: add handler to traffic light
        north_red.setOnAction(handler);
        north_green.setOnAction(handler);
        west_red.setOnAction(handler);
        west_green.setOnAction(handler);
        south_red.setOnAction(handler);
        south_green.setOnAction(handler);
        east_red.setOnAction(handler);
        east_green.setOnAction(handler);
    }

    /**
     * changeTrafficLightState: Change the state of the trafficLights
     *
     */

    public void changeTrafficLightState(Direction direction, TrafficLightState state) {
        crossroad.setTrafficLightState(direction, state);
    }

    @Override
    public void update() {
        // called when new vehicle is added
        List<Vehicle> vehicles = crossroad.getVehicles();
        ObservableList<Node> children = getChildren();

        // in case of perfomance issues, this could help:
        // just add the last item in the cars list.
        // maybe add a counter
        // Car vehicle0 = cars.get(cars.size()-1);

        for(Node node : children) {
            if(node instanceof VehiclePresenter) {
                VehiclePresenter vehiclePresenter = (VehiclePresenter) node;
                vehicles.remove(vehiclePresenter.getVehicle());        // remove controllers from cars which are already in the list
            }
        }

        for (Vehicle vehicle : vehicles) {
            final VehiclePresenter presenter = new VehiclePresenter(vehicle, referencePoint);
            vehicle.addObserver(presenter);
            children.add(presenter);
        }
    }
}





