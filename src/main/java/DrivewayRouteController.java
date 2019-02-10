import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DrivewayRouteController extends AnchorPane implements Initializable, Observer
{
    @FXML   private AnchorPane bicycleSripes;
    @FXML   private Group pedestrianStripes;
    @FXML   private AnchorPane drivewayRoute;

    private DrivewayRoute model;
    private int Rotate;

    /**
     * DrivewayRouteController: Constructor
     *
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    02.08.2018
     * @arg     drivewayRoute (Object form model class), ref (Referenze for all Objects) offset( Place for DrivewayRoute) Rotate (Ankle°)
     */
    public DrivewayRouteController(DrivewayRoute drivewayRoute, Point2D ref, Point2D offset, int Rotate)
    {
        this.model= drivewayRoute;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("drivewayRoute.fxml"));
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
        setRotate(Rotate);
        setScaleX(1);
        setScaleY(1);
    }
    /**
     * initialize(URL location, ResourceBundle resources): Initialize during startUp all settings from the drivewayRoute
     *
     * Is automatic called when fxmlLoader.load() ist called.
     *
     * @version 1.0
     * @autor   NIN Class
     * @date    10.02.2019
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bicycleSripes.setVisible(false);
        pedestrianStripes.setVisible(false);
        model.setPedestrianStripes(false);
        model.setVelostripes(false);

    }

    /**
     * update(): Obstacle where is registred into DrivewayRoute
     *
     * Is automatic called when something into trafficLightModel is changed.
     *
     * @version 1.0
     * @autor   Class NIN
     * @date    10.02.2019
     */

    @Override
    public void update() {

        if(model.getVelostripes() == true)
        {
            bicycleSripes.setVisible(true);
        }
        else{
            bicycleSripes.setVisible(false);
        }

        if(model.getPedestrianStripes()== true)
        {
            pedestrianStripes.setVisible(true);
        }
        else {
            pedestrianStripes.setVisible(false);
        }

        if(model.getVisibility() == true)
        {
            drivewayRoute.setVisible(true);
        }
        else {
            drivewayRoute.setVisible(false);
        }
    }
}
