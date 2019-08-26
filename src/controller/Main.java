package controller;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Supplier;
import service.SupplierManagerService;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
        primaryStage.setTitle("Store Management Nisha Electricals PVC");
        Image icon = new Image(MainController.class.getResource("/res/icons/icon.png").toExternalForm(), false);
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 840, 473));

//
//        Parent root = FXMLLoader.load(getClass().getResource("/view/inventory.fxml"));
//        primaryStage.setScene(new Scene(root, 1280, 720));
//        primaryStage.setTitle("Inventory Management");
//
//        Image icon = new Image(MainController.class.getResource("/res/icons/warehouse.png").toExternalForm(), false);
//        primaryStage.getIcons().add(icon);
        primaryStage.show();
//        loadSuppliers();
    }

//
//    private void loadSuppliers(){
//        SupplierManagerService supplierManagerService = new SupplierManagerService();
//        ObservableList<Supplier> suppliers = new SupplierManagerService().getSupplierList();
//
//        if (suppliers == null){
//            System.out.println("No Suppliers");
//        }else
//        {
////            supplierTableView.setItems(suppliers);
//        }
//    }
}
