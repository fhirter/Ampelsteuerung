import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class DrivewayController implements Observer{



    DrivewayModel drivewayModel;

    @FXML
    Pane pedestrianStripes;



    @Override
    public void update() {

        drivewayModel.getPedestrianStripes();
        drivewayModel.getBicyclePatch();
        drivewayModel.getPublicTrafficRail();
    }

    public void setModel(DrivewayModel drivewayModel) {
        this.drivewayModel = drivewayModel;

    }
    }
