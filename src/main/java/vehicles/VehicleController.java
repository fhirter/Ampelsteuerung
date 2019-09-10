package vehicles;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import org.w3c.dom.css.Rect;
import util.Observer;
import util.Position;

import java.util.concurrent.ConcurrentLinkedDeque;

public class VehicleController extends StackPane implements Observer {
    private final Point2D referencePoint;
    private final Vehicle vehicle;
    private Position position;

    private Rotate rotate;

    public VehicleController(Vehicle vehicle, Point2D referencePoint) {
        this.vehicle = vehicle;
        this.referencePoint = referencePoint;

        initPosition();

        vehicle.addObserver(this);

        ImageView image = new ImageView("/images/car.png");
        Rectangle rect = new Rectangle(position.getX(), position.getY(), vehicle.getLength(), vehicle.getWidth());
        rect.setFill(Color.ALICEBLUE);

        getChildren().addAll(rect,image);

        setCollisionArea();
    }

    private void setCollisionArea() {

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

    public Vehicle getVehicle() {
        return vehicle;
    }
}
