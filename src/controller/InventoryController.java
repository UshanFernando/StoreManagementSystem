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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Brand;
import model.Category;
import model.Notification;
import model.Product;
import service.BrandManagerService;
import service.CategoryManagerService;
import service.NotificationService;
import service.ProductManagerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class InventoryController implements Initializable {

    @FXML
    TableView<Product> product_table;

    @FXML
    TableColumn<Product, Integer> pidColumn;

    @FXML
    TableColumn<Product, Integer> nameColumn;

    @FXML
    TableColumn<Product, Integer> priceColumn;

    @FXML
    TableColumn<Product, Brand> brandColumn;

    @FXML
    TableColumn<Product, Category> categoryColumn;

    @FXML
    TableColumn<Product, Integer> qtyColumn;

    @FXML
    Label statusLbl;

    @FXML
    TextField pidTF;

    @FXML
    ComboBox<Brand> brandCB;

    @FXML
    ComboBox<Category> categoryCB;

    @FXML
    TextField nameTF;

    @FXML
    TextField priceTF;

    @FXML
    TextField qtyTF;

    @FXML
    TextField criticalQty;

    private ProductManagerService productManagerService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));

        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*|\\d+\\.\\d*")) {
                return change;
            }
            return null;
        };


        pidTF.setTextFormatter(
                new TextFormatter<Integer>(new IntegerStringConverter(), null, integerFilter));
        priceTF.setTextFormatter(
                new TextFormatter<Double>(new DoubleStringConverter(), null, doubleFilter));
        qtyTF.setTextFormatter(
                new TextFormatter<Integer>(new IntegerStringConverter(), null, integerFilter));
        criticalQty.setTextFormatter(
                new TextFormatter<Integer>(new IntegerStringConverter(), null, integerFilter));

        productManagerService = new ProductManagerService();

        loadData();
        loadBrands();
        loadCategories();

        product_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                Product product = product_table.getSelectionModel().getSelectedItem();

                pidTF.setText(String.valueOf(product.getPid()));
                nameTF.setText(product.getName());
                priceTF.setText(String.valueOf(product.getPrice()));
                brandCB.getSelectionModel().select(product.getBrand());
                categoryCB.getSelectionModel().select(product.getCategory());
                qtyTF.setText(String.valueOf(product.getQty()));
                criticalQty.setText(String.valueOf(product.getCriticalQty()));
            }
        });



    }

    private void loadData() {


        ObservableList<Product> products = productManagerService.getProductsList();

        if (products == null) {
            System.out.println("No Products");
        } else {
            product_table.setItems(products);
        }

        loadNotifications();

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

            if (brand.isActive())
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

            if (category.isActive()) {
                categoryCB.getItems().add(category);
            }
        }

    }

    @FXML
    public void add() {

        if (valid()) {

            int pid = Integer.parseInt(pidTF.getCharacters().toString());
            String name = nameTF.getCharacters().toString();
            Double price = Double.parseDouble(priceTF.getCharacters().toString());
            Brand brand = brandCB.getSelectionModel().getSelectedItem();
            Category category = categoryCB.getSelectionModel().getSelectedItem();
            int qty = Integer.parseInt(qtyTF.getCharacters().toString());
            int critQty = Integer.parseInt(criticalQty.getCharacters().toString());


            Product product = new Product(pid, name, price, brand, category, qty,critQty);
            if (productManagerService.addProduct(product)) {
                clearData();
                loadData();
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Product ID Already Exists In the System ! Use a Unique Id !", ButtonType.OK);

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

    @FXML
    private void delete() {

        Product product = product_table.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure You Want to delete this Product from System ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            productManagerService.removeProduct(product.getPid());
            loadData();
            clearData();
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
                     popupStage.setTitle("Notifications");
                     popupStage.initModality(Modality.WINDOW_MODAL);
                     popupStage.initStyle(StageStyle.TRANSPARENT);
                     popupStage.initOwner(Main.getPrimaryStage());

                     popupStage.show();
                 }catch (Exception e){
                     e.printStackTrace();
                 }
             }
         }

    }

    @FXML
    public void update() {

        if (valid()) {

            int pid = Integer.parseInt(pidTF.getCharacters().toString());
            String name = nameTF.getCharacters().toString();
            Double price = Double.parseDouble(priceTF.getCharacters().toString());
            Brand brand = brandCB.getSelectionModel().getSelectedItem();
            Category category = categoryCB.getSelectionModel().getSelectedItem();
            int qty = Integer.parseInt(qtyTF.getCharacters().toString());
            int critQty = Integer.parseInt(criticalQty.getCharacters().toString());

            if (!product_table.getSelectionModel().isEmpty()){

                int  pidSelected = product_table.getSelectionModel().getSelectedItem().getPid();

                Product product = new Product(pid, name, price, brand, category, qty ,critQty);
                if (productManagerService.updateProduct(product,pidSelected)) {
                    clearData();
                    loadData();
                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR,
                            "Product ID Already Exists In the System ! Use a Unique Id !", ButtonType.OK);

                    alert.initStyle(StageStyle.TRANSPARENT);
                    alert.showAndWait();
                }
            }else {

                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Please Select a Product To Update!", ButtonType.OK);

                alert.initStyle(StageStyle.TRANSPARENT);
                alert.showAndWait();
            }




        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }


    }

    @FXML
    public void clear(){
        clearData();
    }


    private void clearData() {

        nameTF.clear();
        priceTF.clear();
        qtyTF.clear();
        pidTF.clear();
        brandCB.getSelectionModel().select(0);
        categoryCB.getSelectionModel().select(0);
        criticalQty.clear();


    }

    private boolean valid() {

        return !((nameTF.getCharacters().length() == 0)
                || (pidTF.getCharacters().length() == 0)
                || (priceTF.getCharacters().length() == 0)
                || (qtyTF.getCharacters().length() == 0)
                || (criticalQty.getCharacters().length() == 0)
                || (brandCB.getSelectionModel().getSelectedIndex() == 0)
                || (categoryCB.getSelectionModel().getSelectedIndex() == 0)
        );
    }

    public void openManageCategoryBrands(ActionEvent event) throws IOException {

        Parent viewParent = FXMLLoader.load(getClass().getResource("/view/categories_and_brands.fxml"));
        Stage windowstage = (Stage) ((Node) event.getTarget()).getScene().getWindow();

        windowstage.setScene(new Scene(viewParent, 1280, 720));
        windowstage.centerOnScreen();
        windowstage.setTitle("Manage Product Brands and Categories");
        Image icon = new Image(MainController.class.getResource("/res/icons/warehouse.png").toExternalForm(), false);
        windowstage.getIcons().add(icon);
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

