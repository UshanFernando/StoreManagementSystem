package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import model.Order;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;


/**
 * @author Ilukkumbure S.P.M.K.W
 * @author IT18022902
 * This is SupplierManagerService
 * @see SupplierManagerServiceInterface
 * @see Order
 * @see Product
 * @see Constants
 * @see DBConnection
 * @see QueryUtil
 */
public class OrderManagerService implements OrderManagerServiceInterface {

    private static Connection connection;
    private ResultSet myresultSet = null;
    private static Statement mystatement = null;
    private static PreparedStatement preparedStatement;
    private ProductManagerService productManagerService;
    private SupplierManagerService supplierManagerService;


    public OrderManagerService() {
    }

    static {
        createTableOrders();
    }

    private static void createTableOrders() {
        try {
            connection = DBConnection.getDBConnection();
            mystatement = connection.createStatement();

            mystatement.execute(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_ORDERS_TABLE));
        } catch (IOException | SQLException | ClassNotFoundException | SAXException | ParserConfigurationException e) {
//            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<OrderItem> getOrderList() {
        ObservableList<OrderItem> orders = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_ALL_ORDERS));
            myresultSet = preparedStatement.executeQuery();

            while (myresultSet.next()) {
                int id = myresultSet.getInt("id");
//                String product = myresultSet.getString("product");
//                String qty = myresultSet.getString("qty");
//                String vendor = myresultSet.getString("vendor");
                productManagerService = new ProductManagerService();
                supplierManagerService = new SupplierManagerService();

                Product product = productManagerService.getProductById(myresultSet.getInt("product"));

                String qty = myresultSet.getString("qty");
                Supplier vendor = supplierManagerService.getSupplierById(myresultSet.getInt("vendor"));
                String orderDate = myresultSet.getString("orderDate");
                String deliveryDate = myresultSet.getString("deliveryDate");
                String status = myresultSet.getString("status");

//                Timestamp deliverydate = Timestamp.valueOf((LocalDate.parse(deliveryDate)).atStartOfDay());
//                Timestamp currentdate = Timestamp.valueOf((LocalDate.now()).atTime(LocalTime.now()));
//

                Order currentOrder = new Order(id, product, qty, vendor, orderDate, deliveryDate, status);
                OrderItem currentOrderItem = new OrderItem(currentOrder,product,vendor);
                orders.add(currentOrderItem);
            }
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public OrderItem getOrderById(int oid) {
        Order orders = null;
        OrderItem orderItem = null;
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_ORDER_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, oid);
            myresultSet = preparedStatement.executeQuery();

            while (myresultSet.next()) {
                int id = myresultSet.getInt("id");
                Product product = productManagerService.getProductById(myresultSet.getInt("product"));
                String qty = myresultSet.getString("qty");
                Supplier vendor = supplierManagerService.getSupplierById(myresultSet.getInt("vendor"));
                String orderDate = myresultSet.getString("orderDate");
                String deliveryDate = myresultSet.getString("deliveryDate");
                String status = myresultSet.getString("status");

                 orders = new Order(id, product, qty, vendor, orderDate, deliveryDate, status);
                 orderItem = new OrderItem(orders,product,vendor);
            }
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public boolean updateOrder(OrderItem order) {
        boolean success = false;
        try{
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_ORDER));
            makeQuery(order);
            preparedStatement.setInt(Constants.COLUMN_INDEX_EIGHT, order.getOrderID());

        }catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean addOrder(OrderItem orderItem) {
        boolean success = false;
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement =  connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_ORDER));
            makeQuery(orderItem);
            preparedStatement.execute();
            success = true;
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }
        return success;
    }

    @Override
    public boolean removeOrder(int oid) {
        boolean success = false;
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_REMOVE_SUPPLIER));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, oid);
            preparedStatement.execute();
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }
        return success;
    }


    private void makeQuery(OrderItem orderItem) throws SQLException {
        preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, orderItem.getOrderID());
        preparedStatement.setInt(Constants.COLUMN_INDEX_TWO, orderItem.getProduct().getPid());
        preparedStatement.setString(Constants.COLUMN_INDEX_THREE, orderItem.getQty());
        preparedStatement.setInt(Constants.COLUMN_INDEX_FOUR, orderItem.getSupplier().getId());
        preparedStatement.setString(Constants.COLUMN_INDEX_FIVE,orderItem.getOrderDate());
        preparedStatement.setString(Constants.COLUMN_INDEX_SIX,orderItem.getDeliveryDate());
        preparedStatement.setString(Constants.COLUMN_INDEX_SEVEN,orderItem.getRequest());

    }




    private void connectionClose() {
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
