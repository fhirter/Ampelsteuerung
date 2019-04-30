package crossroad;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// todo: move center pane to crossroad controller

public class CenterPane extends AnchorPane implements Initializable
{
    @FXML   private Rectangle pedestrianWay;
    @FXML   private Pane fourthRoute;

    private Crossroad crossroadModel;

    /**
     * crossroad.CenterPane(): Constructor
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    29.12.2018
     * @arg     DrivewayCenterModel: (Instance from DrivewayCenterModel)
     */
    public CenterPane(Crossroad crossroad, Point2D ref)
    {
        this.crossroadModel = crossroad; // center does not need own model

        FXMLLoader loader = new FXMLLoader(getClass().getResource("drivewayCenter.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Point2D offset = new Point2D(-250/2,-250/2);  // todo: get effective height

        // positioning
        setLayoutX(ref.getX() + offset.getX());
       setLayoutY(ref.getY() + offset.getY());
    }


    /**
     * initialize(URL location, ResourceBundle resources): Initialize during startUp all settings from the crossroad.CenterPane
     *
     * Is automatic called when fxmlLoader.load() ist called.
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    29.12.2018
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
//        if(crossroadModel.getRoadCount() == 3)
//        {
//            pedestrianWay.setVisible(true);
//            fourthRoute.setVisible(false);
//        }else if(crossroadModel.getRoadCount() == 4)
//        {
//            pedestrianWay.setVisible(false);
//            fourthRoute.setVisible(true);
//        }
    }


}
