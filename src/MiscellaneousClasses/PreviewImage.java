package MiscellaneousClasses;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class PreviewImage 
{
    public PreviewImage()
    {
        
    }
    
    public ImageView showImage(String path)
    {
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        ImageView imageContainer = new ImageView();
        imageContainer.setFitWidth(360);
        imageContainer.setFitHeight(250);
        imageContainer.setImage(image);
        return imageContainer;
    }
}
