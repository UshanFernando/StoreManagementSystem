package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;
import model.Sale;
import service.SaleManagerService;

import java.net.URL;
import java.util.ResourceBundle;

public class RecentSalesController implements Initializable {

    @FXML
    private TableView salesTable;

    @FXML
    private TableColumn sidColumn;

    @FXML
    private TableColumn subTotalColumn;

    @FXML
    private TableColumn discountColumn;

    @FXML
    private TableColumn netTotalColumn;

    @FXML
    private TableColumn paymentColumn;

    @FXML
    private TableColumn timeStampColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        subTotalColumn.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
        netTotalColumn.setCellValueFactory(new PropertyValueFactory<>("netTotal"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));
        timeStampColumn.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));

        loadData();
    }

    private void loadData() {

        SaleManagerService saleManagerService = new SaleManagerService();

        ObservableList<Sale> sales = saleManagerService.getSalesList();

        if (sales == null) {
            System.out.println("No Products");
        } else {
            salesTable.setItems(sales);
        }


    }

}
