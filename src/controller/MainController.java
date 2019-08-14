package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    public void openInventoryScene(ActionEvent event) throws IOException {

        Parent invenetoryViewParent = FXMLLoader.load(getClass().getResource("/view/inventory.fxml"));

        Stage window = (Stage) ((Node) event.getTarget()).getScene().getWindow();

        window.setScene(new Scene(invenetoryViewParent, 1280, 720));
        window.centerOnScreen();
        window.setTitle("manager.inventory"); //placed the string in /util/config.properties file
        Image icon = new Image(MainController.class.getResource("/res/icons/warehouse.png").toExternalForm(), false);
        window.getIcons().add(icon);

    }

    @FXML
    public  void openSupplierScene(ActionEvent event) throws IOException{
        Parent supplierViewParent = FXMLLoader.load(getClass().getResource("/view/supplier.fxml"));
        Stage windowstage = (Stage) ((Node) event.getTarget()).getScene().getWindow();

        windowstage.setScene(new Scene(supplierViewParent,1280,720));
        windowstage.centerOnScreen();
        windowstage.setTitle("manager.supplier"); //placed the string in /util/config.properties file
        Image icon = new Image(MainController.class.getResource("/res/icons/trucking.png").toExternalForm(),false);
        windowstage.getIcons().add(icon);
    }

    @FXML
    public  void openOrderScene(ActionEvent event) throws IOException{
        Parent ordersViewParent = FXMLLoader.load(getClass().getResource("/view/orders.fxml"));
        Stage windowsstage = (Stage)((Node) event.getTarget()).getScene().getWindow();

        windowsstage.setScene(new Scene(ordersViewParent,1280,720));
        windowsstage.centerOnScreen();
        windowsstage.setTitle("manager.order"); //placed the string in /util/config.properties file 
        Image icon = new Image(MainController.class.getResource("/res/icons/checklist.png").toExternalForm(),false);
        windowsstage.getIcons().add(icon);
    }

}
