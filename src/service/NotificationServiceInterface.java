package service;

import javafx.collections.ObservableList;
import model.Notification;
import model.Product;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

/**
 * @author Fernando G.U.S
 * @author IT18027884
 * <p>
 * This is ProductManagerService
 * @see DBConnection
 * @see QueryUtil
 * @see Constants
 */

public interface NotificationServiceInterface {

    public ObservableList<Notification> getNotifications();

    public ObservableList<Notification> getUnreadNotifications();

    public void updateNotification(Notification notification);

    public void deleteNotification(int nid);

}
