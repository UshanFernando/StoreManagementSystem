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
//import model.Brand;
//import model.Category;
import model.Finance;
//import service.BrandManagerService;
//import service.CategoryManagerService;
import service.FinanceManagerService;
import util.Constants;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static service.FinanceManagerService.*;

public class FinanceController implements Initializable {

    @FXML
    TableView<Finance> incomeTable;

    @FXML
    TableView<Finance> expenseTable;

    @FXML
    TableColumn iidColumn;

    @FXML
    TableColumn statColumn;

    @FXML
    TableColumn amntColumn;

    @FXML
    TableColumn datColumn;

    @FXML
    TextField qtyTF;

    @FXML
    TextField qtyTF1;

    @FXML
    TextField qtyTF11;

    @FXML
    TextField qtyTF111;

    @FXML
    Button monthButtonId;


    private FinanceManagerService financeManagerService;
    private static PreparedStatement preparedStatement;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        iidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        statColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        amntColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        datColumn.setCellValueFactory(new PropertyValueFactory<>("date"));


        financeManagerService = new FinanceManagerService();


        loadFinance();
        incomeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                Finance finance = incomeTable.getSelectionModel().getSelectedItem();

                qtyTF.setText(String.valueOf(finance.getId()));
                qtyTF1.setText(finance.getStatus());
                qtyTF11.setText(String.valueOf(finance.getAmount()));
                qtyTF111.setText(finance.getDate());

            }
        });


    }

    private void loadFinance() {

        ObservableList<Finance> finance = financeManagerService.getFinanceList();

        incomeTable.setItems(finance);
        if (finance == null) {
            System.out.println("No finance");
        }
    }


    @FXML
    public void add() {

        if (true) {

            int fid = Integer.parseInt(qtyTF.getCharacters().toString());
            String status = qtyTF1.getCharacters().toString();
            Double amount = Double.parseDouble(qtyTF11.getCharacters().toString());
            String date   = qtyTF111.getCharacters().toString();
            int qty = Integer.parseInt(qtyTF.getCharacters().toString());

            Finance finance = new Finance(fid, status, amount, date);

            financeManagerService.addFinance(finance);
            System.out.println(finance.getStatus());
            loadFinance();


        }else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }



    }


    @FXML
    public void update() {

        if (valid()) {
            int fid = Integer.parseInt(qtyTF.getCharacters().toString());
            String status = qtyTF1.getCharacters().toString();
            Double amount = Double.parseDouble(qtyTF11.getCharacters().toString());
            String date   = qtyTF111.getCharacters().toString();


            Finance selected = incomeTable.getSelectionModel().getSelectedItem();
            Finance finance = new Finance(fid, status, amount, date);
            financeManagerService.updateFinance(finance);
            loadData();

        } /*else if (type.getSelectionModel().isSelected(2)) {

                Category selected = categoryTable.getSelectionModel().getSelectedItem();
                Category category = new Category(selected.getId(),name.getCharacters().toString(), status.getValue().toString());
                categoryManagerService.updateCategory(category);
                loadCategories();
            }
*/ else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }


    }



    private boolean valid() {

        return !(qtyTF.getCharacters().length() == 0);

    }

    private void loadData() {


        ObservableList<Finance> finances = financeManagerService.getFinanceList();

        if (finances == null) {
            System.out.println("No Finances");
        } else {
            incomeTable.setItems(finances);
        }


    }


    @FXML
    public void delete() {

        if (!incomeTable.getSelectionModel().isEmpty()) {

            Finance finance = incomeTable.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure You Want to delete this Category from System ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
               financeManagerService.removeFinance(finance.getId());
               loadFinance();
            }

//        } else if (!brandTable.getSelectionModel().isEmpty()) {
//
//            Brand brand = brandTable.getSelectionModel().getSelectedItem();
//
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//                    "Are you sure You Want to delete this Brand from System ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
//            alert.initStyle(StageStyle.UTILITY);
//            alert.showAndWait();

//            if (alert.getResult() == ButtonType.YES) {
//                brandManagerService.removeBrand(brand.getId());
//                loadBrands();
//            }

        }
    }
    @FXML
    public void openMonthlyReports(ActionEvent event) throws IOException {

        Parent financeViewParent = FXMLLoader.load(getClass().getResource("/view/monthly_reports.fxml"));

        Stage window = (Stage) ((Node) event.getTarget()).getScene().getWindow();

        window.setScene(new Scene(financeViewParent, 1280, 720));
        window.centerOnScreen();
        window.setTitle("Inventory Management");
//    Image icon = new Image(MainController.class.getResource("/res/icons/warehouse.png").toExternalForm(), false);
//    window.getIcons().add(icon);
    }

    @FXML
    public void openRequests(ActionEvent event) throws IOException {

        Parent financeViewParent = FXMLLoader.load(getClass().getResource("/view/requests.fxml"));

        Stage window = (Stage) ((Node) event.getTarget()).getScene().getWindow();

        window.setScene(new Scene(financeViewParent, 1280, 720));
        window.centerOnScreen();
        window.setTitle("Requests Management");
//    Image icon = new Image(MainController.class.getResource("/res/icons/warehouse.png").toExternalForm(), false);
//    window.getIcons().add(icon);
    }

//    private boolean valid() {
//
//        return !(name.getCharacters().length() == 0 || type.getSelectionModel().isSelected(0)
//                || status.getSelectionModel().isSelected(0));
//    }

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
