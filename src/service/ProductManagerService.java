package service;

import javafx.beans.InvalidationListener;
import javafx.collections.*;
import model.Product;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.util.*;


public class ProductManagerService implements ProductManagerServiceInterface {

    private static Connection connection;
    private ResultSet myRs = null;
    private static Statement myStmt = null;
    private static PreparedStatement preparedStatement;

    /**
     * default constructor
     */
    public ProductManagerService() {

    }

    static {
        //This will call craeteUsersTable() when class loads.
        createUsersTable();
    }

    /**
     * This static method will generate products table
     */
    public static void createUsersTable() {

        try {
            connection = DBConnection.getDBConnection();
            myStmt = connection.createStatement();

            // Create new products table as per SQL query available in
            myStmt.executeUpdate(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_PRODUCT_TABLE));

        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
            System.out.println("No need to create table");

        }
    }


    @Override
    public ObservableList<Product> getProductsList() {
        ObservableList<Product>  products = FXCollections.observableArrayList();
        // TODO Auto-generated method stub
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_PRODUCTS));


            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                int id = myRs.getInt("pid");
                String name = myRs.getString("name");
                Double price = myRs.getDouble("price");
                String brand = myRs.getString("bname");
                String category = myRs.getString("cname");
                int qty = myRs.getInt("qty");

                // create product object
                Product currentProduct = new Product(id, name, price, brand, category, qty);

                // adding user object to list
                products.add(currentProduct);

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return products;
    }

    /**
     * This Function will return a particular user by id
     *
     * @return User
     */
    @Override
    public Product getProductById(int userId) {
        // TODO Auto-generated method stub
        Product product = null;
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_PRODUCT_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, userId);
            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                int id = myRs.getInt("pid");
                String name = myRs.getString("name");
                Double price = myRs.getDouble("price");
                String brand = myRs.getString("brand");
                String category = myRs.getString("category");
                int qty = myRs.getInt("qty");

                // create product object
                product = new Product(id, name, price, brand, category, qty);

            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return product;
    }


    @Override
    public void updateProduct(Product product) {
        // TODO Auto-generated method stub

        try {
            makeProductQuery(product);
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

    private void makeProductQuery(Product product) throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException {
        connection = DBConnection.getDBConnection();
        preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_PRODUCT));
        preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, product.getPid());
        preparedStatement.setString(Constants.COLUMN_INDEX_TWO, product.getName());
        preparedStatement.setDouble(Constants.COLUMN_INDEX_THREE, product.getPrice());
        preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, product.getBrand());
        preparedStatement.setString(Constants.COLUMN_INDEX_FIVE, product.getCategory());
        preparedStatement.setInt(Constants.COLUMN_INDEX_SIX, product.getQty());
    }

    @Override
    public boolean addProduct(Product product) {
        // TODO Auto-generated method stub
        boolean success = false;
//		if (!emailCheck(product.getEmail())) {
//			// this code will only run if entered email is not already in DB
//			success = true;

        try {
            makeProductQuery(product);
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
//		}

        return success;
    }


    @Override
    public void removeProduct(int pid) {
        // TODO Auto-generated method stub

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_REMOVE_PRODUCT));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, pid);
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

    /**
     * This function will check given email is already exists in the DB This
     * function will avoid adding multiple products with same email
     *
     * @param email
     */
//	@Override
//	public boolean emailCheck(String email) {
//		// TODO Auto-generated method stub
//		boolean exists = false;
//		try {
//			connection = DBConnection.getDBConnection();
//			preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_EMAIL_CHECK));
//			preparedStatement.setString(Constants.COLUMN_INDEX_ONE, email);
//			myRs = preparedStatement.executeQuery();
//
//			if (myRs.next()) {
//				exists = true;
//			} else {
//				exists = false;
//			}
//
//		} catch (Exception exc) {
//			exc.printStackTrace();
//		}
//		return exists;
//	}

}
