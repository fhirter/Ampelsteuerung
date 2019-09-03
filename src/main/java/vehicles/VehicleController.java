package vehicles;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import util.Observer;
import util.Position;

public class VehicleController extends ImageView implements Observer {
    private final Point2D referencePoint;
    private final Vehicle vehicle;
    private Position position;

    private Rotate rotate;

    public VehicleController(Vehicle vehicle, Point2D referencePoint) {
        this.vehicle = vehicle;
        this.referencePoint = referencePoint;
        vehicle.addObserver(this);

        setImage(new Image("/images/car.png"));

        initPosition();
    }

    private void initPosition() {
        position = vehicle.getStartPosition().add(referencePoint,0);
        setLayoutX(position.getX());
        setLayoutY(position.getY());

        rotate = new Rotate(position.getAngle());
        getTransforms().add(rotate);
    }

    private void setPosition() {
        position = vehicle.getPosition().add(referencePoint,0);
        setLayoutX(position.getX());
        setLayoutY(position.getY());

        rotate.setAngle(position.getAngle());

    }


    @Override
    public void update() {
        setPosition();

        // setRotate(position.getAngle());

    }
}
