import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class PrimaryStageModel extends Observable
{
    private final String[] allgorithmusTypes = {"Algorithm A",
                                                "Algorithm B",
                                                "Algorithm C",
                                                "Algorithm D",
                                                "Algorithm E"};

    private final String[] typeOfCrossroad = {"3 Streets",
                                              "4 Streets"};

    private Crossroad crossroad;


    /**
     * PrimaryStageModel: Constructor for class PrimaryStageModel
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    11.12.2018
     * @arg     Crossroad crossroad: Instance form class crossroad
     */
    public PrimaryStageModel(Crossroad crossroad)
    {
        this.crossroad = crossroad;
    }


    /**
     * getControllerSettings: Set the settings into the controller
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    11.12.2018
     * @return  HashMap<String, String[]>: (Input-text for the ChoiceBoxes)
     */
    public HashMap<String, String[]> getControllerSettings()
    {
        HashMap<String, String[]> controllerSettings = new HashMap<>();

        controllerSettings.put("allgorithmusType", allgorithmusTypes);
        controllerSettings.put("typeOfCrossroad", typeOfCrossroad);

        return controllerSettings;
    }


    /**
     * startConfigurationIsPressed: Store from selected crossroad the configuration.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date   11.12.2018
     */
    public void startConfigurationIsPressed(HashMap<String, HashMap> settingsForCrossroad)
    {
        try {

            crossroad.configureAndDrawCrossroad(settingsForCrossroad);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
