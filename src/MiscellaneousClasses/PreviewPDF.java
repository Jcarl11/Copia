
package MiscellaneousClasses;

import com.qoppa.pdfViewerFX.PDFViewer;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class PreviewPDF 
{
    private PDFViewer viewer;
    public PreviewPDF()
    {
        viewer = new PDFViewer();
    }
    
    public PDFViewer showPDF(String path) throws Exception
    {
        viewer.loadPDF(path);
        viewer.setScale(75);
        viewer.setSplitVisible(false);
        viewer.setToolBarVisible(false);
        viewer.setMaxWidth(360);
        viewer.setMaxHeight(543);
        viewer.setZoomMode(PDFViewer.ZOOMMODE_FITWIDTH);
        return viewer;
    }
    
    public void clear()
    {
        viewer.clearDocument();
    }
    
  
    
}
