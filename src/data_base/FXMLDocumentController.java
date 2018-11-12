package data_base;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import com.qoppa.pdfViewerFX.PDFViewer;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import MiscellaneousClasses.*;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Joey Carlo Francisco
 */
public class FXMLDocumentController implements Initializable 
{
    
    PreviewPDF previewpdf = new PreviewPDF();
    PreviewImage previewimage = new PreviewImage();
    DatabaseQuery dbQuery;
    @FXML
    private ListView<String> listview_specifications_FiletoUpload,listview_client_FiletoUpload,listview_suppliers_FiletoUpload;
    @FXML
    private AnchorPane anchorpane_main,anchorpane_viewdocument;
    @FXML
    private GridPane gridpane_specifications,gridpane_consultants,gridpane_contractors,gridpane_suppliers
            ,gridpane_client;
    @FXML
    private ComboBox<String> combobox_client_industry,combobox_client_type,combobox_suppliers_industry,combobox_suppliers_type;
    
    @FXML
    void specificationsClicked(ActionEvent event)
    {
        SectionsManager.showPane(anchorpane_main, gridpane_specifications);
    }
    
    @FXML
    void clientOnClicked(ActionEvent event)  throws Exception
    {
        SectionsManager.showPane(anchorpane_main, gridpane_client);
        if(combobox_client_industry.getItems().size() <= 0 || combobox_client_type.getItems().size() <= 0)
        {
            dbQuery = new DatabaseQuery("https://concipiotektura.back4app.io/classes/ComboboxData");
            combobox_client_industry.getItems().addAll(dbQuery.RetrieveComboboxData("GET"));
            combobox_client_type.getItems().add("Corporate");
            combobox_client_type.getItems().add("Private");
            combobox_client_type.getItems().add("Government");
        }
        
    }
    
    @FXML
    void suppliersOnClicked(ActionEvent event)
    {
        SectionsManager.showPane(anchorpane_main, gridpane_suppliers);
        combobox_suppliers_industry.getItems().add("Construction");
        combobox_suppliers_industry.getItems().add("Food and Beverage");
        combobox_suppliers_industry.getItems().add("Retail");
        combobox_suppliers_industry.getItems().add("Hospitality");  
        combobox_suppliers_industry.getItems().add("Graphics and Printing");
        combobox_suppliers_industry.getItems().add("Information Technology");
        combobox_suppliers_industry.getItems().add("Art and Design");
        
       
        combobox_suppliers_type.getItems().add("Corporate");
        combobox_suppliers_type.getItems().add("Private");
        combobox_suppliers_type.getItems().add("Finishing");
    }
    
