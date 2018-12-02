import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Class PrimaryStageController: Class for handling the PrimaryStage
 *
 *
 *
 * @version 1.0
 * @autor   Class NIN
 * @date   30.11.2018
 */
public class PrimaryStageController implements Observer {


    @FXML
    MenuItem exitSoftware;
    @FXML
    CheckBox checkboxvelostripes;
    @FXML
    CheckBox checkboxbusway;
    @FXML
    CheckBox checkboxtramway;
    @FXML
    private ChoiceBox<String> setchoiceOfAlgorithm;
    @FXML
    private ChoiceBox<String> setnumberOfCrossing;
    @FXML
    CheckBox pedestrainStripesCheckbox;
    @FXML
    Pane pedestrianStripes;
    @FXML
    Button startButtonConfiguration;

    DrivewayModel drivewayModel;

    //todo
    @FXML
    private void handleSubmitButtonAction() {
        String message = "Your Choice:\n";


        if (checkboxvelostripes.isSelected()) {
            message += "Velo Stripes TRUE\n";

        }

        if (checkboxbusway.isSelected())
            message += "Bus Way TRUE\n";

        if (checkboxtramway.isSelected())
            message += "Tramway TRUE\n";


        System.out.println(message);
        System.out.println(setnumberOfCrossing.getValue());
        System.out.println(setchoiceOfAlgorithm.getValue());

    }

    @FXML
    public void setSetchoiceOfAlgorithm(List<String> algorithm) {

        setchoiceOfAlgorithm.getItems().addAll(algorithm);
    }

    public void setSetnumberOfCrossing(List<String> crossing) {

    }

    /**
     * Method handleExitSoftware: Exit Software
     *
     *
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   30.11.2018
     */
    public void handleExitSoftware() {
        System.exit(0);

    }


    public void newProject(MouseEvent mouseEvent) throws IOException {
        // Erstellen eines neuen Projektes

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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("aboutStage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method startButtonConfig: Start for the configuration
     *
     *
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   14.11.2018
     */
    public void startButtonConfig() throws Exception {
        notify();

    }

    /**
     * Method update: update function for Observer
     *
     *
     *
     * @version 1.0
     * @autor   Class NIN
     * @date   14.11.2018
     */
    @Override
    public void update() {

    }
}
