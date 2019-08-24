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

public class SalesController implements Initializable {

    @FXML
   private TableView<Product> product_table;

    @FXML
    private TableColumn<Product, Integer> pidColumn;

    @FXML
    private TableColumn<Product, Integer> nameColumn;

    @FXML
    private TableColumn<Product, Integer> priceColumn;

    @FXML
    private TableColumn<Product, Integer> brandColumn;

    @FXML
    private TableColumn<Product, Integer> categoryColumn;

    @FXML
    private TableColumn<Product, Integer> qtyColumn;

    @FXML
    private Label statusLbl;

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

    private void loadData() {

        ProductManagerService productManagerService = new ProductManagerService();
        ObservableList<Product> products = productManagerService.getProductsList();

        if (products == null) {
            System.out.println("No Products");
        } else {
            product_table.setItems(products);
        }

    }



}

