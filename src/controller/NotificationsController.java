package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Notification;
import service.NotificationService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NotificationsController implements Initializable {

    @FXML
    private ListView<Notification> notList;

    @FXML
    private Button markAllReadBtn;

    @FXML
    private Button markReadBtn;

    @FXML
    private Button viewAllBtn;

    @FXML
    private Label titleLbl;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadData();
    }

    private void loadData() {

        NotificationService notificationService = new NotificationService();

        ObservableList<Notification> notifications = notificationService.getUnreadNotifications();

        if (notifications == null) {
            System.out.println("No Notifications");
        } else {
           notList.setItems(notifications);
        }


    }

}
