package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Brand;
import model.Category;
import model.Product;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;


public class ProductManagerService implements ProductManagerServiceInterface {

    private static Connection connection;
    private ResultSet myRs = null;
    private static Statement myStmt = null;
    private static PreparedStatement preparedStatement;
    private BrandManagerService brandManagerService;
    private CategoryManagerService categoryManagerService;

    /**
     * default constructor
     */
    public ProductManagerService() {

     brandManagerService=   new BrandManagerService();
     categoryManagerService =  new CategoryManagerService();

    }

    static {
        //This will call craeteUsersTable() when class loads.
        createProductsTable();
        BrandManagerService.createTable();
        CategoryManagerService.createTable();
    }

    /**
     * This static method will generate products table
     */
    public static void createProductsTable() {

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
    public ObservableList<Product> getProductsList() {
        ObservableList<Product> products = FXCollections.observableArrayList();
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
                Brand brand = brandManagerService.getBrandById(myRs.getInt("brand"));
                Category category = categoryManagerService.getCategoryById(myRs.getInt("category"));
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
                Brand brand = brandManagerService.getBrandById(myRs.getInt("brand"));
                Category category = categoryManagerService.getCategoryById(myRs.getInt("category"));
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
    public boolean updateProduct(Product product) {

        boolean success = false;

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_PRODUCT));
            makeQuery(product);
            preparedStatement.setInt(Constants.COLUMN_INDEX_SEVEN,product.getPid());
            preparedStatement.executeUpdate();
            success = true;

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
        System.out.println(success);
        return success;
    }



    @Override
    public boolean addProduct(Product product) {
        // TODO Auto-generated method stub
        boolean success ;
//		if (!emailCheck(product.getEmail())) {
//			// this code will only run if entered email is not already in DB
//			success = true;

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_PRODUCT));
            makeQuery(product);
            preparedStatement.execute();
            success = true;

        } catch (SQLException | SAXException | IOException | ParserConfigurationException
                | ClassNotFoundException e) {
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

    private void makeQuery(Product product) throws SQLException {
        preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, product.getPid());
        preparedStatement.setString(Constants.COLUMN_INDEX_TWO, product.getName());
        preparedStatement.setDouble(Constants.COLUMN_INDEX_THREE, product.getPrice());
        preparedStatement.setInt(Constants.COLUMN_INDEX_FOUR, product.getBrand().getId());
        preparedStatement.setInt(Constants.COLUMN_INDEX_FIVE, product.getCategory().getId());
        preparedStatement.setInt(Constants.COLUMN_INDEX_SIX, product.getQty());
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



}
