package virtual.catalog.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import virtual.catalog.dao.CategoryDAO;
import virtual.catalog.model.Category;

public class AddProductController implements Initializable {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldPrice;
    @FXML
    private ComboBox<Category> comboBoxCategory;
    @FXML
    private TextArea textAreaDescription;
    @FXML
    private Button btnAddImage;
    @FXML
    private Button btnAddProduct;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getCategories();
        
        if (!categories.isEmpty()) {
            comboBoxCategory.setItems(FXCollections.observableList(categories));
        }
    }    

    @FXML
    private void ButtonAddImage(MouseEvent event) {
    }

    @FXML
    private void ButtonAddProduct(MouseEvent event) {
        if (textFieldName.getText().isEmpty()) {
            
        }
        
        if (textFieldPrice.getText().isEmpty()) {
            
        }
        
        if (comboBoxCategory.isPressed()) {
            
        }
    }

    void setMainController(MainController aThis) {
        
    }
    
}
