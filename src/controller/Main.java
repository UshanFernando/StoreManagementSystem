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

    }

    private static Stage pStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
        primaryStage.setTitle("Store Management Nisha Electricals PVC");
        Image icon = new Image(MainController.class.getResource("/res/icons/icon.png").toExternalForm(), false);
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 840, 473));
        setPrimaryStage(primaryStage);

//        Parent root = FXMLLoader.load(getClass().getResource("/view/sales.fxml"));
//        primaryStage.setScene(new Scene(root, 1280, 720));
//        primaryStage.setTitle("Inventory Management");
//
//        Image icon = new Image(MainController.class.getResource("/res/icons/warehouse.png").toExternalForm(), false);
//        primaryStage.getIcons().add(icon);
        primaryStage.show();


    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        Main.pStage = pStage;
    }
}
