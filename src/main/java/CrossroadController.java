import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static javafx.collections.FXCollections.observableArrayList;


/**
 * Class CrossroadController: Class for handling the PrimaryStage
 *
 * @version 1.0
 * @autor Class NIN
 * @date 30.11.2018
 */
public class CrossroadController extends BorderPane implements Initializable, Observer
{
    @FXML    private CheckBox checkboxvelostripes;
    @FXML    private CheckBox checkboxbusway;
    @FXML    private CheckBox checkboxtramway;
    @FXML    private CheckBox checkboxpedestrainStripes;
    @FXML    private ChoiceBox setchoiceOfAlgorithm;
    @FXML    private ChoiceBox setnumberOfCrossing;
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

    private List<DrivewayRouteController> drivewayRouteControllers = new LinkedList<>();

    private Crossroad crossroadModel;
    private static Point2D ref = Main.getRef();
    private int countOfMovedElements;

    /**
     * CrossroadController(): Constructor
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    27.11.2018
     * @arg     Crossroad model: (Object form model class)
     */
    public CrossroadController(Crossroad model) {
        this.crossroadModel = model;
        double xPoint = 0;
        double yPoint = 0;
        int rotateRoute = 0;


        List<Point2D> offsets = new LinkedList<>();
        int centerHeight = 255;
        int roadWidth = 300;
        int roadHeight = 250;
        //     offsets.add(new Point2D(-centerHeight/2- roadWidth,-roadHeight /2));     // todo: get effective size
        //    //    offsets.add(new Point2D(centerHeight/2, -centerHeight /2-roadWidth));
        //      //  offsets.add(new Point2D(centerHeight/2+roadWidth,roadHeight /2));
        //        //offsets.add(new Point2D(-centerHeight/2,centerHeight/2+roadWidth));

        offsets.add(new Point2D(-roadWidth-centerHeight/2, -roadHeight/2));     // todo: get effective size
        offsets.add(new Point2D(-roadHeight-centerHeight/2, 0));
        offsets.add(new Point2D(0, 0));
        offsets.add(new Point2D(0, 0));

        /* Loop to create all driveways */
        for (int i = 0; i < 4; i++) {

            /* create controller */
            DrivewayRouteController drivewayRouteController = new DrivewayRouteController(crossroadModel.getDrivewayRoutes().get(i), ref, offsets.get(i), rotateRoute);
            crossroadModel.getDrivewayRoutes().get(i).addObserver(drivewayRouteController);
            drivewayRouteControllers.add(drivewayRouteController);

            drivewayRouteController.getChildren().add(drivewayRouteController.getTrafficLightControllerCar().get(0));
            drivewayRouteController.getChildren().add(drivewayRouteController.getTrafficLightControllerPedestrian().get(0));
            drivewayRouteController.getChildren().add(drivewayRouteController.getTrafficLightControllerPedestrian().get(1));

            /* add controller to observer from the createt model */
            rotateRoute += 90;
        }

        this.crossroadModel = model;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("primaryStage.fxml"));
        loader.setRoot(this);       // dieses BorderPane als root element des GUI setzen. Dazu muss in primaryStage.fxml das root element folgendes sein: <fx:root type="BorderPane">
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
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

        observableList = observableArrayList(crossroadModel.getAlgorithms());
        setchoiceOfAlgorithm.setItems(observableList);
        setchoiceOfAlgorithm.setValue("Algorithm A");

        observableList = observableArrayList("3", "4");
        setnumberOfCrossing.setItems(observableList);
        setnumberOfCrossing.setValue("4");

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
    public void mnuOpenAboutWindow(ActionEvent actionEvent) throws Exception {
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
            for (int i = 0; i < crossroadModel.getDrivewayRoutes().size(); i++) {
                crossroadModel.getDrivewayRoutes().get(i).setPedestrianStripes(checkboxpedestrainStripes.isSelected());
                crossroadModel.getDrivewayRoutes().get(i).setVelostripes(checkboxvelostripes.isSelected());
            }

            crossroadModel.setNumberOfDriveways(Integer.valueOf(setnumberOfCrossing.getValue().toString()));

            if (crossroadModel.getNumberOfDriveways() == 3) {
                crossroadModel.getDrivewayRoutes().get(3).setVisibility(false);
            } else {
                crossroadModel.getDrivewayRoutes().get(3).setVisibility(true);
            }

        } catch (NullPointerException e) {
            crossroadModel.startMovedElements(this, countOfMovedElements);

        }catch (NullPointerException e)
        {
            System.err.println("Error: Allgorithmus oder Kreuzungstyp wurde nicht angewaehlt.");
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
            crossroadModel.setStateFromTrafficLight(Direction.NORTH, TrafficLightState.RED);
        }else{
            crossroadModel.setStateFromTrafficLight(Direction.NORTH, TrafficLightState.GREEN);
        }

        if(westSetRed.isSelected()){
            crossroadModel.setStateFromTrafficLight(Direction.WEST, TrafficLightState.RED);
        }else{
            crossroadModel.setStateFromTrafficLight(Direction.WEST, TrafficLightState.GREEN);
        }

        if(southSetRed.isSelected()){
            crossroadModel.setStateFromTrafficLight(Direction.SOUTH, TrafficLightState.RED);
        }else{
            crossroadModel.setStateFromTrafficLight(Direction.SOUTH, TrafficLightState.GREEN);
        }

        if(eastSetRed.isSelected()){
            crossroadModel.setStateFromTrafficLight(Direction.EAST, TrafficLightState.RED);
        }else{
            crossroadModel.setStateFromTrafficLight(Direction.EAST, TrafficLightState.GREEN);
        }
    }


    /**
     * update(): Obstacle where is registred into Crossroad
     * <p>
     * Is automatic called when something into trafficLightModel is changed.
     *
     * @version 1.0
     * @autor Class NIN
     * @date 02.10.2019
     */
    @Override
    public void update() {
        for (int i = 0; i < crossroadModel.getDrivewayRoutes().size(); i++) {
            checkboxpedestrainStripes.setSelected(crossroadModel.getDrivewayRoutes().get(i).getPedestrianStripes());
            checkboxvelostripes.setSelected(crossroadModel.getDrivewayRoutes().get(i).getVelostripes());
            setnumberOfCrossing.setValue(crossroadModel.getNumberOfDriveways());
        }

    }

    /**
     * CrossroadController: get the DrivewayRouteController
     *
     * @version 1.0
     * @autor NIN Class
     * @date 02.08.2018
     */
    public List<DrivewayRouteController> getDrivewayRouteControllers() {
        return drivewayRouteControllers;
    }
}





