package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Category;
import model.Contact;
import model.Mail;
import model.Supplier;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;

/**
 * @author Ilukkumbure S.P.M.K.W
 * @author IT18022902
 * This is SupplierManagerService
 * @see SupplierManagerServiceInterface
 * @see Supplier
 * @see model.Contact
 * @see model.Mail
 * @see util.Constants
 * @see util.DBConnection
 * @see util.QueryUtil
 */
public class SupplierManagerService implements SupplierManagerServiceInterface {

    private static Connection connection;
    private ResultSet myresultSet = null;
    private static Statement mystatement = null;
    private static PreparedStatement preparedStatement;
    private CategoryManagerService categoryManagerService;

    public SupplierManagerService() {
        categoryManagerService = new CategoryManagerService();
    }

    static {
        createTableSupplier();
        createTableContact();
        createTableMail();
        BrandManagerService.createTable();
    }

    private static void createTableSupplier() {
        try {
            connection = DBConnection.getDBConnection();
            mystatement = connection.createStatement();

            mystatement.execute(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_SUPPLIER_TABLE));
        } catch (IOException | SQLException | ClassNotFoundException | SAXException | ParserConfigurationException e) {
//            e.printStackTrace();
        }
    }

    public static void createTableContact() {
        try {
            connection = DBConnection.getDBConnection();
            mystatement = connection.createStatement();

            mystatement.execute(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_SUPPLIER_CONTACT_TABLE));
        } catch (IOException | SQLException | ClassNotFoundException | SAXException | ParserConfigurationException e) {
//            e.printStackTrace();
        }
    }

    public static void createTableMail() {
        try {
            connection = DBConnection.getDBConnection();
            mystatement = connection.createStatement();

            mystatement.execute(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_SUPPLIER_EMAIL_TABLE));
        } catch (IOException | SQLException | ClassNotFoundException | SAXException | ParserConfigurationException e) {
//           e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Supplier> getSupplierList() {
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_SUPPLIERS));
            myresultSet = preparedStatement.executeQuery();

            while (myresultSet.next()) {
                int id = myresultSet.getInt("sid");
                String vendor = myresultSet.getString("name");
                Category category = categoryManagerService.getCategoryById(myresultSet.getInt("cid"));
                String address = myresultSet.getString("address");

                Supplier currentSupplier = new Supplier(id, vendor, category, address);
                suppliers.add(currentSupplier);
            }
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    @Override
    public ObservableList<Contact> getContactListByID(int cid) {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_SUPPLIER_CONTACTS_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, cid);
            myresultSet = preparedStatement.executeQuery();

            while (myresultSet.next()) {
                int id = myresultSet.getInt("id");
                String phone = myresultSet.getString("phone");


                Contact currentContact = new Contact(id, phone);
                contacts.add(currentContact);
            }
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public ObservableList<Mail> getEmailListByID(int cid) {
        ObservableList<Mail> eMail = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_SUPPLIER_EMAIL_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, cid);
            myresultSet = preparedStatement.executeQuery();

            while (myresultSet.next()) {
                int id = myresultSet.getInt("id");
                String email = myresultSet.getString("email");


                Mail currentEmail = new Mail(id, email);
                eMail.add(currentEmail);
            }
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return eMail;
    }

    @Override
    public boolean addMail(Mail mail) {
        boolean success = false;
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_SUPPLIER_EMAIL));
            makeQueryMail(mail);
            preparedStatement.setInt(Constants.COLUMN_INDEX_THREE, mail.getId());
//            preparedStatement.executeUpdate();
            preparedStatement.execute();
            success = true;
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }
        return success;

    }

    @Override
    public boolean updateMail(Mail mail) {
        boolean success = false;
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_SUPPLIER_EMAIL));
            makeQueryMail(mail);
            preparedStatement.setInt(Constants.COLUMN_INDEX_THREE, mail.getId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }
        return success;
    }

    @Override
    public boolean removeMail(int sid) {
        boolean success = false;
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_REMOVE_SUPPLIER));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, sid);
            preparedStatement.execute();
            success = true;
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }
        return success;
    }

    @Override
    public boolean addContact(Contact contact) {
        boolean success = false;
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_SUPPLIER_CONTACT));
            makeQueryContact(contact);
            preparedStatement.setInt(Constants.COLUMN_INDEX_THREE, contact.getId());
//            preparedStatement.executeUpdate();
            preparedStatement.execute();
            success = true;
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }
        return success;
    }

    @Override
    public boolean updateContact(Contact contact) {
        boolean success = false;
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_SUPPLIER_CONTACT));
            makeQueryContact(contact);
            preparedStatement.setInt(Constants.COLUMN_INDEX_FIVE, contact.getId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }
        return success;
    }

    @Override
    public boolean removeContact(int sid) {
        return false;
    }


    @Override
    public Supplier getSupplierById(int sid) {
        Supplier suppliers = null;

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_SUPPLIER_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, sid);
            myresultSet = preparedStatement.executeQuery();

            while (myresultSet.next()) {
                int id = myresultSet.getInt("sid");
                String vendor = myresultSet.getString("name");
                Category category = categoryManagerService.getCategoryById(myresultSet.getInt("category"));
                String address = myresultSet.getString("address");

                suppliers = new Supplier(id, vendor, category, address);
            }
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        boolean success = false;
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_SUPPLIER));
            makeQuery(supplier);
            preparedStatement.setInt(Constants.COLUMN_INDEX_FIVE, supplier.getId());
            preparedStatement.executeUpdate();
            success = true;
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }
        return success;
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        boolean success = false;
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_SUPPLIER));
            makeQuery(supplier);
//            preparedStatement.setInt(Constants.COLUMN_INDEX_FOUR, supplier.getId());
//            preparedStatement.executeUpdate();
            preparedStatement.execute();
            success = true;
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }
        return success;
    }

    @Override
    public boolean removeSupplier(int sid) {
        boolean success = false;
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_REMOVE_SUPPLIER));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, sid);
            preparedStatement.execute();
            success = true;
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            connectionClose();
        }
        return success;
    }



    private int getLastIdOfSupplier() {
        int id = -1;
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_LAST_SUPPLIER_ID));
            myresultSet = preparedStatement.executeQuery();
            id = myresultSet.getInt("sid");
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        return id;
    }

    private void makeQuery(Supplier supplier) throws SQLException {
        preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, supplier.getId());
        preparedStatement.setString(Constants.COLUMN_INDEX_TWO, supplier.getVendor());
        preparedStatement.setInt(Constants.COLUMN_INDEX_THREE, supplier.getCategory().getId());
        preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, supplier.getAddress());

    }

    private void makeQueryContact(Contact contact) throws SQLException{
        preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, contact.getId());
        preparedStatement.setString(Constants.COLUMN_INDEX_TWO, contact.getPhone());
    }

    private void makeQueryMail(Mail mail) throws SQLException{
        preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, mail.getId());
        preparedStatement.setString(Constants.COLUMN_INDEX_TWO, mail.getEmail());
    }

    private void connectionClose() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
