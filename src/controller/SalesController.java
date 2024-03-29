package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import model.*;
import service.BrandManagerService;
import service.CategoryManagerService;
import service.ProductManagerService;
import util.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

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
    private Label dateLbl;

    @FXML
    private Label timeLbl;

    @FXML
    private Label dayLbl;

    @FXML
    private TableView<ShoppingCartItem> purchasedTable;

    @FXML
    private TableColumn productDiscountColumn;

    @FXML
    private TableColumn productColumn;

    @FXML
    private TableColumn productPriceColumn;

    @FXML
    private TableColumn productQtyColumn;

    @FXML
    private TextField qtyTF;

    @FXML
    private TextField discountTF;

    @FXML
    private Label subTotalLbl;

    @FXML
    private Label discountLbl;

    @FXML
    private Label netTotalLbl;

    @FXML
    private Button addBtn;

    @FXML
    private TextField pidTF;

    @FXML
    private TextField nameTF;

    @FXML
    private ComboBox categoryCB;

    @FXML
    private ComboBox brandCB;


    private ShoppingCart shoppingCart;

    private boolean saleActive = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initClock();

        pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));


        productColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productQtyColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productDiscountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));

        addBtn.setText("Create a New Sale to Continue!");

        loadData();
        disbleAddBtn();
        loadBrands();
        loadCategories();

        UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*|\\d+\\.\\d*")) {
                return change;
            }
            return null;
        };
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };


            discountTF.setTextFormatter(
                new TextFormatter<Integer>(new IntegerStringConverter(), null, doubleFilter));

            qtyTF.setTextFormatter(
                new TextFormatter<Integer>(new IntegerStringConverter(), null, integerFilter));

    }

    private void loadData() {

        ProductManagerService productManagerService = new ProductManagerService();
        ObservableList<Product> products = productManagerService.getProductsList();

        if (products == null) {
            System.out.println("No Products");
        } else {
            product_table.setItems(products);
        }
        FilteredList<Product> filteredData = new FilteredList<>(products, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        nameTF.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.

                }

                return false; // Does not match.
            });
        });

        // 2. Set the filter Predicate whenever the filter changes.
        pidTF.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getPid()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.

                }

                return false; // Does not match.
            });
        });


        // 2. Set the filter Predicate whenever the filter changes.
        brandCB.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.toString().isEmpty() || newValue.toString().equals("Select Product Brand")) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toString().toLowerCase();


                if (String.valueOf(myObject.getBrand().getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.

                }

                return false; // Does not match.
            });
        });

        categoryCB.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.toString().isEmpty() || newValue.toString().equals("Select Product Category")) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toString().toLowerCase();


                if (String.valueOf(myObject.getCategory().getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.

                }

                return false; // Does not match.
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Product> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(product_table.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        product_table.setItems(sortedData);

    }

    @FXML
    public void newSale() {

        if (saleActive) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Complete Current Sale or Reset to Make a new Sale", ButtonType.OK);
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.initOwner(Main.getPrimaryStage());
            alert.showAndWait();
        } else {

            if (shoppingCart == null) {
                shoppingCart = new ShoppingCart();
            } else {
                shoppingCart.clear();
            }

            purchasedTable.getItems().clear();
            saleActive = true;
            addBtn.setText("Add Item to the List");
            enableAddBtn();
        }


    }

    @FXML
    public void addToCart() {

        if (!(qtyTF.getCharacters().length() == 0)) {
            Product product = product_table.getSelectionModel().getSelectedItem();

            int qty = Integer.parseInt(qtyTF.getCharacters().toString());
            double discount;

            if (discountTF.getCharacters().length() == 0) {
                discount = 0;
            } else {
                discount = Double.parseDouble(discountTF.getCharacters().toString());
            }
            ShoppingCartItem item = new ShoppingCartItem(product, qty, discount);

            shoppingCart.addItem(item);
            updateData();
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Quantity !", ButtonType.OK);
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.initOwner(Main.getPrimaryStage());
            alert.showAndWait();

        }
    }

    private void loadBrands() {

        BrandManagerService brandManagerService = new BrandManagerService();
        ObservableList<Brand> brands = brandManagerService.getBrandsList();

        /*This model will work as a prompt text for combo Box
        Java 11 Doesnt properly support  prompt text on Non Editable ComboBox.
        */
        Brand promtText = new Brand("Select Product Brand", null);
        brandCB.getItems().add(0, promtText);
        brandCB.getSelectionModel().select(0);

        for (Brand brand : brands) {
            brandCB.getItems().add(brand);

        }

    }

    private void loadCategories() {

        CategoryManagerService categoryManagerService = new CategoryManagerService();
        ObservableList<Category> categories = categoryManagerService.getCategoriesList();

        /*This model will work as a prompt text for combo Box
        Java 11 Doesnt properly support prompt text on Non Editable ComboBox
        */
        Category promtText = new Category("Select Product Category", null);
        categoryCB.getItems().add(0, promtText);
        categoryCB.getSelectionModel().select(0);
        for (Category category : categories) {

            categoryCB.getItems().add(category);

        }

    }

    private void updateData() {

        ObservableList<ShoppingCartItem> items = FXCollections.observableArrayList(shoppingCart.getItems());
        purchasedTable.setItems(items);
        subTotalLbl.setText(String.valueOf(shoppingCart.getSubTotal()));
        discountLbl.setText(String.valueOf(shoppingCart.getDiscount()));
        netTotalLbl.setText(String.valueOf(shoppingCart.getNetTotal()));
        qtyTF.clear();
        discountTF.clear();

    }

    @FXML
    public void checkout() throws IOException {

        if (saleActive && !(shoppingCart.getItems().isEmpty())) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/checkout_sales.fxml"));

            Parent viewParent = loader.load();
            Stage popupStage = new Stage();

            CheckoutController controller =
                    loader.<CheckoutController>getController();
            controller.checkout(shoppingCart);

            popupStage.setScene(new Scene(viewParent, 300, 450));
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.initStyle(StageStyle.TRANSPARENT);
            popupStage.initOwner(Main.getPrimaryStage());

            popupStage.show();

                reset();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Create a sale to checkout !", ButtonType.OK);
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.initOwner(Main.getPrimaryStage());
            alert.showAndWait();
        }
    }



    @FXML
    public void openStats() throws IOException {

        SceneManager.manage().openSalesReportScene();
    }

    @FXML
    public  void reset() {
        saleActive = false;
        netTotalLbl.setText("000000.00");
        discountLbl.setText("000000.00");
        subTotalLbl.setText("000000.00");
        disbleAddBtn();
        purchasedTable.getItems().clear();
        addBtn.setText("Create a New Sale to Continue!");
    }


    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            int second = LocalDateTime.now().getSecond();
            int minute = LocalDateTime.now().getMinute();
            int hour = LocalDateTime.now().getHour();
            String day = LocalDateTime.now().getDayOfWeek().name();
            long year = LocalDateTime.now().getYear();
            String month = LocalDateTime.now().getMonth().name();
            int dayValue = LocalDateTime.now().getDayOfMonth();
            timeLbl.setText(hour + ":" + (minute) + ":" + second);
            dayLbl.setText(day);
            dateLbl.setText(dayValue + " " + month + " " + year);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void disbleAddBtn() {

        addBtn.setDisable(true);

    }

    private void enableAddBtn() {

        addBtn.setDisable(false);

    }

    @FXML
    public void recentSales() throws IOException {

        Parent viewParent = FXMLLoader.load(getClass().getResource("/view/recentSales.fxml"));
        Stage popupStage = new Stage();

        popupStage.setScene(new Scene(viewParent, 840, 473));
        popupStage.setTitle("Recent Sales");
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(Main.getPrimaryStage());

        popupStage.show();

    }


    @FXML
    public void openHomeScene() throws IOException {
        SceneManager.manage().openSalesScene();
    }

    @FXML
    public void openHomeScene(ActionEvent event) throws IOException {

        Parent viewParent = FXMLLoader.load(getClass().getResource("/view/home.fxml"));

        Stage windowstage = (Stage) ((Node) event.getTarget()).getScene().getWindow();

        windowstage.setScene(new Scene(viewParent, 840, 473));
        windowstage.centerOnScreen();
        windowstage.setTitle("Store Management Nisha Electricals PVC");
        Image icon = new Image(MainController.class.getResource("/res/icons/icon.png").toExternalForm(), false);
        windowstage.getIcons().add(icon);

    }
}

