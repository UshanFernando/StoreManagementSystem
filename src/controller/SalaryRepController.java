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
import model.Salary;
import service.SalaryCalculatorManagerService;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SalaryRepController implements Initializable {

    @FXML
    TableView<Salary> salaryTable;


    @FXML
    TableColumn sidColumn;

    @FXML
    TableColumn eidColumn;

    @FXML
    TableColumn bonusColumn;

    @FXML
    TableColumn overtimeColumn;

    @FXML
    TableColumn basicColumn;

    @FXML
    TableColumn deductionColumn;

    @FXML
    TableColumn dateColumn;

    @FXML
    TableColumn totalColumn;







    private SalaryCalculatorManagerService salaryCalculatorManagerService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sidColumn.setCellValueFactory(new PropertyValueFactory<>("sid"));
        eidColumn.setCellValueFactory(new PropertyValueFactory<>("eid"));
        bonusColumn.setCellValueFactory(new PropertyValueFactory<>("bonus"));

        overtimeColumn.setCellValueFactory(new PropertyValueFactory<>("overtime"));
        basicColumn.setCellValueFactory(new PropertyValueFactory<>("basic"));
        deductionColumn.setCellValueFactory(new PropertyValueFactory<>("deduction"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));


        salaryCalculatorManagerService = new SalaryCalculatorManagerService();


        loadSalary();
//
//
//        brandTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//
//                Brand brand = brandTable.getSelectionModel().getSelectedItem();
//
//                name.setText(brand.getName());
//                status.setValue(brand.getStatus());
//                type.getSelectionModel().select(1);
//
//
//            }
//        });
//
//        categoryTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//
//                Category category = categoryTable.getSelectionModel().getSelectedItem();
//
//                name.setText(category.getName());
//                status.setValue(category.getStatus());
//                type.getSelectionModel().select(2);
//
//
//            }
//        });

    }

    private void loadSalary() {

        ObservableList<Salary> salary = salaryCalculatorManagerService.getSalaryList();

        if (salary == null) {
            System.out.println("empty");
        } else salaryTable.setItems(salary);
    }


//    public void openInventory(ActionEvent event) throws IOException {
//
//        Parent invenetoryViewParent = FXMLLoader.load(getClass().getResource("/view/inventory.fxml"));
//
//        Stage window = (Stage) ((Node) event.getTarget()).getScene().getWindow();
//
//        window.setScene(new Scene(invenetoryViewParent, 1280, 720));
//        window.centerOnScreen();
//        window.setTitle("Inventory Management");
//        Image icon = new Image(MainController.class.getResource("/res/icons/warehouse.png").toExternalForm(), false);
//        window.getIcons().add(icon);
//    }

//    @FXML
//    public void add() {
//
//        if (true) {
//            //if (type.getSelectionModel().isSelected(1)) {
//
//            int sid = Integer.parseInt(sID.getCharacters().toString());
//            String eid = eID.getCharacters().toString();
//            String dat= date.getCharacters().toString();
//            Double basic = Double.parseDouble(basicid.getCharacters().toString());
//            Double overt = Double.parseDouble(overtime.getCharacters().toString());
//            Double bon = Double.parseDouble(bonus.getCharacters().toString());
//            Double ded = Double.parseDouble(deduction.getCharacters().toString());
//            Double tot = Double.parseDouble(total.getCharacters().toString());
//
//            //loadSalary();
//
//            Salary salary = new Salary(sid, eid, dat, basic, overt, bon, tot, ded);
//
//            salaryCalculatorManagerService.addSalary(salary);
//
//
//            loadSalary();
//        }
//
//        else {
//
//            Alert alert = new Alert(Alert.AlertType.ERROR,
//                    "Please Provide Valid Information !", ButtonType.OK);
//
//            alert.initStyle(StageStyle.UTILITY);
//            alert.showAndWait();
//
//
//        }
//
//        sID.clear();
//        eID.clear();
//        bonus.clear();
//        overtime.clear();
//        basic.clear();
//        date.clear();
//
//
//
//
//    }


//    @FXML
//    public void update() {
//
//        if (valid()) {
//            if (type.getSelectionModel().isSelected(1)) {
//
//                Brand selected = brandTable.getSelectionModel().getSelectedItem();
//                Brand brand = new Brand(selected.getId(),name.getCharacters().toString(), status.getValue().toString());
//                brandManagerService.updateBrand(brand);
//                loadBrands();
//
//            } else if (type.getSelectionModel().isSelected(2)) {
//
//                Category selected = categoryTable.getSelectionModel().getSelectedItem();
//                Category category = new Category(selected.getId(),name.getCharacters().toString(), status.getValue().toString());
//                categoryManagerService.updateCategory(category);
//                loadCategories();
//            }
//
//        }else {
//
//            Alert alert = new Alert(Alert.AlertType.ERROR,
//                    "Please Provide Valid Information !", ButtonType.OK);
//
//            alert.initStyle(StageStyle.UTILITY);
//            alert.showAndWait();
//
//
//        }
//
//        name.clear();
//        status.getSelectionModel().selectFirst();
//
//    }


//    @FXML
//    public void delete() {
//
//        if (!categoryTable.getSelectionModel().isEmpty()) {
//
//            Category category = categoryTable.getSelectionModel().getSelectedItem();
//
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//                    "Are you sure You Want to delete this Category from System ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
//            alert.initStyle(StageStyle.UTILITY);
//            alert.showAndWait();
//
//            if (alert.getResult() == ButtonType.YES) {
//                categoryManagerService.removeCategory(category.getId());
//                loadCategories();
//            }
//
//        } else if (!brandTable.getSelectionModel().isEmpty()) {
//
//            Brand brand = brandTable.getSelectionModel().getSelectedItem();
//
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//                    "Are you sure You Want to delete this Brand from System ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
//            alert.initStyle(StageStyle.UTILITY);
//            alert.showAndWait();
//
//            if (alert.getResult() == ButtonType.YES) {
//                brandManagerService.removeBrand(brand.getId());
//                loadBrands();
//            }
//
//        }
//    }

//    private boolean valid() {
//
//        return !(name.getCharacters().length() == 0 || type.getSelectionModel().isSelected(0)
//                || status.getSelectionModel().isSelected(0));
//    }
}
