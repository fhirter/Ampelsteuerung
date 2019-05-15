package crossroad;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class crossroad.GreenPlanetController Class for the Erviroment
 *
 * @version 1.0
 * @autor Class NIN
 * @date 02.10.2019
 */
public class GreenPlanetController extends AnchorPane implements Initializable {
    @FXML
    private ImageView imageTree1;
    @FXML
    private ImageView imageTree2;
    @FXML
    private ImageView imageTree3;
    @FXML
    private ImageView imageTree4;
    @FXML
    private ImageView imageTree5;
    @FXML
    private ImageView imageBusch1;
    @FXML
    private ImageView imageBusch2;
    @FXML
    private ImageView imageBusch3;
    @FXML
    private ImageView imageBusch4;
    @FXML
    private ImageView imageBusch5;

    final String path = "..\\Ampelsteuerung\\Dokumente\\Bilder\\";
    final String nameTree1 = "images/Baum1.png";
    final String nameTree2 = "images/Baum2.png";
    final String nameTree3 = "images/Baum3.png";
    final String nameBusch1 = "images/Busch1.png";
    final String nameBusch2 = "images/Busch2.png";


    /**
     * crossroad.GreenPlanetController: Constructor
     *
     * @version 1.0
     * @autor Class NIN
     * @date 10.02.2019
     * @arg crossroad (Object form crossroad.Crossroad), ref (Referenze for all Objects) offset( Place for greenPlanet))
     */
    public GreenPlanetController(Point2D ref, Point2D offset) {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/greenPlanet.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // positioning
        setLayoutX(ref.getX() + offset.getX());
        setLayoutY(ref.getY() + offset.getY());
    }

    /**
     * initialize(URL location, ResourceBundle resources): Initialize during startUp all settings from the greenPlanet
     * <p>
     * Is automatic called when fxmlLoader.load() ist called.
     *
     * @version 1.0
     * @autor NIN Class
     * @date 10.02.2019
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAndDrawImage(nameTree1, imageTree1, 10, 10, 1.2);
        setAndDrawImage(nameTree2, imageTree2, 50, 150, 1.6);
        setAndDrawImage(nameTree3, imageTree3, 650, 100, 1.9);
        setAndDrawImage(nameTree2, imageTree4, 70, 600, 1.5);
        setAndDrawImage(nameTree1, imageTree5, 580, 590, 1.4);

        setAndDrawImage(nameBusch1, imageBusch1, 550, 30, 0.6);
        setAndDrawImage(nameBusch2, imageBusch2, 700, 700, 0.8);
        setAndDrawImage(nameBusch1, imageBusch3, 30, 700, 0.7);
        setAndDrawImage(nameBusch2, imageBusch4, 150, 50, 0.7);
        setAndDrawImage(nameBusch1, imageBusch5, 330, 600, 0.8);
    }

    /**
     * crossroad.GreenPlanetController: set the Erviroment
     *
     * @version 1.0
     * @autor NIN Class
     * @date 02.08.2018
     */
    public void setAndDrawImage(String pictureName, ImageView image, int posX, int posY, double scale) {
        Image imageStream = null;
        try {
            imageStream = new Image(new FileInputStream(path + pictureName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        image.setTranslateX(posX);
        image.setTranslateY(posY);
        image.setScaleX(scale);
        image.setScaleY(scale);
        image.setImage(imageStream);
    }
}
