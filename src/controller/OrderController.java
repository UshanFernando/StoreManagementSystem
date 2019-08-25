package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderController {
    public void openHomeScenefromOrder(ActionEvent event) throws IOException {
        Parent homeViewParent = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
        Stage window = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        window.setScene(new Scene(homeViewParent, 840, 473));
        window.centerOnScreen();
        window.setTitle("Store Management Nisha Electricals PVC");
        Image icon = new Image(MainController.class.getResource("/res/icons/icon.png").toExternalForm(), false);
        window.getIcons().add(icon);
    }
}
