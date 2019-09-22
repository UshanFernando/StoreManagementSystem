package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Brand;
import model.Category;
import model.Notification;
import model.Product;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;


public class NotificationService implements NotificationServiceInterface {

    private static Connection connection;
    private ResultSet myRs = null;
    private static Statement myStmt = null;
    private static PreparedStatement preparedStatement;


    /**
     * default constructor
     */
    public NotificationService() {


    }

    static {
        //This will call craeteUsersTable() when class loads.
        createNotificationsTable();
        BrandManagerService.createTable();
        CategoryManagerService.createTable();
    }

    /**
     * This static method will generate products table
     */
    public static void createNotificationsTable() {

        try {
            connection = DBConnection.getDBConnection();
            myStmt = connection.createStatement();

            // Create new products table as per SQL query available in
            myStmt.executeUpdate(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_PRODUCT_TABLE));
            System.out.println("Product Table Created");
        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
            System.out.println("Product Table Not Created");
        }
    }


    @Override
    public ObservableList<Notification> getNotifications() {
        ObservableList<Notification> notifications = FXCollections.observableArrayList();
        // TODO Auto-generated method stub
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_ALL_NOTIFICATION));


            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                int id = myRs.getInt("nId");
                String message = myRs.getString("message");
                String status = myRs.getString("status");

                // create product object
                Notification currentNotification = new Notification(message);

                // adding user object to list
                notifications.add(currentNotification);

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return notifications;
    }

    @Override
    public ObservableList<Notification> getUnreadNotifications() {
        ObservableList<Notification> notifications = FXCollections.observableArrayList();
        // TODO Auto-generated method stub
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_UNREAD_NOTIFICATION));


            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                int id = myRs.getInt("nId");
                String message = myRs.getString("message");
                String status = myRs.getString("status");

                // create product object
                Notification currentNotification = new Notification(message);

                // adding user object to list
                notifications.add(currentNotification);

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return notifications;
    }


    @Override
    public void updateNotification(Notification notification ) {

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_NOTIFICATION));
            makeQuery(notification);
            preparedStatement.setInt(Constants.COLUMN_INDEX_FOUR,notification.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();


        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }



    private void makeQuery(Notification notification) throws SQLException {
        preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, notification.getId());
        preparedStatement.setString(Constants.COLUMN_INDEX_TWO,notification.getMessage());
        preparedStatement.setString(Constants.COLUMN_INDEX_THREE,notification.getStatus());

    }


    @Override
    public void deleteNotification(int nId) {
        // TODO Auto-generated method stub

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_NOTIFICATION_TABLE));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, nId);
            preparedStatement.execute();

        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
