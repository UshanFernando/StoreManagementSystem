package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Brand;
import model.Category;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;


public class CategoryManagerService implements CategoryManagerServiceInterface {

    private static Connection connection;
    private ResultSet myRs = null;
    private static Statement myStmt = null;
    private static PreparedStatement preparedStatement;

    /**
     * default constructor
     */
    public CategoryManagerService() {

    }

    static {
        //This will call createTable() when class loads.
        createTable();
    }

    /**
     * This static method will generate categories table
     */
    public static void createTable() {

        try {
            connection = DBConnection.getDBConnection();
            myStmt = connection.createStatement();

            // Create new Categories table as per SQL query available in
            myStmt.executeUpdate(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_CATEGORIES_TABLE));
            System.out.println("Category Table Created");
        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
            System.out.println("Category Table Not Created");

        }
    }


    @Override
    public ObservableList<Category> getCategoriesList() {
        ObservableList<Category>  categories = FXCollections.observableArrayList();
        // TODO Auto-generated method stub
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_CATEGORIES));


            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                int id = myRs.getInt("cid");
                String name = myRs.getString("name");
                String status = myRs.getString("status");


                // create Category object
                Category currentCategory = new Category(id, name, status);

                // adding Category object to list
                categories.add(currentCategory);

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return categories;
    }


    @Override
    public Category getCategoryById(int cid) {
        // TODO Auto-generated method stub
        Category category = null;
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_CATEGORY_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, cid);
            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                int id = myRs.getInt("cid");
                String name = myRs.getString("name");
                String status = myRs.getString("status");


                // create product object
                category = new Category(id, name,status);

            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return category;
    }


    @Override
    public void updateCategory(Category category) {
        // TODO Auto-generated method stub

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_CATEGORY));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, category.getId());
            preparedStatement.setString(Constants.COLUMN_INDEX_TWO, category.getName());
            preparedStatement.setString(Constants.COLUMN_INDEX_THREE, category.getStatus());
            preparedStatement.setInt(Constants.COLUMN_INDEX_FOUR, category.getId());
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
    public boolean addCategory(Category category) {
        // TODO Auto-generated method stub
        boolean success = false;

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_CATEGORY));
            preparedStatement.setString(Constants.COLUMN_INDEX_ONE, category.getName());
            preparedStatement.setString(Constants.COLUMN_INDEX_TWO, category.getStatus());
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
    public void removeCategory(int cid) {
        // TODO Auto-generated method stub

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_REMOVE_CATEGORY));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, cid);
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
