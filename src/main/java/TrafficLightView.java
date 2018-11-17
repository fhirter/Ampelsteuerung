import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrafficLightView extends Node      // so können dann deine ampeln in die main gui eingebaut werden, dies habe ich aber nicht getestet
{
    private TrafficLight trafficLight;

    public TrafficLightView(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;       // hier übergebe ich dir mal das datenmodell. anhand von diesem erstellst du dann das gui. in diesem konkreten fall wohl nur, ob fussgänger oder autoampel, der rest ist ja immer gleich

        launchGui();
    }

    /**
     * launchGui(): Start gui for trafficLight
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    15.11.2018
     */
    public void launchGui() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trafficLightView.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            TrafficLightController trafficLightController = fxmlLoader.getController();


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
