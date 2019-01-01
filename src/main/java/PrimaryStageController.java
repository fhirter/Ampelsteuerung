import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.*;
import static javafx.collections.FXCollections.observableArrayList;


/**
 * Class PrimaryStageController: Class for handling the PrimaryStage
 *
 *
 *
 * @version 1.0
 * @autor   Class NIN
 * @date   30.11.2018
 */
public class PrimaryStageController implements Initializable
{
    @FXML    private CheckBox checkboxvelostripes;
    @FXML    private CheckBox checkboxbusway;
    @FXML    private CheckBox checkboxtramway;
    @FXML    private CheckBox pedestrainStripesCheckbox;
    @FXML    private ChoiceBox setchoiceOfAlgorithm;
    @FXML    private ChoiceBox setnumberOfCrossing;

    private PrimaryStageModel model;
    private HashMap<String, HashMap> settingsForCrossroad = new HashMap<>();
    private HashMap<String, Boolean> settingsFromCheckBoxes = new HashMap<>();
    private HashMap<String, String> allgorithmusAndTypeFromCrossroad = new HashMap<>();

    /**
     * PrimaryStageController(): Constructor
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    27.11.2018
     * @arg     PrimaryStageModel model: (Object form model class)
     */
    public PrimaryStageController(PrimaryStageModel model)
    {
        this.model = model;
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
        HashMap<String, String[]> controllerSettings = new HashMap<>();
        ObservableList<String> observableList = null;

        controllerSettings = model.getControllerSettings();

        observableList = observableArrayList(controllerSettings.get("allgorithmusType"));
        setchoiceOfAlgorithm.setItems(observableList);
        setchoiceOfAlgorithm.setValue("Algorithm A");

        observableList = observableArrayList(controllerSettings.get("typeOfCrossroad"));
        setnumberOfCrossing.setItems(observableList);
        setnumberOfCrossing.setValue("4 Streets");

        checkboxvelostripes.setSelected(true);
        checkboxbusway.setSelected(false);
        checkboxtramway.setSelected(false);
        pedestrainStripesCheckbox.setSelected(true);
    }


    /**
     * mnuExitApplication: Called when exit button from MainMenue was pressed
     *
     * Close the program Ampelsteuerung
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    11.12.2018
     * @arg     ActionEvent actionEvent: ActionEvent from FXML
     */
    @FXML
    public void mnuExitApplication(ActionEvent actionEvent)
    {
        model.closeProgram();
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
        model.openAboutWindow();
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
            allgorithmusAndTypeFromCrossroad.put("allgorithmusTypes", setchoiceOfAlgorithm.getValue().toString());
            allgorithmusAndTypeFromCrossroad.put("typeOfCrossroad", setnumberOfCrossing.getValue().toString());
            settingsFromCheckBoxes.put("checkboxvelostripes", checkboxvelostripes.isSelected());
            settingsFromCheckBoxes.put("checkboxbusway", checkboxbusway.isSelected());
            settingsFromCheckBoxes.put("checkboxtramway", checkboxtramway.isSelected());
            settingsFromCheckBoxes.put("pedestrainStripesCheckbox", pedestrainStripesCheckbox.isSelected());

            settingsForCrossroad.put("allgorithmusAndType", allgorithmusAndTypeFromCrossroad);
            settingsForCrossroad.put("checkboxes", settingsFromCheckBoxes);

            model.startConfigurationIsPressed(settingsForCrossroad);

        }catch (NullPointerException e)
        {
            System.err.println("Error: Allgorithmus oder Kreuzungstyp wurde nicht angewaehlt.");
        }
    }
}
