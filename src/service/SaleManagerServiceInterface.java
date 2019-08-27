package service;

import javafx.collections.ObservableList;
import model.Brand;
import model.Sale;
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

//    public Sale getSaleById(int pid);

//    public void updateBrand(Brand user);

    public boolean addSale(Sale sale);

    public void removeSale(int sid);
}
