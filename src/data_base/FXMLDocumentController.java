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
import java.nio.file.Files;
import java.text.Collator;
import java.util.ArrayList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    DatabaseQuery dbQuery = new DatabaseQuery();
    ClientEntity clientEntity = new ClientEntity();
    @FXML
    private ListView<String> listview_specifications_FiletoUpload,listview_client_FiletoUpload,listview_suppliers_FiletoUpload, 
            listview_contractors_FiletoUpload;
    @FXML
    private AnchorPane anchorpane_main,anchorpane_viewdocument;
    @FXML
    private GridPane gridpane_specifications,gridpane_consultants,gridpane_contractors,gridpane_suppliers
            ,gridpane_client;
    @FXML
    private ComboBox<String> combobox_client_industry,combobox_client_type,combobox_suppliers_industry,combobox_suppliers_type
            ,combobox_contractors_industry,combobox_contractors_classificiation;
    @FXML
    private TextField textfield_client_representative,textfield_client_position,textfield_client_companyname;
    
    @FXML
    void clientOnClicked(ActionEvent event)  throws Exception
    {
        SectionsManager.showPane(anchorpane_main, gridpane_client);
    }
    @FXML
    void button_clients_choosefileOnClick(ActionEvent event)
    {
        listview_client_FiletoUpload.getItems().addAll(showChooserDialog("pdf","jpg","png","gif"));
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
    void suppliersOnClicked(ActionEvent event)
    {
        SectionsManager.showPane(anchorpane_main, gridpane_suppliers);
    }
    
    @FXML
    void button_suppliers_choosefileOnClick(ActionEvent event)
    {
        listview_suppliers_FiletoUpload.getItems().addAll(showChooserDialog("pdf","jpg","png","gif"));
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
    
    @FXML
    void specificationsClicked(ActionEvent event)
    {
        SectionsManager.showPane(anchorpane_main, gridpane_specifications);
    }
    
    @FXML
    void button_specifications_choosefileOnClick(ActionEvent event) 
    {
        listview_specifications_FiletoUpload.getItems().addAll(showChooserDialog("pdf"));
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
    void button_contractorsOnClick(ActionEvent event)
    {
        SectionsManager.showPane(anchorpane_main, gridpane_contractors);
    }
    
    @FXML
    void button_contractors_choosefileOnClick(ActionEvent event) 
    {
        listview_contractors_FiletoUpload.getItems().addAll(showChooserDialog("pdf","jpg","png","gif"));
    }
    
    @FXML
    void button_contractors_previewOnClick(ActionEvent event) 
    {
        if(listview_contractors_FiletoUpload.getSelectionModel().getSelectedItem() != null)
        {
            anchorpane_viewdocument.getChildren().clear();
            String extension = FilenameUtils.getExtension(listview_contractors_FiletoUpload.getSelectionModel().getSelectedItem());
            if(extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("gif"))
            {
                anchorpane_viewdocument.getChildren().add(previewimage.showImage(listview_contractors_FiletoUpload.getSelectionModel().getSelectedItem()));
            }
            else if(extension.equalsIgnoreCase("pdf"))
            {
                try
                {
                    anchorpane_viewdocument.getChildren().add(previewpdf.showPDF(listview_contractors_FiletoUpload.getSelectionModel().getSelectedItem()));
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
    void button_contractors_removeOnClick(ActionEvent event) 
    {
         if(listview_contractors_FiletoUpload.getSelectionModel().getSelectedItem() != null)
        {
            int selection = JOptionPane.showConfirmDialog(null, "Delete selected fie?", "Confirm", 
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(selection == JOptionPane.YES_OPTION)
            {
                int index = listview_contractors_FiletoUpload.getSelectionModel().getSelectedIndex();
                listview_contractors_FiletoUpload.getItems().remove(index);
            }
        }
    }
    
    @FXML
    void button_upload(ActionEvent event)
    {
        File file = new File("D:/Back Up11-10-18/JOEY/OJT/Project/ProcessFlow.pdf");
        try
        {
            byte[] dataByte = Files.readAllBytes(file.toPath());
            clientEntity.setRepresentative(textfield_client_representative.getText().trim());
            clientEntity.setPosition(textfield_client_position.getText().trim());
            clientEntity.setCompany_Name(textfield_client_companyname.getText().trim());
            clientEntity.setIndustry(combobox_client_industry.getSelectionModel().getSelectedItem());
            clientEntity.setType(combobox_client_type.getSelectionModel().getSelectedItem());
            clientEntity.setFileToUpload(file);
            
            dbQuery.SendPostData(clientEntity, "https://concipiotektura.back4app.io/classes/PDFFiles/New.pdf", "POST");
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
    
    @FXML
    void button_resetfields(ActionEvent event) 
    {
        previewpdf.clear();
    }
    
    private ArrayList<String> showChooserDialog(String... acceptableFileTypes)
    {
        ArrayList<String> paths = new ArrayList<>();
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileFilter(new FileNameExtensionFilter("Files",acceptableFileTypes));
        chooser.showOpenDialog(null);
        File[] selectedFiles = chooser.getSelectedFiles();
        if(selectedFiles != null)
        {
            for(File getPath: selectedFiles)
            {
                paths.add(getPath.getAbsolutePath().toString());
            }
        }
        return paths;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        SectionsManager.clearThis(anchorpane_main);
        try
        {
            combobox_client_industry.setItems(new SortedList<String>(dbQuery.RetrieveComboboxData("https://concipiotektura.back4app.io/classes/ComboboxData?where={\"Field\":\"Industry\",\"Category\":\"Client\"}", "GET"),Collator.getInstance()));
            combobox_client_type.setItems(new SortedList<String>(dbQuery.RetrieveComboboxData("https://concipiotektura.back4app.io/classes/ComboboxData?where={\"Field\":\"Type\",\"Category\":\"Client\"}","GET"),Collator.getInstance()));
            combobox_suppliers_industry.setItems(new SortedList<String>(dbQuery.RetrieveComboboxData("https://concipiotektura.back4app.io/classes/ComboboxData?where={\"Field\":\"Industry\",\"Category\":\"Suppliers\"}", "GET"),Collator.getInstance()));
            combobox_suppliers_type.setItems(new SortedList<String>(dbQuery.RetrieveComboboxData("https://concipiotektura.back4app.io/classes/ComboboxData?where={\"Field\":\"Type\",\"Category\":\"Suppliers\"}", "GET"),Collator.getInstance()));
            combobox_contractors_industry.setItems(new SortedList<String>(dbQuery.RetrieveComboboxData("https://concipiotektura.back4app.io/classes/ComboboxData?where={\"Field\":\"Industry\",\"Category\":\"Contractors\"}", "GET"), Collator.getInstance()));
            combobox_contractors_classificiation.setItems(new SortedList<String>(dbQuery.RetrieveComboboxData("https://concipiotektura.back4app.io/classes/ComboboxData?where={\"Field\":\"Classification\",\"Category\":\"Contractors\"}", "GET"), Collator.getInstance()));
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        gridpane_client.setVisible(true);
        gridpane_suppliers.setVisible(true);
        gridpane_contractors.setVisible(true);
        gridpane_specifications.setVisible(true);
    }    
    
}
