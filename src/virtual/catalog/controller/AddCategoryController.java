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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
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
            
            categoryDAO.addCategory(name);
            
            textFieldName.setText("");
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Successfully added category");
            alert.showAndWait();
            
            List<Category> categories = categoryDAO.getCategories();
            
            tableViewCategory.setItems(FXCollections.observableArrayList(categories));
            addButtonToTable();
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
                        System.out.println("Edit clicked for: " + category.getName());
                    });
                    
                    btnDelete.setOnMouseClicked((MouseEvent event) -> {
                        Category category = getTableView().getItems().get(getIndex());
                        
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure you want to remove this category: " +
                                category.getName());
                        
                        Optional<ButtonType> result = alert.showAndWait();
                        
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            CategoryDAO categoryDAO = new CategoryDAO();
                            categoryDAO.deleteCategory(category.getId());
                            
                            System.err.println("Action perform");
                            
                            List<Category> categories = categoryDAO.getCategories();
            
                            tableViewCategory.setItems(FXCollections.observableArrayList(categories));
                            addButtonToTable();
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
