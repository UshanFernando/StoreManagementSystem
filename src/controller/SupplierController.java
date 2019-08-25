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
import model.Supplier;
import service.SupplierManagerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierController  implements Initializable {
    @FXML
    TableView<Supplier> supplierTableView;

    @FXML
    TableColumn columnSid;

    @FXML
    TableColumn columnVendor;

    @FXML
    TableColumn columnCategory;

    @FXML
    TableColumn columnAddress;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnSid.setCellValueFactory(new PropertyValueFactory<>("sId"));
        columnVendor.setCellValueFactory(new PropertyValueFactory<>("vendor"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));


    }

    private void loadSuppliers(){
        SupplierManagerService supplierManagerService = new SupplierManagerService();
        ObservableList<Supplier>  suppliers = new SupplierManagerService().getSupplierList();

        if (suppliers == null){
            System.out.println("No Suppliers");
        }else
        {
            supplierTableView.setItems(suppliers);
        }
    }


    public void openHomeScenefromSupplier(ActionEvent event) throws IOException {
        Parent homeViewParent = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
        Stage window = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        window.setScene(new Scene(homeViewParent, 840, 473));
        window.centerOnScreen();
        window.setTitle("Store Management Nisha Electricals PVC");
        Image icon = new Image(MainController.class.getResource("/res/icons/icon.png").toExternalForm(), false);
        window.getIcons().add(icon);
    }



}
