import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DrivewayRouteController implements Initializable
{
    @FXML   private AnchorPane bicycleSripes;
    @FXML   private Group pedestrianStripes;

    private DrivewayRoute drivewayRoute;
    private HashMap<String, Boolean> settingsFromCheckBoxes = new HashMap<>();

    public DrivewayRouteController(DrivewayRoute drivewayRoute)
    {
        this.drivewayRoute = drivewayRoute;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        settingsFromCheckBoxes = drivewayRoute.getStateFromChckboxes();

        bicycleSripes.setVisible(false);
        pedestrianStripes.setVisible(false);

        /* Settings for checkboxes */
        if(settingsFromCheckBoxes.get("checkboxvelostripes") == true)
        {
            bicycleSripes.setVisible(true);
        }
        if(settingsFromCheckBoxes.get("checkboxbusway") == true)
        {
            //todo: Busstreifen noch nicht implementiert
        }
        if(settingsFromCheckBoxes.get("checkboxtramway") == true)
        {
            //todo: Tramweg noch nicht implementiert
        }
        if(settingsFromCheckBoxes.get("pedestrainStripesCheckbox") == true)
        {
            pedestrianStripes.setVisible(true);
        }
    }
}
