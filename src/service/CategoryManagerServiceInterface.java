package service;

import javafx.collections.ObservableList;
import model.Brand;
import model.Category;
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

public interface CategoryManagerServiceInterface {

    public ObservableList<Category> getCategoriesList();

    public Category getCategoryById(int pid);

    public void updateCategory(Category user);

    public boolean addCategory(Category user);

    public void removeCategory(int pid);
}
