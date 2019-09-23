package service;

import javafx.collections.ObservableList;
import model.Sale;
import model.ShoppingCart;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

/**
 * @author Fernando G.U.S
 * @author IT18027884
 * <p>
 * This is SalesManagerService
 * @see DBConnection
 * @see QueryUtil
 * @see Constants
 */

public interface SaleManagerServiceInterface {

    public ObservableList<Sale> getSalesList();

    public Sale getSaleById(int pid);

    public boolean addSale(ShoppingCart cart, Sale sale);

//    public void updateBrand(Brand user);

    public void removeSale(int sid);
}
