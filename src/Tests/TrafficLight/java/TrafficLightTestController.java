import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class TrafficLightTestController
{
    private TrafficLightTestModel model;

    @FXML private RadioButton allOn;
    @FXML private RadioButton flashYellow;
    @FXML private RadioButton stateToRed;
    @FXML private RadioButton stateToGreen;
    @FXML private RadioButton dark;


    public void setModel(TrafficLightTestModel model)
    {
        this.model = model;
    }


    public void runSimulation(ActionEvent actionEvent)
    {
        if(flashYellow.isSelected())
        {
            model.setYellow();
        }
        else if(stateToRed.isSelected())
        {
            model.setRed();
        }
        else if(stateToGreen.isSelected())
        {
            model.setGreen();
        }
        else if(allOn.isSelected())
        {
            model.setAllOn();
        }
        else if(dark.isSelected())
        {
            model.setDark();
        }
    }
}
