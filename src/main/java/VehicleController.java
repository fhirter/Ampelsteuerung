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
        setLayoutX(position.x);
        setLayoutY(position.y);

        rotate = new Rotate(position.getAngle(),500,0);

        getTransforms().add(rotate);
     //   setRotate(position.angle);
    }


    @Override
    public void update()
    {
        position = vehicle.getPosition();

        setLayoutX(position.x);
        setLayoutY(position.y);

        rotate.setAngle(position.angle);
    }
}
