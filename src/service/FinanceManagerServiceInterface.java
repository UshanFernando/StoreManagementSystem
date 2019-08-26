package service;

import javafx.collections.ObservableList;
import model.Finance;
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

public interface FinanceManagerServiceInterface {

    public ObservableList<Finance> getFinanceList();

    public Finance getFinanceById(int pid);

    public void updateFinance(Finance user);

    public boolean addFinance(Finance user);

    public void removeFinance(int pid);
}
