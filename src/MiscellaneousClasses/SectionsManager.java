package MiscellaneousClasses;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class SectionsManager 
{
    public SectionsManager()
    {
        
    }
    
    public static void showPane(AnchorPane container, GridPane toAdd)
    {
        if(!container.getChildren().contains(toAdd))
        {
            clearThis(container);
            container.getChildren().add(toAdd);
        }
    }
    
    public static void clearThis(AnchorPane container)
    {
        container.getChildren().clear();
    }
}
