import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
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

    private MovedElements movedElements;
    private VehicleModel vehicleModel;

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

        setRotate(vehicleModel.getStartRotation());
        Point2D point2d = vehicleModel.getStartPosition();
        setLayoutX(point2d.getX());
        setLayoutY(point2d.getY());

        movedElements = vehicleModel.getTypeOfMovedElements();
        switch(movedElements)
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
        Point2D point2d = vehicleModel.getNewPosition();
        int rotation = vehicleModel.getNewRotation();

        setLayoutX(point2d.getX());
        setLayoutY(point2d.getY());
        setRotate(rotation);
    }
}
