import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CenterPane extends AnchorPane implements Initializable
{
    @FXML   private Pane pedestrianWay;
    @FXML   private Pane fourthRoute;

    private Crossroad crossroadModel;
    private HashMap<String, String> allgorithmusAndTypeFromCrossroad;


    /**
     * CenterPane(): Constructor
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    29.12.2018
     * @arg     DrivewayCenterModel: (Instance from DrivewayCenterModel)
     */
    public CenterPane(Crossroad crossroad, Point2D ref, Point2D offset)
    {
        this.crossroadModel = crossroad; // center does not need own model

        FXMLLoader loader = new FXMLLoader(getClass().getResource("drivewayCenter.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // positioning
        setLayoutX(ref.getX() + offset.getX());
        setLayoutY(ref.getY() + offset.getY());
    }


    /**
     * initialize(URL location, ResourceBundle resources): Initialize during startUp all settings from the CenterPane
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
        if(crossroadModel.getNumberOfDriveways().toString() == "3")
        {
            pedestrianWay.setVisible(true);
            fourthRoute.setVisible(false);
        }else if(crossroadModel.getNumberOfDriveways().toString() == "4")
        {
            pedestrianWay.setVisible(false);
            fourthRoute.setVisible(true);
        }
    }


    /**
     * update the count (tree or four) of number from the crossroad
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    01.02.2019
     */
    public void updateNumberOfCrossroad(String numberOfCrossroad)
    {
        if(numberOfCrossroad == "3")
        {
            pedestrianWay.setVisible(true);
            fourthRoute.setVisible(false);
        }else if(numberOfCrossroad == "4")
        {
            pedestrianWay.setVisible(false);
            fourthRoute.setVisible(true);
        }
    }
}
