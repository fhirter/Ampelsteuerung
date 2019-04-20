import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class VehicleController extends ImageView implements Observer
{
    private Vehicle vehicle;
    private Position position;

    private  Rotate rotate;

    public VehicleController(Vehicle vehicle)
    {
        this.vehicle = vehicle;

        setImage(new Image("images/car.png"));

        position = vehicle.getStartPosition();
        setLayoutX(position.getX());
        setLayoutY(position.getY());

        rotate = new Rotate(position.getAngle(),(vehicle.getLength()-vehicle.getWheelbase())/2,vehicle.getWidth()/2);

        getTransforms().add(rotate);
     //   setRotate(position.angle);
    }


    @Override
    public void update()
    {
        position = vehicle.getPosition();

        setLayoutX(position.getX());
        setLayoutY(position.getY());

        rotate.setAngle(position.angle);
    }
}
