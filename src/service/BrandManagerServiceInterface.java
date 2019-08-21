package service;

import javafx.collections.ObservableList;
import model.Brand;
import model.Product;
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

public interface BrandManagerServiceInterface {

    public ObservableList<Brand> getBrandsList();

    public Brand getBrandById(int pid);

    public void updateBrand(Brand user);

    public boolean addBrand(Brand user);

    public void removeBrand(int pid);
}
