package service;

import javafx.collections.ObservableList;
import model.Supplier;


/**
 * @author Ilukkumbure S.P.M.K.W
 * @author IT18022902
 * This is SupplierManagerServiceInterface
 * @see SupplierManagerService
 */
public interface SupplierManagerServiceInterface {
    public ObservableList<Supplier> getSupplierList();

    public Supplier getSupplierById(int sid);

    public void  updateSupplier(Supplier supplier);

    public boolean addSupplier(Supplier supplier);

    public  void  removeSupplier(int pid);
}
