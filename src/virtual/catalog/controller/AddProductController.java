package virtual.catalog.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddProductController implements Initializable {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldPrice;
    @FXML
    private ComboBox<?> comboBoxCategory;
    @FXML
    private TextArea textAreaDescription;
    @FXML
    private Button btnAddImage;
    @FXML
    private Button btnAddProduct;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ButtonAddImage(MouseEvent event) {
    }

    @FXML
    private void ButtonAddProduct(MouseEvent event) {
    }

    void setMainController(MainController aThis) {
        
    }
    
}
