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

    public OrderItem getOrderById(int oid);

    public  boolean updateOrder(OrderItem orderItem);

    public  boolean addOrder(OrderItem orderItem);

    public boolean removeOrder(int oid);
}
