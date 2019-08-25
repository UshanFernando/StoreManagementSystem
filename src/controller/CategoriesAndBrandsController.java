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
import model.Brand;
import model.Category;
import service.BrandManagerService;
import service.CategoryManagerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoriesAndBrandsController implements Initializable {

    @FXML
    TableView<Brand> brandTable;

    @FXML
    TableView<Category> categoryTable;

    @FXML
    TableColumn bidColumn;

    @FXML
    TableColumn bnameColumn;

    @FXML
    TableColumn bstatusColumn;

    @FXML
    TableColumn cidColumn;

    @FXML
    TableColumn cnameColumn;

    @FXML
    TableColumn cstatusColumn;

    @FXML
    ChoiceBox type;

    @FXML
    ChoiceBox status;

    @FXML
    TextField name;

    private BrandManagerService brandManagerService;
    private CategoryManagerService categoryManagerService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        bstatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        cidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cstatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        brandManagerService = new BrandManagerService();
        categoryManagerService = new CategoryManagerService();

        loadBrands();
        loadCategories();

        brandTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

              Brand brand = brandTable.getSelectionModel().getSelectedItem();

              name.setText(brand.getName());
              status.setValue(brand.getStatus());
              type.getSelectionModel().select(1);


            }
        });

        categoryTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                Category category = categoryTable.getSelectionModel().getSelectedItem();

                name.setText(category.getName());
                status.setValue(category.getStatus());
                type.getSelectionModel().select(2);


            }
        });

    }

    private void loadBrands() {

        ObservableList<Brand> brands = brandManagerService.getBrandsList();

        if (brands == null) {
            System.out.println("No Brands");
        } else brandTable.setItems(brands);
    }

    private void loadCategories() {

        ObservableList<Category> categories = categoryManagerService.getCategoriesList();

        if (categories == null) {
            System.out.println("No Categories");
        } else categoryTable.setItems(categories);
    }

    public void openInventory(ActionEvent event) throws IOException {

        Parent invenetoryViewParent = FXMLLoader.load(getClass().getResource("/view/inventory.fxml"));

        Stage window = (Stage) ((Node) event.getTarget()).getScene().getWindow();

        window.setScene(new Scene(invenetoryViewParent, 1280, 720));
        window.centerOnScreen();
        window.setTitle("Inventory Management");
        Image icon = new Image(MainController.class.getResource("/res/icons/warehouse.png").toExternalForm(), false);
        window.getIcons().add(icon);
    }

    @FXML
    public void add() {

        if (valid()) {
            if (type.getSelectionModel().isSelected(1)) {

                Brand brand = new Brand(name.getCharacters().toString(), status.getValue().toString());
                brandManagerService.addBrand(brand);
                loadBrands();
            } else if (type.getSelectionModel().isSelected(2)) {

                Category category = new Category(name.getCharacters().toString(), status.getValue().toString());
                categoryManagerService.addCategory(category);
                loadCategories();
            }

        }else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }

        name.clear();
        status.getSelectionModel().selectFirst();

    }


    @FXML
    public void update() {

        if (valid()) {
            if (type.getSelectionModel().isSelected(1)) {

                Brand selected = brandTable.getSelectionModel().getSelectedItem();
                Brand brand = new Brand(selected.getId(),name.getCharacters().toString(), status.getValue().toString());
                brandManagerService.updateBrand(brand);
                loadBrands();

            } else if (type.getSelectionModel().isSelected(2)) {

                Category selected = categoryTable.getSelectionModel().getSelectedItem();
                Category category = new Category(selected.getId(),name.getCharacters().toString(), status.getValue().toString());
                categoryManagerService.updateCategory(category);
                loadCategories();
            }

        }else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }

        name.clear();
        status.getSelectionModel().selectFirst();

    }


    @FXML
    public void delete() {

        if (!categoryTable.getSelectionModel().isEmpty()) {

            Category category = categoryTable.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure You Want to delete this Category from System ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                categoryManagerService.removeCategory(category.getId());
                loadCategories();
            }

        } else if (!brandTable.getSelectionModel().isEmpty()) {

            Brand brand = brandTable.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure You Want to delete this Brand from System ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                brandManagerService.removeBrand(brand.getId());
                loadBrands();
            }

        }
    }

    private boolean valid() {

        return !(name.getCharacters().length() == 0 || type.getSelectionModel().isSelected(0)
                || status.getSelectionModel().isSelected(0));
    }
}
