package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Product;
import service.ProductManagerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    @FXML
    TableView<Product> product_table;

    @FXML
    TableColumn<Product, Integer> pidColumn;

    @FXML
    TableColumn<Product, Integer> nameColumn;

    @FXML
    TableColumn<Product, Integer> priceColumn;

    @FXML
    TableColumn<Product, Integer> brandColumn;

    @FXML
    TableColumn<Product, Integer> categoryColumn;

    @FXML
    TableColumn<Product, Integer> qtyColumn;

    @FXML
    Label statusLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));
        loadData();


    }

    public void loadData() {

        ProductManagerService productManagerService = new ProductManagerService();
        ObservableList<Product> products = productManagerService.getProductsList();

        if (products == null) {
            System.out.println("No Products");
        } else {
            product_table.setItems(products);
        }

    }


    public void openManageCategoryBrands(ActionEvent event) throws IOException {

        Parent supplierViewParent = FXMLLoader.load(getClass().getResource("/view/categories_and_brands.fxml"));
        Stage windowstage = (Stage) ((Node) event.getTarget()).getScene().getWindow();

        windowstage.setScene(new Scene(supplierViewParent, 1280, 720));
        windowstage.centerOnScreen();
        windowstage.setTitle("Manage Product Brands and Categories");
        Image icon = new Image(MainController.class.getResource("/res/icons/checklist.png").toExternalForm(), false);
        windowstage.getIcons().add(icon);
    }


}

