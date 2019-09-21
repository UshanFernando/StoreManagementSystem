package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import model.Brand;
import model.Finance;
//import model.Product;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;


public class FinanceManagerService implements FinanceManagerServiceInterface {

    private static Connection connection;
    private ResultSet myRs = null;
    private static Statement myStmt = null;
    private static PreparedStatement preparedStatement;

    /**
     * default constructor
     */
    public FinanceManagerService() {

    }

    static {
        //This will call createTable() when class loads.
        createTable();
    }

    /**
     * This static method will generate brands table
     */
    public static void createTable() {

        try {
            connection = DBConnection.getDBConnection();
            myStmt = connection.createStatement();

            // Create new products table as per SQL query available in
            myStmt.executeUpdate(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_FINANCE_TABLE));

        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
            System.out.println("No need to create table");

        }
    }




    @Override
    public ObservableList<Finance> getFinanceList() {
        ObservableList<Finance>  finance = FXCollections.observableArrayList();
        // TODO Auto-generated method stub
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_FINANCE));


            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                int id = myRs.getInt("id");
                String status = myRs.getString("status");
                double amount = myRs.getDouble("amount");
                String date  = myRs.getString("date");


                // create Brand object
                Finance currentFinance = new Finance(id, status, amount, date);

                // adding Brand object to list
                finance.add(currentFinance);

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return finance;
    }

    /**
     * This Function will return a particular user by id
     *
     * @return User
     */
    @Override
    public Finance getFinanceById(int userId) {
        // TODO Auto-generated method stub
        Finance finance = null;
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_BRAND_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, userId);
            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                int id = myRs.getInt("bid");
                String status = myRs.getString("status");
                double amount = myRs.getDouble("amount");
                String date  = myRs.getString("date");


                // create product object
                finance = new Finance(id, status, amount, date);

            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return finance;
    }

    @Override
    public void updateFinance(Finance finance) {
        // TODO Auto-generated method stub

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_PRODUCT));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, finance.getId());
            preparedStatement.setString(Constants.COLUMN_INDEX_TWO, finance.getStatus());
            preparedStatement.setDouble(Constants.COLUMN_INDEX_THREE, finance.getAmount());
            preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, finance.getDate());
            preparedStatement.setInt(5, finance.getId());
            System.out.println(preparedStatement.toString());
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




    private void makeFinanceQuery(Finance finance) throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException {
        connection = DBConnection.getDBConnection();
        preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_PRODUCT));
        preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, finance.getId());
        preparedStatement.setString(Constants.COLUMN_INDEX_TWO, finance.getStatus());
        preparedStatement.setDouble(Constants.COLUMN_INDEX_THREE, finance.getAmount());
        preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, finance.getDate());


    }

    @Override
    public boolean addFinance(Finance finance) {
        // TODO Auto-generated method stub
        boolean success = false;
//		if (!emailCheck(brand.getEmail())) {
//			// this code will only run if entered email is not already in DB
//			success = true;

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_FINANCE));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, finance.getId());
            preparedStatement.setString(Constants.COLUMN_INDEX_TWO, finance.getStatus());
            preparedStatement.setDouble(Constants.COLUMN_INDEX_THREE, finance.getAmount());
            preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, finance.getDate());
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


    @Override
    public void removeFinance(int fid) {
        // TODO Auto-generated method stub

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_REMOVE_FINANCE));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, fid);
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
