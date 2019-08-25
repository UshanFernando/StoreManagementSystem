package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Brand;
import model.Product;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;


public class BrandManagerService implements BrandManagerServiceInterface {

    private static Connection connection;
    private ResultSet myRs = null;
    private static Statement myStmt = null;
    private static PreparedStatement preparedStatement;

    /**
     * default constructor
     */
    public BrandManagerService() {

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
            myStmt.executeUpdate(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_BRAND_TABLE));
            System.out.println("Brands Table Created");
        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
            System.out.println("Brands Table Not Created");

        }
    }


    @Override
    public ObservableList<Brand> getBrandsList() {
        ObservableList<Brand>  brands = FXCollections.observableArrayList();
        // TODO Auto-generated method stub
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_BRANDS));


            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                int id = myRs.getInt("bid");
                String name = myRs.getString("name");
                String status = myRs.getString("status");


                // create Brand object
                Brand currentBrand = new Brand(id, name, status);

                // adding Brand object to list
                brands.add(currentBrand);

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return brands;
    }

    /**
     * This Function will return a particular user by id
     *
     * @return User
     */
    @Override
    public Brand getBrandById(int userId) {
        // TODO Auto-generated method stub
        Brand brand = null;
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_BRAND_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, userId);
            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                int id = myRs.getInt("bid");
                String name = myRs.getString("name");
                String status = myRs.getString("status");


                // create product object
                brand = new Brand(id, name,status);

            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return brand;
    }


    @Override
    public void updateBrand(Brand brand) {
        // TODO Auto-generated method stub

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_BRAND));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, brand.getId());
            preparedStatement.setString(Constants.COLUMN_INDEX_TWO, brand.getName());
            preparedStatement.setString(Constants.COLUMN_INDEX_THREE, brand.getStatus());
            preparedStatement.setInt(Constants.COLUMN_INDEX_FOUR, brand.getId());
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



    @Override
    public boolean addBrand(Brand brand) {
        // TODO Auto-generated method stub
        boolean success = false;


        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_BRAND));
            preparedStatement.setString(Constants.COLUMN_INDEX_ONE, brand.getName());
            preparedStatement.setString(Constants.COLUMN_INDEX_TWO, brand.getStatus());
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
    public void removeBrand(int bid) {
        // TODO Auto-generated method stub

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_REMOVE_BRAND));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, bid);
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
