package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Leave;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;

import static javafx.collections.FXCollections.*;


public class LeaveManagerService implements LeaveManagerServiceInterface {

    private static Connection connection;
    private ResultSet myRs = null;
    private static Statement myStmt = null;
    private static PreparedStatement preparedStatement;

    /**
     * default constructor
     */
    public LeaveManagerService() {

    }

    static {
        //This will call createTable() when class loads.
        createTable();
    }

    /**
     * This static method will generate leaverecord table
     */
    public static void createTable() {

        try {
            connection = DBConnection.getDBConnection();
            myStmt = connection.createStatement();

            // Create new products table as per SQL query available in
            myStmt.executeUpdate(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_LEAVERECORD_TABLE));

        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
            System.out.println("No need to create table");

        }
    }

    @Override
    public boolean addLeave(Leave leave) {
        // TODO Auto-generated method stub
        boolean success = false;
        //if (!emailCheck(brand.getEmail())) {
        // this code will only run if entered email is not already in DB
        //	success = true;

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_LEAVE));
            preparedStatement.setString(Constants.COLUMN_INDEX_ONE, leave.getFullname());
            preparedStatement.setInt(Constants.COLUMN_INDEX_TWO, leave.getEmpId());
            preparedStatement.setString(Constants.COLUMN_INDEX_THREE, leave.getLtype());
            preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, leave.getEmail());
            preparedStatement.setString(Constants.COLUMN_INDEX_FIVE, leave.getDate());
            preparedStatement.execute();

        } catch (SQLException | SAXException | IOException | ParserConfigurationException
                | ClassNotFoundException e) {
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


        return success;
    }
}