package service;

import javafx.collections.ObservableList;
import model.Contact;
import model.Mail;
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

    public boolean updateSupplier(Supplier supplier);

    public boolean addSupplier(Supplier supplier);

    public boolean removeSupplier(int pid);

    public ObservableList<Contact> getContactListByID(int cid);

    public ObservableList<Mail> getEmailListByID(int cid);

    public boolean addMail(Mail mail);

    public boolean updateMail(Mail mail);

    public boolean removeMail(int sid);

    public boolean addContact(Contact contact);

    public boolean updateContact(Contact contact);

    public boolean removeContact(int sid);
}
