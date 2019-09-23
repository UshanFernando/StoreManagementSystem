package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
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
        createTableSalesProduct();
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

    public static void createTableSalesProduct() {

        try {
            connection = DBConnection.getDBConnection();
            myStmt = connection.createStatement();

            // Create new products table as per SQL query available in
            myStmt.executeUpdate(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_SALES_PRODUCTS));
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
    public Sale getSaleById(int pid) {
        return null;
    }

    @Override
    public boolean addSale(ShoppingCart cart , Sale sale) {
        // TODO Auto-generated method stub
        boolean state =false;

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_SALES),PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(Constants.COLUMN_INDEX_ONE,sale.getSubTotal());
            preparedStatement.setDouble(Constants.COLUMN_INDEX_TWO,sale.getDiscount());
            preparedStatement.setDouble(Constants.COLUMN_INDEX_THREE,sale.getNetTotal());
            preparedStatement.setDouble(Constants.COLUMN_INDEX_FOUR,sale.getPayment());
            preparedStatement.setTimestamp(Constants.COLUMN_INDEX_FIVE,sale.getTimeStamp());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            int idLast = -1;
            if (rs.next()) {
                 idLast = rs.getInt(1);

            }

            if (idLast > -1) {
                addSalesProduct(cart, idLast);
                state=true;
            }

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
        return state;
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

    public void addSalesProduct(ShoppingCart cart,int saleId) {
        // TODO Auto-generated method stub

        for (ShoppingCartItem item : cart.getItems()) {
            try {
                connection = DBConnection.getDBConnection();
                preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_SALES_PRODUCTS));
                preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, item.getProduct().getPid());
                preparedStatement.setInt(Constants.COLUMN_INDEX_TWO, saleId);
                preparedStatement.setInt(Constants.COLUMN_INDEX_THREE, item.getQuantity());
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

    public SalesReport getSalesReport(String day) {
        SalesReport currentReport = null;
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_SALES_REPORT));
            preparedStatement.setString(Constants.COLUMN_INDEX_ONE,day);

            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                int salesCount = myRs.getInt("noOfSales");
                double totalSales = myRs.getDouble("totalSales");
                double totalDiscount = myRs.getDouble("totalDiscount");


                // create Brand object
                currentReport = new SalesReport(salesCount, totalSales, totalDiscount);



            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return currentReport;
    }

    public SalesReport getSalesReportMonthly(String month,String year) {
        SalesReport currentReport = null;
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_SALES_REPORT_MONTHLY));
            preparedStatement.setString(Constants.COLUMN_INDEX_ONE,month);
            preparedStatement.setString(Constants.COLUMN_INDEX_TWO,year);

            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                int salesCount = myRs.getInt("noOfSales");
                double totalSales = myRs.getDouble("totalSales");
                double totalDiscount = myRs.getDouble("totalDiscount");


                // create Brand object
                currentReport = new SalesReport(salesCount, totalSales, totalDiscount);



            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return currentReport;
    }


    public ShoppingCart getProductSalesDaily(String date) {
        ShoppingCart  cart = new ShoppingCart();
        // TODO Auto-generated method stub
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_PRODUCT_SALES_DAILY));
            preparedStatement.setString(Constants.COLUMN_INDEX_ONE,date);

            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                String name = myRs.getString("product");
                int qty = myRs.getInt("amount");

                // create Brand object
                ShoppingCartItem item =  new ShoppingCartItem();
                item.setProductName(name);
                item.setQuantity(qty);

                // adding Brand object to list
                cart.addItem(item);

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return cart;
    }

}
