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
import javafx.util.converter.IntegerStringConverter;
import model.Order;
import model.OrderItem;
import model.Supplier;
import service.OrderManagerService;

import java.io.IOException;
import java.net.URL;
import java.time.temporal.TemporalAccessor;
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
                textFieldOrderId.setDisable(true);
                textFieldQty.setText(orderItem.getQty());


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

        comboBoxVendor.getItems().add(0,"Load Supplier");

    }

    private void loadProducts(){
        comboBoxProduct.getItems().add(0,"Load Products");
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

}
