import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.text.Position;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class GreenPlanetController implements Initializable
{
    @FXML   private ImageView imageTree1;
    @FXML   private ImageView imageTree2;
    @FXML   private ImageView imageTree3;
    @FXML   private ImageView imageTree4;
    @FXML   private ImageView imageTree5;
    @FXML   private ImageView imageBusch1;
    @FXML   private ImageView imageBusch2;
    @FXML   private ImageView imageBusch3;
    @FXML   private ImageView imageBusch4;
    @FXML   private ImageView imageBusch5;

    final String path = "..\\Ampelsteuerung\\Dokumente\\Bilder\\";
    final String nameTree1 = "Baum1.png";
    final String nameTree2 = "Baum2.png";
    final String nameTree3 = "Baum3.png";
    final String nameBusch1 = "Busch1.png";
    final String nameBusch2 = "Busch2.png";

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setAndDrawImage(nameTree1, imageTree1, 10, 10, 1.2);
        setAndDrawImage(nameTree2, imageTree2, 50, 150, 1.6);
        setAndDrawImage(nameTree3, imageTree3,650, 100, 1.9);
        setAndDrawImage(nameTree2, imageTree4, 70, 550,1.5);
        setAndDrawImage(nameTree1, imageTree5,580, 550,1.4);

        setAndDrawImage(nameBusch1, imageBusch1, 550, 30,0.6);
        setAndDrawImage(nameBusch2, imageBusch2, 700, 700,0.8);
        setAndDrawImage(nameBusch1, imageBusch3, 30, 700,0.7);
        setAndDrawImage(nameBusch2, imageBusch4, 150, 50,0.7);
        setAndDrawImage(nameBusch1, imageBusch5, 330, 600,0.8);
    }

    public void setAndDrawImage(String pictureName, ImageView image, int posX, int posY, double scale)
    {
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
