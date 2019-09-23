package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import util.SceneManager;

import java.io.IOException;

public class MainController {

    @FXML
    public void openInventoryScene(ActionEvent event) throws IOException {
        SceneManager.manage().openInventoryScene();
    }

    @FXML
    public void openSupplierScene(ActionEvent event) throws IOException {
        SceneManager.manage().openSupplierScene();
    }

    @FXML
    public void openOrderScene(ActionEvent event) throws IOException {
        SceneManager.manage().openOrderScene();
    }

    @FXML
    public void openSalesScene(ActionEvent event) throws IOException {
        SceneManager.manage().openSalesScene();
    }

    @FXML
    public void openFinanceScene(ActionEvent event) throws IOException {
        SceneManager.manage().openFinanceScene();
    }

    @FXML
    public void openAttendanceScene(ActionEvent event) throws IOException {
        SceneManager.manage().openAttendanceScene();
    }

    @FXML
    public void openSalaryScene(ActionEvent event) throws IOException {
        SceneManager.manage().openAttendanceScene();
    }

    @FXML
    public void openAdminScene(ActionEvent event) throws IOException {
        SceneManager.manage().openAdminScene();
    }

}
