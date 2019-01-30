import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class VehicleController extends AnchorPane implements Initializable
{
    @FXML private ImageView carImage;
    @FXML private ImageView busImage;
    @FXML private ImageView biycleView;

    private VehicleModel vehicleModel;

    public VehicleController(VehicleModel vehicleModel, Point2D ref, Point2D offset, int Rotate)
    {
        this.vehicleModel = vehicleModel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("vehicle.fxml"));
        loader.setRoot(this);       // dieses BorderPane als root element des GUI setzen. Dazu muss in primaryStage.fxml das root element folgendes sein: <fx:root type="BorderPane">
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayoutX(ref.getX() + offset.getX());
        setLayoutY(ref.getY() + offset.getY());
        setRotate(Rotate);
        setScaleX(1);
        setScaleY(1);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    }
}
