package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Order;
import service.OrderManagerService;

import java.io.IOException;
import java.net.URL;
import java.time.temporal.TemporalAccessor;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    TableView<Order> orderTableView;

    @FXML
    TableColumn columnOrderId;

    @FXML
    TableColumn columnOrderProduct;

    @FXML
    TableColumn columnOrderQty;

    @FXML
    TableColumn columnVendor;

    @FXML
    TableColumn columnOrderDate;

    @FXML
    TableColumn columnDeliveryDate;

    @FXML
    TableColumn columnETA;

    @FXML
    TableColumn columnStatus;

    @FXML
    TextField textFieldOrderId;

    @FXML
    ComboBox comboBoxProduct;

    @FXML
    TextField textFieldQty;

    private OrderManagerService orderManagerService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       columnOrderId.setCellValueFactory(new PropertyValueFactory<>("oid"));
        columnOrderProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
        columnOrderQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        columnVendor.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        columnOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        columnDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        columnETA.setCellValueFactory(new PropertyValueFactory<>("eta"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("requests"));


        orderManagerService = new OrderManagerService();

        loadOrders();

        orderTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                Order order = orderTableView.getSelectionModel().getSelectedItem();

                textFieldOrderId.setText(String.valueOf(order.getOid()));
                textFieldOrderId.setDisable(true);
                textFieldQty.setText(order.getQty());


            }
        });
    }

    private  void loadOrders(){
        ObservableList<Order> orders = orderManagerService.getOrderList();

        if (orders == null){
            System.out.println("No Orders");
        }else {
            orderTableView.setItems(orders);
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

}
