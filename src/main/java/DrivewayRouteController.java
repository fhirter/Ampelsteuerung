import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DrivewayRouteController extends AnchorPane implements Initializable, Observer
{
    @FXML   private AnchorPane bicycleSripes;
    @FXML   private Group pedestrianStripes;

    private DrivewayRoute model;
    private int Rotate;



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


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        //model.getStateFromCheckboxes();



        /* Settings for checkboxes */

        /*if(settingsFromCheckBoxes.get("checkboxbusway") == true)
        {
            //todo: Busstreifen noch nicht implementiert
        }
        if(settingsFromCheckBoxes.get("checkboxtramway") == true)
        {
            //todo: Tramweg noch nicht implementiert
        }*/

    }

    @Override
    public void update() {

        if(model.getVelostripes() == true)
        {
            bicycleSripes.setVisible(true);
        }

        if(model.getPedestrianStripes()== true)
        {
            pedestrianStripes.setVisible(true);
        }


    }
}
