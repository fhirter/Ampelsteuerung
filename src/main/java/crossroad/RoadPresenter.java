package crossroad;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import traffic_lights.TrafficLight;
import traffic_lights.TrafficLightPresenter;
import util.Area;
import util.Observer;
import util.Position;

import java.io.IOException;

/**
 * @autor Schweizer Patrick, Grimm Raphael, Vogt Andreas, Reiter Daniel, Hirter Fabian
 * @since  02.08.2018
 *
 */

public class RoadPresenter extends Group implements Observer {
    private final Point2D refTrafficLights = new Point2D(0, 0);
    private final Point2D referencePoint;
    @FXML private Group bicycleLane;
    @FXML private Line pedestrianStripes;
    @FXML private Group drivewayRoute;
    @FXML private Rectangle stopLine;

    private final Road road;
    private final TrafficLightPresenter trafficLightPresenter;

    public RoadPresenter(Road road, Point2D referencePoint) throws IOException {
        this.road = road;
        this.road.addObserver(this);
        this.referencePoint = referencePoint;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/road.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Position position = road.getPosition();

        // positioning
        getTransforms().add(new Rotate(position.getAngle(), 0, 0));

        setLayoutX(referencePoint.getX() + position.getX());
        setLayoutY(referencePoint.getY() + position.getY());

        // Traffic Light
        TrafficLight trafficLight = road.getTrafficLight();
        trafficLightPresenter = new TrafficLightPresenter(trafficLight, refTrafficLights, new Position(130, 145, 90));

        trafficLightPresenter.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(mouseEvent);
            }
        });
        getChildren().add(trafficLightPresenter);
        trafficLight.addObserver(trafficLightPresenter);

        setStopArea();

    }

    private void setStopArea() {
        Area stopArea = road.getStopArea();

        final int height = stopArea.getHeight();
        final int width = stopArea.getWidth();
        final Point2D offset = new Point2D(425,125);

        stopLine.setLayoutX(stopArea.getCenter().getX()-width/2 + offset.getX());
        stopLine.setLayoutY(stopArea.getCenter().getY()-height/2 + offset.getY());

        stopLine.setHeight(height);
        stopLine.setWidth(width);
    }


    @Override
    public void update() {
        if (road.isVisible() == true) {
            drivewayRoute.setVisible(true);
        } else {
            drivewayRoute.setVisible(false);
        }
    }
}
