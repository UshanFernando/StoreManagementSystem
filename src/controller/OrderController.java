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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;
import model.*;
import service.NotificationService;
import service.OrderManagerService;
import service.ProductManagerService;
import service.SupplierManagerService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class OrderController implements Initializable {

    @FXML
    private TableView<OrderItem> orderTableView;

    @FXML
    private TableColumn columnOrderId;

    @FXML
    private TableColumn columnOrderProduct;

    @FXML
    private TableColumn columnOrderQty;

    @FXML
    private TableColumn columnVendor;

    @FXML
    private TableColumn columnOrderDate;

    @FXML
    private TableColumn columnDeliveryDate;

    @FXML
    private TableColumn columnETA;

    @FXML
    private TableColumn columnStatus;

    @FXML
    private TextField textFieldOrderId;

    @FXML
    private ComboBox comboBoxProduct;

    @FXML
    private ComboBox comboBoxVendor;

    @FXML
    private TextField textFieldQty;

    @FXML
    private DatePicker dataPickerOrderDate;

    @FXML
    private DatePicker datePickerArrivalDate;

    @FXML
    private TextField textFieldStatus;

    private OrderManagerService orderManagerService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnOrderId.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        columnOrderProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        columnOrderQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        columnVendor.setCellValueFactory(new PropertyValueFactory<>("vendor"));
        columnOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        columnDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        columnETA.setCellValueFactory(new PropertyValueFactory<>("eta"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("request"));

        loadNotifications();
        loadProducts();
        loadSuppliers();

        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };


        textFieldOrderId.setTextFormatter(
                new TextFormatter<Integer>(new IntegerStringConverter(),null, integerFilter)
        );
        textFieldQty.setTextFormatter(
                new TextFormatter<Integer>(new IntegerStringConverter(),null, integerFilter)
        );

        orderManagerService = new OrderManagerService();

        loadOrders();

        orderTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                OrderItem orderItem = orderTableView.getSelectionModel().getSelectedItem();

                textFieldOrderId.setText(String.valueOf(orderItem.getOrderID()));
//                textFieldOrderId.setDisable(true);
                textFieldOrderId.setEditable(false);
                textFieldQty.setText(orderItem.getQty());
                textFieldStatus.setText(orderItem.getRequest());



            }
        });
    }

    private void loadOrders() {
        ObservableList<OrderItem> orders = orderManagerService.getOrderList();

        if (orders == null) {
            System.out.println("No Orders");
        } else {
            orderTableView.setItems(orders);
        }
    }

    private void loadSuppliers(){
        SupplierManagerService supplierManagerService =  new SupplierManagerService();
        ObservableList<Supplier> suppliers = supplierManagerService.getSupplierList();

        comboBoxVendor.getItems().add(0,"Select Supplier");
        comboBoxVendor.getSelectionModel().select(0);

        for (Supplier supplier: suppliers){
            comboBoxVendor.getItems().add(supplier);
        }

    }

    private void loadProducts(){
        ProductManagerService productManagerService = new ProductManagerService();
        ObservableList<Product> products = productManagerService.getProductsList();
        comboBoxProduct.getItems().add(0,"Select Products");
        comboBoxProduct.getSelectionModel().select(0);

        for (Product product: products){
            comboBoxProduct.getItems().add(product);
        }
    }

    private void loadNotifications(){

        ObservableList<Notification> notifications ;


        NotificationService notificationService = new NotificationService();
        notifications =  notificationService.getUnreadNotifications();

        if (notifications!= null && notifications.size()>0){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "They are new Notifications , Do You Want to View Them ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                try {
                    Parent viewParent = FXMLLoader.load(getClass().getResource("/view/notifications.fxml"));
                    Stage popupStage = new Stage();

                    popupStage.setScene(new Scene(viewParent, 840, 473));
                    popupStage.setTitle("Recent Sales");
                    popupStage.initModality(Modality.WINDOW_MODAL);
                    popupStage.initOwner(Main.getPrimaryStage());

                    popupStage.show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    public void openHomeScenefromOrder(ActionEvent event) throws IOException {
        Parent homeViewParent = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
        Stage window = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        window.setScene(new Scene(homeViewParent, 840, 473));
        window.centerOnScreen();
        window.setTitle("Store Management Nisha Electricals PVC");
        Image icon = new Image(MainController.class.getResource("/res/icons/icon.png").toExternalForm(), false);
        window.getIcons().add(icon);
    }

    @FXML
    public void clear() {
        clearData();
        textFieldOrderId.setEditable(true);
    }

    private void clearData() {
        textFieldOrderId.clear();
        comboBoxVendor.getSelectionModel().select(0);
        comboBoxProduct.getSelectionModel().select(0);
    }

    @FXML
    public void add() {
        if(valid()){
            int oid = Integer.parseInt(textFieldOrderId.getCharacters().toString());
            Product product = (Product) comboBoxProduct.getSelectionModel().getSelectedItem();
            String qty = textFieldQty.getCharacters().toString();
            Supplier supplier = (Supplier) comboBoxVendor.getSelectionModel().getSelectedItem();
            String status = textFieldStatus.getCharacters().toString();
            Order order = new Order(oid,product,qty,supplier,"24-09-2019","25-10-2019",status);
            OrderItem orderItem = new OrderItem(order,product,supplier);

            if (orderManagerService.addOrder(orderItem)){
                clearData();
                loadOrders();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Order Already Exists In the System ! Use a Unique Id !", ButtonType.OK);

                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }

        }else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }
    }

    @FXML
    public void update() {

        if (validUpdate()){
            int oid = Integer.parseInt(textFieldOrderId.getCharacters().toString());
            Product product = (Product) comboBoxProduct.getSelectionModel().getSelectedItem();
            String qty = textFieldQty.getCharacters().toString();
            Supplier supplier = (Supplier) comboBoxVendor.getSelectionModel().getSelectedItem();
            String status = textFieldStatus.getCharacters().toString();
            Order order = new Order(oid,product,qty,supplier,"24-09-2019","30-10-2019",status);
            OrderItem orderItem = new OrderItem(order,product,supplier);
            if (orderManagerService.updateOrder(orderItem)){
                clearData();
                loadOrders();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Order ID "+ order.getOid() +" Update Failed !", ButtonType.OK);

                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }

        }else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }

    }

    private boolean validUpdate() {
        return !(textFieldQty.getCharacters().length() == 0 ||
                textFieldStatus.getCharacters().length()==0 ||
                comboBoxProduct.getSelectionModel().getSelectedIndex() == 0||
                comboBoxVendor.getSelectionModel().getSelectedIndex() == 0);
    }


    private boolean valid(){
        return !(textFieldOrderId.getCharacters().length() == 0||
                textFieldQty.getCharacters().length()==0||
                comboBoxProduct.getSelectionModel().getSelectedIndex() ==0||
                comboBoxVendor.getSelectionModel().getSelectedIndex() == 0||
                textFieldStatus.getCharacters().length() == 0 );
    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @FXML
    public void delete() {
    }
}
