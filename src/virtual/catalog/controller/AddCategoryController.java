package virtual.catalog.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Pair;
import virtual.catalog.dao.CategoryDAO;
import virtual.catalog.model.Category;

public class AddCategoryController implements Initializable {

    private MainController mainController;
    
    @FXML
    private TextField textFieldName;
    
    @FXML
    private Button btnAddCategory;
    
    @FXML
    private TableView<Category> tableViewCategory;
    
    @FXML
    private TableColumn<Category, Integer> columnID;
    
    @FXML
    private TableColumn<Category, String> columnName;
    
    @FXML
    private TableColumn<Category, Void> columnOptions;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getCategories();
        
        this.columnID.setCellValueFactory(new PropertyValueFactory("id"));
        this.columnName.setCellValueFactory(new PropertyValueFactory("name"));
        
        if (categories != null) {
            tableViewCategory.setItems(FXCollections.observableArrayList(categories));
            addButtonToTable();
        }
    }

    @FXML
    private void ButtonAddCategory(MouseEvent event) {
        CategoryDAO categoryDAO = new CategoryDAO();
        
        if (!textFieldName.getText().isEmpty()) {
            String name = textFieldName.getText();
            
            categoryDAO.add(name);
            
            textFieldName.setText("");
            
            List<Category> categories = categoryDAO.getCategories();
            
            tableViewCategory.setItems(FXCollections.observableArrayList(categories));
            addButtonToTable();
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Successfully added category");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("You have to write a name for add a new category");
            alert.showAndWait();
        }
    }
    
    private void addButtonToTable() {
        Callback<TableColumn<Category, Void>, TableCell<Category, Void>> cellFactory = (TableColumn<Category, Void> p) -> {
            final TableCell<Category, Void> cell = new TableCell<>() {
                private final Button btnEdit = new Button("Edit");
                private final Button btnDelete = new Button("Delete");
                
                {
                    btnEdit.setOnMouseClicked((MouseEvent event) -> {
                        Category category = getTableView().getItems().get(getIndex());
                        
                        Dialog<ButtonType> dialogEdit = new Dialog<>();
                        
                        dialogEdit.setTitle("Edit category");
                        
                        dialogEdit.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
                        
                        Label label = new Label("Editing name of the category: " + category.getName());
                        
                        TextField textFieldNewName = new TextField();
                        textFieldNewName.setPromptText("New name");
                        
                        VBox vBox = new VBox(label, textFieldNewName);
                        dialogEdit.getDialogPane().setContent(vBox);
                        
                        Optional<ButtonType> result = dialogEdit.showAndWait();
                        
                        if (result.isPresent() && ButtonType.OK == result.get() && !textFieldNewName.getText().isEmpty()) {
                            Alert alertConfirmation = new Alert(AlertType.CONFIRMATION);
                            alertConfirmation.setTitle("Confirmation");
                            alertConfirmation.setHeaderText(null);
                            alertConfirmation.setContentText("Are you sure you want to edited this category: " +
                                category.getName());
                            
                            Optional<ButtonType> confirmation = alertConfirmation.showAndWait();
                            
                            if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
                                CategoryDAO categoryDAO = new CategoryDAO();
                                categoryDAO.update(category.getId(), textFieldNewName.getText());
                                
                                System.out.println("Action perfom");
                                
                                List<Category> categories = categoryDAO.getCategories();
                                
                                tableViewCategory.setItems(FXCollections.observableArrayList(categories));
                                addButtonToTable();
                                
                                Alert alertInformation = new Alert(AlertType.INFORMATION);
                                alertInformation.setTitle("Information");
                                alertInformation.setHeaderText(null);
                                alertInformation.setContentText("Successfully updated category");
                                alertInformation.showAndWait();
                                
                                dialogEdit.close();
                            } else {
                                System.err.println("Action cancelled");
                            }
                        } else {
                            Alert alert = new Alert(AlertType.WARNING);
                            
                            alert.setTitle("Warning");
                            alert.setHeaderText(null);
                            alert.setContentText("You have to write a name for update category");
                            alert.showAndWait();
                        }
                    });
                    
                    btnDelete.setOnMouseClicked((MouseEvent event) -> {
                        Category category = getTableView().getItems().get(getIndex());
                        
                        Alert alertConfirmation = new Alert(AlertType.CONFIRMATION);
                        alertConfirmation.setTitle("Confirmation");
                        alertConfirmation.setHeaderText(null);
                        alertConfirmation.setContentText("Are you sure you want to remove this category: " +
                            category.getName());
                        
                        Optional<ButtonType> result = alertConfirmation.showAndWait();
                        
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            CategoryDAO categoryDAO = new CategoryDAO();
                            categoryDAO.delete(category.getId());
                            
                            System.err.println("Action perform");
                            
                            List<Category> categories = categoryDAO.getCategories();
            
                            tableViewCategory.setItems(FXCollections.observableArrayList(categories));
                            addButtonToTable();
                            
                            Alert alertInformation = new Alert(AlertType.INFORMATION);
                            alertInformation.setTitle("Information");
                            alertInformation.setHeaderText(null);
                            alertInformation.setContentText("Successfully removed category");
                            alertInformation.showAndWait();
                        } else {
                            System.err.println("Action cancelled");
                        }
                    });
                }
                
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox hBox = new HBox(btnEdit, btnDelete);
                        hBox.setSpacing(10);
                        hBox.setAlignment(Pos.CENTER);
                        setGraphic(hBox);
                    }
                }
            };
            
            return cell;
        };
        
        columnOptions.setCellFactory(cellFactory);
    }

    void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
}
