package controller;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;
import model.*;
import service.CategoryManagerService;
import service.ProductManagerService;
import service.SupplierManagerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class SupplierController  implements Initializable {
    @FXML
    private TableView<Supplier> supplierTableView;

    @FXML
    private TableView<Contact> tableViewPhone;

    @FXML
    private TableView<Mail> tableViewEmail;

    @FXML
    private TableColumn columnSid;

    @FXML
    private TableColumn columnVendor;

    @FXML
    private TableColumn columnCategory;

    @FXML
    private TableColumn columnAddress;

    @FXML
    private TableColumn columnPhone;

    @FXML
    private TableColumn columnEmail;

    @FXML
    private TextField textFieldSupplierID;

    @FXML
    private TextField textFieldVendor;

    @FXML
    private TextField textFieldAddress;


    @FXML
    private ComboBox comboBoxCategory;

    @FXML
    private TextField textFieldContact;

    @FXML
    private TextField textFieldEmail;

    private SupplierManagerService supplierManagerService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnSid.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnVendor.setCellValueFactory(new PropertyValueFactory<>("vendor"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        textFieldSupplierID.setTextFormatter(
                new TextFormatter<Integer>(new IntegerStringConverter(),null, integerFilter)
        );

        supplierManagerService = new SupplierManagerService();

        loadSuppliers();
        loadCategories();

        supplierTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Supplier supplier = supplierTableView.getSelectionModel().getSelectedItem();
                loadContacts(supplier.getId());
                textFieldSupplierID.setText(String.valueOf(supplier.getId()));
//                textFieldSupplierID.setDisable(true);
                textFieldSupplierID.setEditable(false);
                textFieldVendor.setText(supplier.getVendor());
                textFieldAddress.setText(supplier.getAddress());
                comboBoxCategory.getSelectionModel().select(supplier.getCategory());


            }
        });

        tableViewPhone.getSelectionModel().selectedItemProperty().addListener((observableValue, contact, t1) -> {
            if (t1 != null){
                Contact contact1 = tableViewPhone.getSelectionModel().getSelectedItem();
                textFieldContact.setText(contact1.getPhone());
            }
        });

        tableViewEmail.getSelectionModel().selectedItemProperty().addListener((observableValue, mail, t2) ->{
            if(t2 != null){
                Mail mail1 = tableViewEmail.getSelectionModel().getSelectedItem();
                textFieldEmail.setText(mail1.getEmail());
            }
        } );
    }
    @FXML
    public void clear(){
        clearData();
//        textFieldSupplierID.setDisable(false);
        textFieldSupplierID.setEditable(true);
    }

    @FXML
    public void add(){
        if (valid()){
            int sid =  Integer.parseInt(textFieldSupplierID.getCharacters().toString());
            String vendor = textFieldVendor.getCharacters().toString();
            Category category = (Category) comboBoxCategory.getSelectionModel().getSelectedItem();
            String address = textFieldAddress.getCharacters().toString();

            Supplier supplier = new Supplier(sid,vendor,category,address);
            if (supplierManagerService.addSupplier(supplier)){
                clearData();
                loadSuppliers();
            }else {

                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Supplier ID Already Exists In the System ! Use a Unique Id !", ButtonType.OK);

                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }


        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }

    }

    private void clearData() {
        textFieldSupplierID.clear();
        textFieldVendor.clear();
        comboBoxCategory.getSelectionModel().select(0);
        textFieldAddress.clear();
        textFieldContact.clear();
        textFieldEmail.clear();
    }

    private boolean valid() {

        return !(textFieldVendor.getCharacters().length() == 0
                || textFieldSupplierID.getCharacters().length() == 0
                || textFieldAddress.getCharacters().length() == 0
                || comboBoxCategory.getSelectionModel().getSelectedIndex() == 0
        );
    }

    private boolean validpn(){ return !(textFieldContact.getCharacters().length() == 0);}

    private boolean validem(){ return !(textFieldEmail.getCharacters().length() == 0);}


    private boolean validUpdate() {

        return !(textFieldVendor.getCharacters().length() == 0
                || textFieldAddress.getCharacters().length() == 0
                || comboBoxCategory.getSelectionModel().getSelectedIndex() == 0
        );
    }

    @FXML
    public void delete(){
        Supplier supplier = supplierTableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to Delete this Supplier from System ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            supplierManagerService.removeSupplier(supplier.getId());
            supplierManagerService.removeMail(supplier.getId());
            supplierManagerService.removeContact(supplier.getId());
            loadSuppliers();
            clearData();
        }

    }

    @FXML
    public void update(){
        if (validUpdate()){
            int sid =  Integer.parseInt(textFieldSupplierID.getCharacters().toString());
            String vendor = textFieldVendor.getCharacters().toString();
            Category category = (Category) comboBoxCategory.getSelectionModel().getSelectedItem();
            String address = textFieldAddress.getCharacters().toString();

            Supplier supplier = new Supplier(sid,vendor,category,address);
            if(supplierManagerService.updateSupplier(supplier)) {
                loadSuppliers();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Supplier ID "+ supplier.getId() +" Update Failed !", ButtonType.OK);
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Provide Valid Information !", ButtonType.OK);
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        }
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

    private void loadCategories() {

        CategoryManagerService categoryManagerService = new CategoryManagerService();
        ObservableList<Category> categories = categoryManagerService.getCategoriesList();

        /*This model will work as a prompt text for combo Box
        Java 11 Doesnt properly support prompt text on Non Editable ComboBox
        */
        Category promtText = new Category("Select   Product Category", null);
        comboBoxCategory.getItems().add(0, promtText);
        comboBoxCategory.getSelectionModel().select(0);
        for (Category category : categories) {

            if (category.isActive()) {
                comboBoxCategory.getItems().add(category);
            }
        }

    }

    public void openHomeScenefromSupplier(ActionEvent event) throws IOException {
        Parent homeParent = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
        Stage window = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        window.setScene(new Scene(homeParent, 840, 473));
        window.centerOnScreen();
        window.setTitle("Store Management Nisha Electricals PVC");
        Image icon = new Image(MainController.class.getResource("/res/icons/icon.png").toExternalForm(), true);
        window.getIcons().add(icon);
    }



}
