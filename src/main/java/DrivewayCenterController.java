import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DrivewayCenterController implements Initializable
{
    @FXML   private Rectangle pedestrianWay;

    private DrivewayCenterModel drivewayCenterModel;
    private HashMap<String, String> allgorithmusAndTypeFromCrossroad;

    /**
     * DrivewayCenterController(): Constructor
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    29.12.2018
     * @arg     DrivewayCenterModel: (Instance from DrivewayCenterModel)
     */
    public DrivewayCenterController(DrivewayCenterModel drivewayCenterModel)
    {
        this.drivewayCenterModel = drivewayCenterModel;
    }


    /**
     * initialize(URL location, ResourceBundle resources): Initialize during startUp all settings from the DrivewayCenterController
     *
     * Is automatic called when fxmlLoader.load() ist called.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    29.12.2018
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        allgorithmusAndTypeFromCrossroad = drivewayCenterModel.getAllgorithmusAndTypeFromCrossroad();

        if(allgorithmusAndTypeFromCrossroad.get("typeOfCrossroad").equals("3 Streets"))
        {
            pedestrianWay.setVisible(true);
        }else if(allgorithmusAndTypeFromCrossroad.get("typeOfCrossroad").equals("4 Streets"))
        {
            pedestrianWay.setVisible(false);
        }
    }
}
