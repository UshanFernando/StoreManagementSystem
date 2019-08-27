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

public class SalaryCalController  {



    @FXML
    TextField eID;

    @FXML
    TextField sID;

    @FXML
    TextField bonus;

    @FXML
    TextField overtime;

    @FXML
    TextField deduction;

    @FXML
    TextField date;

    @FXML
    TextField basicid;

    @FXML
    TextField total;






    private SalaryCalculatorManagerService salaryCalculatorManagerService;


//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        eID.setCellValueFactory(new PropertyValueFactory<>("id"));
//        bnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        bstatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
//
//        cidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//        cnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        cstatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
//
//        brandManagerService = new BrandManagerService();
//        categoryManagerService = new CategoryManagerService();
//
//        loadBrands();
//        loadCategories();
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
//
//    }
//
//    private void loadSalary() {
//
//        ObservableList<Salary> salary = salaryCalculatorManagerService.getSalaryList();
//
//        if (salary == null) {
//            System.out.println("empty");
//        } else salaryTable.setItems(salary);
//    }

//    private void loadCategories() {
//
//        ObservableList<Category> categories = categoryManagerService.getCategoriesList();
//
//        if (categories == null) {
//            System.out.println("No Categories");
//        } else categoryTable.setItems(categories);
//    }

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

    @FXML
    public void add() {

        if (true) {


                int sid = Integer.parseInt(sID.getCharacters().toString());
                String eid = eID.getCharacters().toString();
                String dat= date.getCharacters().toString();
                Double basic = Double.parseDouble(basicid.getCharacters().toString());
                Double overt = Double.parseDouble(overtime.getCharacters().toString());
                Double bon = Double.parseDouble(bonus.getCharacters().toString());
                Double ded = Double.parseDouble(deduction.getCharacters().toString());
                Double tot = Double.parseDouble(total.getCharacters().toString());

                //loadSalary();

                Salary salary = new Salary(sid, eid, dat, basic, overt, bon, tot, ded);

                salaryCalculatorManagerService.addSalary(salary);


//                loadSalary();
            }

        else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }

        sID.clear();
        eID.clear();
        bonus.clear();
        overtime.clear();
        basicid.clear();
        date.clear();




    }

    @FXML

    public void openSalaryReports(ActionEvent event) throws IOException {

        Parent salaryViewParent = FXMLLoader.load(getClass().getResource("/view/salary_report.fxml"));

        Stage window = (Stage) ((Node) event.getTarget()).getScene().getWindow();

        window.setScene(new Scene(salaryViewParent, 1280,720));

        window.centerOnScreen();

        window.setTitle("Salary reports");

    }

    @FXML

    public void openRequest(ActionEvent event) throws IOException {

        Parent salaryViewParent = FXMLLoader.load(getClass().getResource("/view/salary_request.fxml"));

        Stage window = (Stage) ((Node) event.getTarget()).getScene().getWindow();

        window.setScene(new Scene(salaryViewParent, 1280,720));

        window.centerOnScreen();

        window.setTitle("Salary request");

    }




}
