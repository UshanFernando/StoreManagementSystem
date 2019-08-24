package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Brand;
import model.Category;
import service.BrandManagerService;
import service.CategoryManagerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoriesAndBrandsController implements Initializable {

    @FXML
    TableView<Brand> brandTable;

    @FXML
    TableView<Category> categoryTable;

    @FXML
    TableColumn bidColumn;

    @FXML
    TableColumn bnameColumn;

    @FXML
    TableColumn bstatusColumn;

    @FXML
    TableColumn cidColumn;

    @FXML
    TableColumn cnameColumn;

    @FXML
    TableColumn cstatusColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        bstatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        cidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cstatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadBrands();
        loadCategories();

    }

    private void loadBrands() {

        BrandManagerService brandManagerService = new BrandManagerService();
        ObservableList<Brand> brands = brandManagerService.getBrandsList();

        if (brands == null) {
            System.out.println("No Brands");
        } else brandTable.setItems(brands);
    }

    private void loadCategories() {
        CategoryManagerService categoryManagerService = new CategoryManagerService();
        ObservableList<Category> categories = categoryManagerService.getCategoriesList();

        if (categories == null) {
            System.out.println("No Categories");
        } else categoryTable.setItems(categories);
    }

    public void openInventory(ActionEvent event) throws IOException {

        Parent invenetoryViewParent = FXMLLoader.load(getClass().getResource("/view/inventory.fxml"));

        Stage window = (Stage) ((Node) event.getTarget()).getScene().getWindow();

        window.setScene(new Scene(invenetoryViewParent, 1280, 720));
        window.centerOnScreen();
        window.setTitle("Inventory Management");
        Image icon = new Image(MainController.class.getResource("/res/icons/warehouse.png").toExternalForm(), false);
        window.getIcons().add(icon);
    }
}
