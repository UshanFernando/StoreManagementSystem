package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Product;
import service.ProductManagerService;

import java.net.URL;
import java.time.LocalDateTime;
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

    @FXML
    private Label dateLbl;

    @FXML
    private Label timeLbl;

    @FXML
    private Label dayLbl;

    @FXML
    private TableColumn productColumn;

    @FXML
    private TableColumn productPriceColumn;

    @FXML
    private TableColumn productQtyColumn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initClock();

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

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            int second = LocalDateTime.now().getSecond();
            int   minute = LocalDateTime.now().getMinute();
            int   hour = LocalDateTime.now().getHour();
            String day = LocalDateTime.now().getDayOfWeek().name();
            long year = LocalDateTime.now().getYear();
            String month = LocalDateTime.now().getMonth().name();
            int dayValue = LocalDateTime.now().getDayOfMonth();
            timeLbl.setText(hour + ":" + (minute) + ":" + second);
            dayLbl.setText(day);
            dateLbl.setText(dayValue+" "+month+" "+year);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }



}

