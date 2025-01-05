package virtual.catalog.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import virtual.catalog.dao.CategoryDAO;
import virtual.catalog.model.Category;

public class MainController implements Initializable {

    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnCategory;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void ButtonAddCategory(MouseEvent event) throws IOException {
        // Parent root = FXMLLoader.load(getClass().getResource("../view/AddCategory.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AddCategory.fxml"));
        Parent root = loader.load();
        
        AddCategoryController controller = loader.getController();
        controller.setMainController(this);
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setTitle("Add category");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void ButtonAddProduct(MouseEvent event) throws IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getCategories();
        
        if (categories.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("You can't add a product because you\n" +
                    "don't have any categories registred");
            alert.showAndWait();    
        } else {
            // Parent root = FXMLLoader.load(getClass().getResource("../view/AddProduct.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AddProduct.fxml"));
            Parent root = loader.load();

            AddProductController controller = loader.getController();
            // controller.setMainController(this);

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setTitle("Add product");
            stage.setScene(scene);
            stage.showAndWait();
        }
    }
    
}