     @FXML
    void button_specifications_choosefileOnClick(ActionEvent event) 
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
        chooser.showOpenDialog(null);
        File[] selectedFile = chooser.getSelectedFiles();
        if(selectedFile != null)
        {
            for(File getPath: selectedFile)
            {
                listview_specifications_FiletoUpload.getItems().add(getPath.getAbsolutePath().toString());
            }
        }
    }
    
    
    @FXML
    void resetFieldsOnClicked(ActionEvent event) 
    {
        previewpdf.clear();
    }
    
    @FXML
    void button_specifications_previewOnClick(ActionEvent event)
    {
        
        if(listview_specifications_FiletoUpload.getSelectionModel().getSelectedItem() != null)
        {
           anchorpane_viewdocument.getChildren().clear();
            try{
                anchorpane_viewdocument.getChildren().add(previewpdf.showPDF(listview_specifications_FiletoUpload.getSelectionModel().getSelectedItem()));
            }catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else
        {
            System.out.println("Error");
        }
    }
    
    @FXML
    void button_specifications_removeOnClick(ActionEvent event) 
    {
         if(listview_suppliers_FiletoUpload.getSelectionModel().getSelectedItem() != null)
        {
            int selection = JOptionPane.showConfirmDialog(null, "Delete selected fie?", "Confirm", 
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(selection == JOptionPane.YES_OPTION)
            {
                int index = listview_suppliers_FiletoUpload.getSelectionModel().getSelectedIndex();
                listview_suppliers_FiletoUpload.getItems().remove(index);
            }
        }
    }
    
    @FXML
    void button_clients_choosefileOnClick(ActionEvent event)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileFilter(new FileNameExtensionFilter("Files", "pdf","jpg","png","gif"));
        chooser.showOpenDialog(null);
        File[] selectedFiles = chooser.getSelectedFiles();
        if(selectedFiles != null)
        {
            for(File getPath: selectedFiles)
            {
                listview_client_FiletoUpload.getItems().add(getPath.getAbsolutePath().toString());
            }
        }
    }
    @FXML
    void button_client_previewOnClick(ActionEvent event) 
    {
        if(listview_client_FiletoUpload.getSelectionModel().getSelectedItem() != null)
        {
            anchorpane_viewdocument.getChildren().clear();
            String extension = FilenameUtils.getExtension(listview_client_FiletoUpload.getSelectionModel().getSelectedItem());
            if(extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("gif"))
            {
                anchorpane_viewdocument.getChildren().add(previewimage.showImage(listview_client_FiletoUpload.getSelectionModel().getSelectedItem()));
            }
            else if(extension.equalsIgnoreCase("pdf"))
            {
                try
                {
                    anchorpane_viewdocument.getChildren().add(previewpdf.showPDF(listview_client_FiletoUpload.getSelectionModel().getSelectedItem()));
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "File type not supported for preview");
            }
        }
    }
    
    @FXML
    void button_client_removeOnClick(ActionEvent event) 
    {
        if(listview_client_FiletoUpload.getSelectionModel().getSelectedItem() != null)
        {
            int selection = JOptionPane.showConfirmDialog(null, "Delete selected fie?", "Confirm", 
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(selection == JOptionPane.YES_OPTION)
            {
                int index = listview_client_FiletoUpload.getSelectionModel().getSelectedIndex();
                listview_client_FiletoUpload.getItems().remove(index);
            }
        }
    }
    
    @FXML
    void button_suppliers_choosefileOnClick(ActionEvent event)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileFilter(new FileNameExtensionFilter("Files", "pdf","jpg","png","gif"));
        chooser.showOpenDialog(null);
        File[] selectedFiles = chooser.getSelectedFiles();
        if(selectedFiles != null)
        {
            for(File getPath: selectedFiles)
            {
                listview_suppliers_FiletoUpload.getItems().add(getPath.getAbsolutePath().toString());
            }
        }
    }
    
    @FXML
    void button_suppliers_previewOnClick(ActionEvent event) 
    {
        if(listview_suppliers_FiletoUpload.getSelectionModel().getSelectedItem() != null)
        {
            anchorpane_viewdocument.getChildren().clear();
            String extension = FilenameUtils.getExtension(listview_suppliers_FiletoUpload.getSelectionModel().getSelectedItem());
            if(extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("gif"))
            {
                anchorpane_viewdocument.getChildren().add(previewimage.showImage(listview_suppliers_FiletoUpload.getSelectionModel().getSelectedItem()));
            }
            else if(extension.equalsIgnoreCase("pdf"))
            {
                try
                {
                    anchorpane_viewdocument.getChildren().add(previewpdf.showPDF(listview_suppliers_FiletoUpload.getSelectionModel().getSelectedItem()));
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "File type not supported for preview");
            }
        }
    }

    @FXML
    void button_suppliers_removeOnClick(ActionEvent event) 
    {
        if(listview_suppliers_FiletoUpload.getSelectionModel().getSelectedItem() != null)
        {
            int selection = JOptionPane.showConfirmDialog(null, "Delete selected fie?", "Confirm", 
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(selection == JOptionPane.YES_OPTION)
            {
                int index = listview_suppliers_FiletoUpload.getSelectionModel().getSelectedIndex();
                listview_suppliers_FiletoUpload.getItems().remove(index);
            }
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SectionsManager.clearThis(anchorpane_main);
    }    
    
}
