package service;

import javafx.collections.ObservableList;
import model.Order;
import model.OrderItem;

/**
 * @author Ilukkumbure S.P.M.K.W
 * @author IT18022902
 * This is OrderManagerServiceInterface
 * @see OrderManagerService
 */
public interface OrderManagerServiceInterface {
    public ObservableList<OrderItem> getOrderList();

    public Order getOrderById(int oid);

    public  boolean updateOrder(Order order);

    public  boolean addOrder(Order order);

    public boolean removeOrder(int oid);
}
