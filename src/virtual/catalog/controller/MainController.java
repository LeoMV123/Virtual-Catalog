/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package virtual.catalog.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author burto
 */
public class MainController implements Initializable {

    @FXML
    private Button btnAddCategory;
    @FXML
    private Button btnAddProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ButtonAddCategory(MouseEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getRoot().setDisable(true);
        
        Parent root = FXMLLoader.load(getClass().getResource("../view/AddCategory.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setTitle("Add category");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ButtonAddProduct(MouseEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getRoot().setDisable(true);
        
        Parent root = FXMLLoader.load(getClass().getResource("../view/AddProduct.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setTitle("Add product");
        stage.setScene(scene);
        stage.show();
    }
    
}
