package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public SupplierManagerService() {
    }
    static {
        createTableSupplier();
        createTableContact();
        createTableMail();
    }
    private static void createTableSupplier(){
        try {
            connection = DBConnection.getDBConnection();
            mystatement = connection.createStatement();

            mystatement.execute(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_SUPPLIER_TABLE));
        } catch (IOException | SQLException | ClassNotFoundException | SAXException | ParserConfigurationException e) {
//            e.printStackTrace();
        }
    }

    public  static  void createTableContact(){
        try {
            connection = DBConnection.getDBConnection();
            mystatement = connection.createStatement();

            mystatement.execute(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_SUPPLIER_CONTACT_TABLE));
        } catch (IOException | SQLException | ClassNotFoundException | SAXException | ParserConfigurationException e) {
//            e.printStackTrace();
        }
    }

    public  static  void createTableMail(){
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

            while (myresultSet.next()){
                int id = myresultSet.getInt("sid");
                String vendor = myresultSet.getString("name");
                String category = myresultSet.getString("cname");
                String address = myresultSet.getString("address");

                Supplier currentSupplier = new Supplier(id,vendor,category,address);
                suppliers.add(currentSupplier);
            }
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    @Override
    public ObservableList<Contact> getContactListByID(int cid){
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_SUPPLIER_CONTACTS_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE,cid);
            myresultSet = preparedStatement.executeQuery();

            while (myresultSet.next()){
                int id = myresultSet.getInt("id");
                String phone = myresultSet.getString("phone");


                Contact currentContact = new Contact(id,phone);
                contacts.add(currentContact);
            }
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public ObservableList<Mail> getEmailListByID(int cid){
        ObservableList<Mail> eMail = FXCollections.observableArrayList();
        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_SUPPLIER_EMAIL_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE,cid);
            myresultSet = preparedStatement.executeQuery();

            while (myresultSet.next()){
                int id = myresultSet.getInt("id");
                String email = myresultSet.getString("email");


                Mail currentEmail = new Mail(id,email);
                eMail.add(currentEmail);
            }
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return eMail;
    }


    @Override
    public Supplier getSupplierById(int sid) {
        Supplier suppliers = null;

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_SUPPLIER_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE,sid);
            myresultSet = preparedStatement.executeQuery();

            while (myresultSet.next()){
                int id = myresultSet.getInt("sid");
                String vendor = myresultSet.getString("name");
                String category = myresultSet.getString("cname");
                String address = myresultSet.getString("address");

                suppliers = new Supplier(id,vendor,category,address);
            }
        } catch (IOException | ClassNotFoundException | SQLException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    @Override
    public void updateSupplier(Supplier supplier) {

    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        return false;
    }

    @Override
    public void removeSupplier(int pid) {

    }
}
