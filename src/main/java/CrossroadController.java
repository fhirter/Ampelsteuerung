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
import javafx.stage.Stage;

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
public class CrossroadController extends Node implements Initializable, Observer
{
    @FXML    private CheckBox checkboxvelostripes;
    @FXML    private CheckBox checkboxbusway;
    @FXML    private CheckBox checkboxtramway;
    @FXML    private CheckBox checkboxpedestrainStripes;
    @FXML    private ChoiceBox setchoiceOfAlgorithm;
    @FXML    private ChoiceBox setnumberOfCrossing;

    private Crossroad crossroadModel;
    private Property numberOfDriveways;
    private Property<Boolean> velostripes;
    private Property<Boolean> pedestrianStripes;


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

        checkboxvelostripes.setSelected(true);
        checkboxbusway.setSelected(false);
        checkboxtramway.setSelected(false);
        checkboxpedestrainStripes.setSelected(true);

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
           crossroadModel.setNumberOfDriveways((Integer)setnumberOfCrossing.getValue());


        }catch (NullPointerException e)
        {
            System.err.println("Error: Allgorithmus oder Kreuzungstyp wurde nicht angewaehlt.");
        }
    }

    @Override
    public void update() {
        pedestrianStripes.setValue(crossroadModel.getPedestrianStripes());
        velostripes.setValue(crossroadModel.getVelostripes());
        setnumberOfCrossing.setValue(crossroadModel.getNumberOfDriveways());

    }
}





