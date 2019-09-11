package service;

import javafx.collections.ObservableList;
import model.Finance;
import model.Requests;
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

public interface RequestsManagerServiceInterface {

    public ObservableList<Requests> getRequestsList();

    public Finance getRequestsById(int pid);

    public void updateRequests(Finance user);

    public boolean addRequests(Finance user);

    public void removeRequests(int pid);
}
