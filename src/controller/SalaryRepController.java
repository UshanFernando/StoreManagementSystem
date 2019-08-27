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

    }

    private void loadSalary() {

        ObservableList<Salary> salary = salaryCalculatorManagerService.getSalaryList();

        if (salary == null) {
            System.out.println("empty");
        } else salaryTable.setItems(salary);
    }




    @FXML
    public void delete() {

        if (!salaryTable.getSelectionModel().isEmpty()) {

            Salary salary = salaryTable.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure You Want to delete details from system ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
               // salaryCalculatorManagerService.removeSalary(salary.getsID());
                loadSalary();
            }

        }
        }
}



