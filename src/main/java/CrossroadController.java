import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    private Crossroad crossroadModel;

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

        observableList = observableArrayList(crossroadModel.getNumberOfDriveways().toString());
        setnumberOfCrossing.setItems(observableList);
        setnumberOfCrossing.setValue("4");

        checkboxvelostripes.setSelected(crossroadModel.getVelostripes());       // init Werte aus dem Model holen
        checkboxbusway.setSelected(false);
        checkboxtramway.setSelected(false);
        checkboxpedestrainStripes.setSelected(crossroadModel.getPedestrianStripes());

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
    public void mnuOpenAboutWindow(ActionEvent actionEvent) throws Exception
    {
        try {
            Stage stage = new Stage();
            stage.setTitle("Projektinformationen");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("aboutStage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
           crossroadModel.setPedestrianStripes(checkboxpedestrainStripes.isSelected());
           crossroadModel.setVelostripes(checkboxvelostripes.isSelected());
           crossroadModel.setNumberOfDriveways(Integer.parseInt((String)setnumberOfCrossing.getValue()));


        }catch (NullPointerException e)
        {
            System.err.println("Error: Allgorithmus oder Kreuzungstyp wurde nicht angewaehlt.");
        }
    }


    @Override
    public void update() {
        checkboxpedestrainStripes.setSelected(crossroadModel.getPedestrianStripes());
        checkboxvelostripes.setSelected(crossroadModel.getVelostripes());
        setnumberOfCrossing.setValue(crossroadModel.getNumberOfDriveways());

    }
}





