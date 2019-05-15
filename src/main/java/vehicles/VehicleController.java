package vehicles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import util.Observer;
import util.Position;

public class VehicleController extends ImageView implements Observer {
    private Vehicle vehicle;
    private Position position;

    private Rotate rotate;

    public VehicleController(Vehicle vehicle) {
        this.vehicle = vehicle;
        vehicle.addObserver(this);

        setImage(new Image("/images/car.png"));

        position = vehicle.getStartPosition();
        setLayoutX(position.getX());
        setLayoutY(position.getY());

        rotate = new Rotate(position.getAngle());

        getTransforms().add(rotate);
        //   setRotate(position.getAngle());
    }


    @Override
    public void update() {
        position = vehicle.getPosition();

        setLayoutX(position.getX());
        setLayoutY(position.getY());

        // setRotate(position.getAngle());
        rotate.setAngle(position.getAngle());
    }
}
