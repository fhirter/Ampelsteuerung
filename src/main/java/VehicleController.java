import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;


public class VehicleController implements Initializable {

    /*
    @FXML private ImageView carImage;
    @FXML private ImageView busImage;
    @FXML private ImageView biycleView;
*/

    private VehicleModel vehicleModel;

    public VehicleController(VehicleModel vehicleModel)
    {
        this.vehicleModel = vehicleModel;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }
}
