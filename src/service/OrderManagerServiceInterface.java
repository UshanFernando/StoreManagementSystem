package service;

import javafx.collections.ObservableList;
import model.Order;

/**
 * @author Ilukkumbure S.P.M.K.W
 * @author IT18022902
 * This is OrderManagerServiceInterface
 * @see OrderManagerService
 */
public interface OrderManagerServiceInterface {
    public ObservableList<Order> getOrderList();

    public Order getOrderById(int oid);

    public  void updateOrder(Order order);

    public  boolean addOrder(Order order);

    public void removeOrder();
}
