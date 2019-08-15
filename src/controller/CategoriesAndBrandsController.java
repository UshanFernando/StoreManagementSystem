package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoriesAndBrandsController implements Initializable {

    @FXML
    TableView brandTable;

    @FXML
    TableView categoryTable;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {


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
}
