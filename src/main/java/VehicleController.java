import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class VehicleController extends AnchorPane implements Initializable, Observer
{
    @FXML private ImageView carImage;
    @FXML private ImageView busImage;
    @FXML private ImageView bicycleImage;
    @FXML private Group movedElementsGroup;

    private MovedElement movedElement;
    private VehicleModel vehicleModel;
    private Position position;

    public VehicleController(VehicleModel vehicleModel)
    {
        this.vehicleModel = vehicleModel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("vehicle.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        carImage.setVisible(false);
        busImage.setVisible(false);
        bicycleImage.setVisible(false);

        position = vehicleModel.getStartPosition();
        setLayoutX(position.x);
        setLayoutY(position.y);
        setRotate(position.angle);

        movedElement = vehicleModel.getTypeOfMovedElements();
        switch(movedElement)
        {
            case Car:
            {
                carImage.setVisible(true);
                setScaleX(0.8);
                setScaleY(0.8);
                break;
            }
            case Bus:
            {
                busImage.setVisible(true);
                setScaleX(0.8);
                setScaleY(0.8);
                break;
            }
            case Bicycle:
            {
                bicycleImage.setVisible(true);
                setScaleX(0.4);
                setScaleY(0.4);
                break;
            }
        }
    }


    @Override
    public void update()
    {
        position = vehicleModel.getNewPosition();

        setLayoutX(position.x);
        setLayoutY(position.y);
        setRotate(position.angle);
    }
}
