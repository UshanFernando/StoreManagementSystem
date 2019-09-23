package util;

import controller.Main;
import controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private static SceneManager sceneManager = null;

    private SceneManager() {

    }

    public static SceneManager manage() {

        if (sceneManager == null) {

            sceneManager = new SceneManager();

        }

        return sceneManager;
    }


    public void openInventoryScene() throws IOException {

        Parent invenetoryViewParent = FXMLLoader.load(getClass().getResource("/view/inventory.fxml"));
        Stage window = Main.getPrimaryStage();

        window.setScene(new Scene(invenetoryViewParent, 1280, 720));
        window.centerOnScreen();
        window.setTitle("Inventory Management");
        Image icon = new Image(MainController.class.getResource("/res/icons/warehouse.png").toExternalForm(), false);
        window.getIcons().add(icon);

    }

    public void openSupplierScene() throws IOException {
        Parent supplierViewParent = FXMLLoader.load(getClass().getResource("/view/supplier.fxml"));
        Stage windowstage = Main.getPrimaryStage();

        windowstage.setScene(new Scene(supplierViewParent, 1280, 720));
        windowstage.centerOnScreen();
        windowstage.setTitle("Supplier Management");
        Image icon = new Image(MainController.class.getResource("/res/icons/trucking.png").toExternalForm(), false);
        windowstage.getIcons().add(icon);
    }

    public void openOrderScene() throws IOException {
        Parent ordersViewParent = FXMLLoader.load(getClass().getResource("/view/orders.fxml"));
        Stage windowsstage = Main.getPrimaryStage();

        windowsstage.setScene(new Scene(ordersViewParent, 1280, 720));
        windowsstage.centerOnScreen();
        windowsstage.setTitle("Order Management");
        Image icon = new Image(MainController.class.getResource("/res/icons/checklist.png").toExternalForm(), false);
        windowsstage.getIcons().add(icon);
    }

    public void openSalesScene() throws IOException {
        Parent salesViewParent = FXMLLoader.load(getClass().getResource("/view/sales.fxml"));
        Stage windowstage = Main.getPrimaryStage();
        windowstage.setScene(new Scene(salesViewParent, 1280, 720));
        windowstage.setFullScreen(true);
        windowstage.setTitle("Sales");
        Image icon = new Image(MainController.class.getResource("/res/icons/sale.png").toExternalForm(), false);
        windowstage.getIcons().add(icon);
    }

    public void openFinanceScene() throws IOException {
        Parent salesViewParent = FXMLLoader.load(getClass().getResource("/view/finance.fxml"));
        Stage windowstage = Main.getPrimaryStage();
        windowstage.setScene(new Scene(salesViewParent, 1280, 720));
        windowstage.setTitle("Finance");
        Image icon = new Image(MainController.class.getResource("/res/icons/accounting.png").toExternalForm(), false);
        windowstage.getIcons().add(icon);
    }

    public void openAttendanceScene() throws IOException {
        Parent salesViewParent = FXMLLoader.load(getClass().getResource("/view/attendance.fxml"));
        Stage windowstage = Main.getPrimaryStage();
        windowstage.setScene(new Scene(salesViewParent, 1280, 720));
        windowstage.setTitle("Attendance");
        Image icon = new Image(MainController.class.getResource("/res/icons/red-card.png").toExternalForm(), false);
        windowstage.getIcons().add(icon);
    }

    public void openSalaryScene() throws IOException {
        Parent salesViewParent = FXMLLoader.load(getClass().getResource("/view/salary_calculator.fxml"));
        Stage windowstage = Main.getPrimaryStage();
        windowstage.setScene(new Scene(salesViewParent, 1280, 720));
        windowstage.setTitle("Attendance");
        Image icon = new Image(MainController.class.getResource("/res/icons/salary.png").toExternalForm(), false);
        windowstage.getIcons().add(icon);
    }

    public void openAdminScene() throws IOException {
        Parent salesViewParent = FXMLLoader.load(getClass().getResource("/view/employee.fxml"));
        Stage windowstage = Main.getPrimaryStage();
        windowstage.setScene(new Scene(salesViewParent, 1280, 720));
        windowstage.setTitle("Admin");
        Image icon = new Image(MainController.class.getResource("/res/icons/admin.png").toExternalForm(), false);
        windowstage.getIcons().add(icon);
    }

    public void openSalesReportScene() throws IOException {
        Parent salesViewParent = FXMLLoader.load(getClass().getResource("/view/sales_reports.fxml"));
        Stage windowstage = Main.getPrimaryStage();
        windowstage.setScene(new Scene(salesViewParent, 1280, 720));
        windowstage.setTitle("Sales Reports");
        Image icon = new Image(MainController.class.getResource("/res/icons/sale.png").toExternalForm(), false);
        windowstage.getIcons().add(icon);
    }


}
