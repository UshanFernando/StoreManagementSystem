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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Contact;
import model.Mail;
import model.Supplier;
import service.SupplierManagerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierController  implements Initializable {
    @FXML
    TableView<Supplier> supplierTableView;

    @FXML
    TableView<Contact> tableViewPhone;

    @FXML
    TableView<Mail> tableViewEmail;

    @FXML
    TableColumn columnSid;

    @FXML
    TableColumn columnVendor;

    @FXML
    TableColumn columnCategory;

    @FXML
    TableColumn columnAddress;

    @FXML
    TableColumn columnPhone;

    @FXML
    TableColumn columnEmail;

    @FXML
    TextField textFieldSupplierID;

    @FXML
    TextField textFieldVendor;

    @FXML
    TextField textFieldAddress;

    @FXML
    TextField textFieldContact;


    private SupplierManagerService supplierManagerService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnSid.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnVendor.setCellValueFactory(new PropertyValueFactory<>("vendor"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        supplierManagerService = new SupplierManagerService();

        loadSuppliers();

        supplierTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Supplier supplier = supplierTableView.getSelectionModel().getSelectedItem();
                loadContacts(supplier.getId());
                textFieldSupplierID.setText(String.valueOf(supplier.getId()));
                textFieldSupplierID.setDisable(true);
                textFieldVendor.setText(supplier.getVendor());
                textFieldAddress.setText(supplier.getAddress());


            }
        });
    }

    private void loadSuppliers(){

        ObservableList<Supplier>  suppliers = supplierManagerService.getSupplierList();

        if (suppliers == null){
            System.out.println("No Suppliers");
        }else
        {
            supplierTableView.setItems(suppliers);
        }
    }

    private void loadContacts(int sid){

        ObservableList<Contact>  contacts = supplierManagerService.getContactListByID(sid);
        ObservableList<Mail>  emails = supplierManagerService.getEmailListByID(sid);
        if (contacts == null || emails == null){
            System.out.println("No Contacts or Emails");
        }else
        {
            tableViewPhone.setItems(contacts);
            tableViewEmail.setItems(emails);
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
