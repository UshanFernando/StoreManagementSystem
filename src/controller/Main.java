package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
//        ProductManagerService ma = new ProductManagerService();
//        Product user = ma.getProductById(1001);
//        System.out.println(user.getName());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
        primaryStage.setTitle("Store Management Nisha Electricals PVC");
        Image icon = new Image(MainController.class.getResource("/res/icons/icon.png").toExternalForm(), false);
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 840, 473));
        primaryStage.show();
    }
}
