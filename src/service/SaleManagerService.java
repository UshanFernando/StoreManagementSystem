package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Brand;
import model.Sale;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;


public class SaleManagerService implements SaleManagerServiceInterface {

    private static Connection connection;
    private ResultSet myRs = null;
    private static Statement myStmt = null;
    private static PreparedStatement preparedStatement;

    /**
     * default constructor
     */
    public SaleManagerService() {

    }

    static {
        //This will call createTable() when class loads.
        createTable();
    }

    /**
     * This static method will generate sales table
     */
    public static void createTable() {

        try {
            connection = DBConnection.getDBConnection();
            myStmt = connection.createStatement();

            // Create new products table as per SQL query available in
            myStmt.executeUpdate(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_SALES_TABLE));
            System.out.println("Sales Table Created");
        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
            System.out.println("Sales Table Not Created");

        }
    }


    @Override
    public ObservableList<Sale> getSalesList() {
        ObservableList<Sale>  sales = FXCollections.observableArrayList();
        // TODO Auto-generated method stub
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_SALES));


            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                int id = myRs.getInt("sid");
                double subTotal = myRs.getDouble("subTotal");
                double discount = myRs.getDouble("discount");
                double netTotal = myRs.getDouble("netTotal");
                double payment = myRs.getDouble("payment");
                Timestamp date = myRs.getTimestamp("timestamp");

                // create Brand object
                Sale currentSale = new Sale(id, subTotal, discount,netTotal,payment,date);

                // adding Brand object to list
               sales.add(currentSale);

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return sales;
    }

    @Override
    public boolean addSale(Sale sale) {
        // TODO Auto-generated method stub
        boolean success = false;


        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_SALES));
            preparedStatement.setDouble(Constants.COLUMN_INDEX_ONE,sale.getSubTotal());
            preparedStatement.setDouble(Constants.COLUMN_INDEX_TWO,sale.getDiscount());
            preparedStatement.setDouble(Constants.COLUMN_INDEX_THREE,sale.getNetTotal());
            preparedStatement.setDouble(Constants.COLUMN_INDEX_FOUR,sale.getPayment());
            preparedStatement.setTimestamp(Constants.COLUMN_INDEX_FIVE,sale.getTimeStamp());
            preparedStatement.execute();
            success = true;

        } catch (SQLException | SAXException | IOException | ParserConfigurationException
                | ClassNotFoundException e) {
            e.printStackTrace();
            success = false;
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


    @Override
    public void removeSale(int sid) {
        // TODO Auto-generated method stub

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_REMOVE_SALES));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, sid);
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
