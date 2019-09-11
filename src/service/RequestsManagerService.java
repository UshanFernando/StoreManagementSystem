package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import model.Brand;
//import model.Finance;
//import model.Product;
//import model.Finance;
import model.Finance;
import model.Requests;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;


public class RequestsManagerService implements RequestsManagerServiceInterface {

    private static Connection connection;
    private ResultSet myRs = null;
    private static Statement myStmt = null;
    private static PreparedStatement preparedStatement;

    /**
     * default constructor
     */
    public RequestsManagerService() {

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
    public ObservableList<Requests> getRequestsList() {
        ObservableList<Requests>  requests = FXCollections.observableArrayList();
        // TODO Auto-generated method stub
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_REQUESTS));


            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                int id = myRs.getInt("id");
                String status = myRs.getString("status");
                double amount = myRs.getDouble("amount");
                String date  = myRs.getString("date");


                // create Brand object
                Requests currentFinance = new Requests(id, status, amount, date);

                // adding Brand object to list
                requests.add(currentFinance);

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return requests;
    }

    @Override
    public Finance getRequestsById(int pid) {
        return null;
    }

    @Override
    public void updateRequests(Finance user) {

    }

    @Override
    public boolean addRequests(Finance user) {
        return false;
    }

    @Override
    public void removeRequests(int pid) {

    }

    /**
     * This Function will return a particular user by id
     *
     * @return User
     */
//    @Override
//    public Requests getRequestsById(int userId) {
//        // TODO Auto-generated method stub
//        Requests requests = null;
//        try {
//
//            connection = DBConnection.getDBConnection();
//            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_REQUESTS_BY_ID));
//            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, userId);
//            myRs = preparedStatement.executeQuery();
//
//            while (myRs.next()) {
//                int id = myRs.getInt("bid");
//                String status = myRs.getString("status");
//                double amount = myRs.getDouble("amount");
//                String date  = myRs.getString("date");
//
//
//                // create product object
//                requests = new Requests(id, status, amount, date);
//
//            }
//
//        } catch (Exception exc) {
//            exc.printStackTrace();
//        }
//
//        return requests;
//    }

//    @Override
//    public void updateFinance(Finance user) {
//
//    }


//    @Override
//    public void updateFinance(Finance finance) {
//        // TODO Auto-generated method stub
//
//        try {
//            makeFinanceQuery(finance);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }


    private void makeFinanceQuery(Requests requests) throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException {
        connection = DBConnection.getDBConnection();
        preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_PRODUCT));
        preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, requests.getId());
        preparedStatement.setString(Constants.COLUMN_INDEX_TWO, requests.getStatus());
        preparedStatement.setDouble(Constants.COLUMN_INDEX_THREE, requests.getAmount());
        preparedStatement.setString(Constants.COLUMN_INDEX_THREE, requests.getDate());


    }

//    @Override
//    public boolean addRequests(Finance finance) {
//        // TODO Auto-generated method stub
//        boolean success = false;
////		if (!emailCheck(brand.getEmail())) {
////			// this code will only run if entered email is not already in DB
////			success = true;
//
//        try {
//            connection = DBConnection.getDBConnection();
//            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_REQUESTS));
//            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, finance.getId());
//            preparedStatement.setString(Constants.COLUMN_INDEX_TWO, finance.getStatus());
//            preparedStatement.setDouble(Constants.COLUMN_INDEX_THREE, finance.getAmount());
//            preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, finance.getDate());
//            preparedStatement.execute();
//
//        } catch (SQLException | SAXException | IOException | ParserConfigurationException
//                | ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        return success;
//    }
//
//
//    @Override
//    public void removeFinance(int fid) {
//        // TODO Auto-generated method stub
//
//        try {
//            connection = DBConnection.getDBConnection();
//            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_REMOVE_FINANCE));
//            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, fid);
//            preparedStatement.execute();
//
//        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
