package service;

import java.util.List;

import javafx.collections.ObservableList;
import model.*;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

/**
 * @author Fernando G.U.S
 * @author IT18027884
 * <p>
 * This is ProductManagerService
 * @see DBConnection
 * @see QueryUtil
 * @see Constants
 */

public interface ProductManagerServiceInterface {

    public ObservableList<Product> getProductsList();

    public Product getProductById(int pid);

    public boolean updateProduct(Product product , int pid);

    public boolean addProduct(Product product);

    public void removeProduct(int pid);

}
