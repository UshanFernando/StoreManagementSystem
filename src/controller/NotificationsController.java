package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Notification;
import service.NotificationService;
import util.Constants;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static util.Constants.READ_TAG;
import static util.Constants.UNREAD_TAG;

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

    private boolean unread = true;

    private NotificationService notificationService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadNotificatios(UNREAD_TAG);
    }

    private void loadNotificatios(String type) {
        notificationService = new NotificationService();
        ObservableList<Notification> notifications = null;
        if (type.equalsIgnoreCase(UNREAD_TAG)){
           notifications = notificationService.getUnreadNotifications();
            titleLbl.setText("Unread Notifications");
        }else if (type.equalsIgnoreCase(READ_TAG)){
            notifications = notificationService.getNotifications();
            titleLbl.setText("ALL Notifications");

        }

        if (notifications==null) {
            System.out.println("No Notifications");
        } else {
           notList.setItems(notifications);

        }


    }

    @FXML
    private void loadAll(){

        if (unread){
            loadNotificatios(READ_TAG);
            viewAllBtn.setText("View Unread Notifications");
            unread = false;
        }else {
            loadNotificatios(UNREAD_TAG);
            viewAllBtn.setText("View All Notifications");
            unread = true;
        }


    }

    @FXML
    private void close(){
        Stage stage = (Stage) viewAllBtn.getScene().getWindow();
        // do what you have to do
        stage.close();

    }


    @FXML
    private void markAsRead(){

        Notification selected  = notList.getSelectionModel().getSelectedItem();
        selected.setStatus(READ_TAG);
        notificationService.updateNotification(selected);
        loadNotificatios(UNREAD_TAG);
    }



}
