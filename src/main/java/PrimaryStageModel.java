import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PrimaryStageModel extends Observable
{
    final private String[] allgorithmusTypes = {"Algorithm A",
                                                "Algorithm B",
                                                "Algorithm C",
                                                "Algorithm D"};

    final private String[] typeOfCrossroad = {"3 Streets",
                                              "4 Streets"};

    private HashMap<String, HashMap> settingsForCrossroad = new HashMap<>();
    private HashMap<String, Boolean> settingsFromCheckBoxes = new HashMap<>();
    private HashMap<String, String> allgorithmusAndTypeFromCrossroad = new HashMap<>();

    /**
     * setControllerSettings: Set the settings into the controller
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    11.12.2018
     * @return  HashMap<String, String[]>: (Input-text for the ChoiceBoxes)
     */
    public HashMap<String, String[]> setControllerSettings()
    {
        HashMap<String, String[]> controllerSettings = new HashMap<>();

        controllerSettings.put("allgorithmusType", allgorithmusTypes);
        controllerSettings.put("typeOfCrossroad", typeOfCrossroad);

        return controllerSettings;
    }


    /**
     * startConfigurationFromCrossroad: Configure a new crossrad
     *
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date   11.12.2018
     */
    public void startConfigurationFromCrossroad(HashMap<String, HashMap> settingsForCrossroad)
    {
        this.settingsForCrossroad = settingsForCrossroad;
    }


    /**
     * Method epenAboutWindow: New Window for info
     *
     *
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   14.11.2018
     */
    public void openAboutWindow() throws Exception {
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
     * closeProgram: Close program Ampelsteuerung
     *
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date   11.12.2018
     */
    public void closeProgram()
    {
        System.exit(0);
    }
}
