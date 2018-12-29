import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class DrivewayCenterController implements Observer, Initializable {

    @FXML
    AnchorPane drivewayAnchorPane;
    @FXML
    Pane pedestrianStripes;
    @FXML
    Group driveway;

    /**
     * update(): Obstacle where is registered into driveway
     *
     * Is automatic called when something into drivewayModel is changed.
     *
     * @version 1.0
     * @autor   Class NIN
     * @date    01.12.2018
     */
    @Override
    public void update()
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {


    }

    public DrivewayCenterController()
    {

    }


    public void setPostion()
    {
        drivewayAnchorPane.setVisible(false);
    }


    public void setPedestrianStripes ()
    {

    }
}
