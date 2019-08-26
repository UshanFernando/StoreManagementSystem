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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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


    private FinanceManagerService financeManagerService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        iidColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        statColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        amntColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        datColumn.setCellValueFactory(new PropertyValueFactory<>("date"));



        financeManagerService = new FinanceManagerService();

        load();


    }

    private void load() {

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
                load();


        }else {

            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please Provide Valid Information !", ButtonType.OK);

            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();


        }



    }

//
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
               load();
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

//    private boolean valid() {
//
//        return !(name.getCharacters().length() == 0 || type.getSelectionModel().isSelected(0)
//                || status.getSelectionModel().isSelected(0));
//    }
}
