import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import static javafx.collections.FXCollections.observableArrayList;


/**
 * Class CrossroadController: Class for handling the PrimaryStage
 *
 *
 *
 * @version 1.0
 * @autor   Class NIN
 * @date   30.11.2018
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
    private final Map<Direction, Point2D> points = new HashMap<>();

    /**
     * CrossroadController(): Constructor
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    27.11.2018
     * @arg     Crossroad model: (Object form model class)
     */
    public CrossroadController(Crossroad model)
    {
        this.crossroadModel = model;
        double xPoint = 0;
        double yPoint = 0;
        int rotateRoute = 0;

        points.put(Direction.WEST, new Point2D(0,0));
        points.put(Direction.NORTH, new Point2D(550,-300));
        points.put(Direction.EAST, new Point2D(850,250));
        points.put(Direction.SOUTH, new Point2D(300,550));

        /* Loop to create all driveways */
        for (int i = 0; i < Direction.values().length; i++)
        {
            /* create driveWayRouteController */
            Direction direction = Direction.values()[i];
            DrivewayRouteController drivewayRouteController = new DrivewayRouteController(crossroadModel.getDrivewayRoute(direction), ref,points.get(direction), rotateRoute);
            crossroadModel.getDrivewayRoute(direction).addObserver(drivewayRouteController);
            drivewayRouteControllers.add(drivewayRouteController);

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
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<String> observableList;

        observableList = observableArrayList(crossroadModel.getAlgorithms());
        setchoiceOfAlgorithm.setItems(observableList);
        setchoiceOfAlgorithm.setValue("Algorithm A");

        observableList = observableArrayList("3","4");
        setnumberOfCrossing.setItems(observableList);
        setnumberOfCrossing.setValue("4");
    }


    @FXML
    public void mnuExitApplication(ActionEvent actionEvent)
    {
        System.exit(0);
    }


    /**
     * mnuOpenAboutWindow: Open a new Gui with project informations
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    11.12.2018
     * @arg     ActionEvent actionEvent: ActionEvent from FXML
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
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    11.12.2018
     * @arg     ActionEvent actionEvent: ActionEvent from FXML
     */
    @FXML
    public void startButtonConfig(ActionEvent actionEvent)
    {
        try {
            for (int i = 0; i < Direction.values().length ; i++)
            {

                crossroadModel.getDrivewayRoute(Direction.values()[i]).setPedestrianStripes(checkboxpedestrainStripes.isSelected());
                crossroadModel.getDrivewayRoute(Direction.values()[i]).setVelostripes(checkboxvelostripes.isSelected());
            }

            crossroadModel.setNumberOfDriveways(Integer.valueOf(setnumberOfCrossing.getValue().toString()));

            /* Disable by 3 counts of routes the visiblity from Direction.SOUTH */
            if(crossroadModel.getNumberOfDriveways() == 3)
            {
                crossroadModel.getDrivewayRoute(Direction.SOUTH).setVisibility(false);
            }
            else
            {
                crossroadModel.getDrivewayRoute(Direction.SOUTH).setVisibility(true);
            }

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
     *
     * Is automatic called when something into trafficLightModel is changed.
     *
     * @version 1.0
     * @autor   Class NIN
     * @date    02.10.2019
     */
    @Override
    public void update()
    {
        for (int i = 0; i < Direction.values().length; i++)
        {
            checkboxpedestrainStripes.setSelected(crossroadModel.getDrivewayRoute(Direction.values()[i]).getPedestrianStripes());
            checkboxvelostripes.setSelected(crossroadModel.getDrivewayRoute(Direction.values()[i]).getVelostripes());
            setnumberOfCrossing.setValue(crossroadModel.getNumberOfDriveways());
        }

    }

    /**
     * CrossroadController: get the DrivewayRouteController
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     *
    */
    public List<DrivewayRouteController> getDrivewayRouteControllers()
    {
        return drivewayRouteControllers;
    }
}





