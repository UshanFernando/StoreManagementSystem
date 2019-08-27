package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Salary;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;


public class SalaryCalculatorManagerService implements SalaryCalculatorServiceInterface {

    private static Connection connection;
    private ResultSet myRs = null;
    private static Statement myStmt = null;
    private static PreparedStatement preparedStatement;

    /**
     * default constructor
     */
    public SalaryCalculatorManagerService() {

    }

    static {
        //This will call createTable() when class loads.
        createTable();
    }

    /**
     * This static method will generate brands table
     */
    public static void createTable() {

        try {
            connection = DBConnection.getDBConnection();
            myStmt = connection.createStatement();

            // Create new products table as per SQL query available in
            myStmt.executeUpdate(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_SALARY_TABLE));
            System.out.println("Salary Table Created");
        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
            System.out.println("Salary Table Not Created");

        }
    }


    @Override
    public ObservableList<Salary> getSalaryList() {
        ObservableList<Salary>  salary = FXCollections.observableArrayList();
        // TODO Auto-generated method stub
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_SALARY));


            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                Integer sID = myRs.getInt("sID");
                String eID = myRs.getString("eID");
                String date = myRs.getString("date");
                double deduction = myRs.getDouble("deduction");
                double bonus = myRs.getDouble("bonus");
                double overtime = myRs.getDouble("overtime");
                double basic = myRs.getDouble("basic");
                double total = myRs.getDouble("total");



                // create salary object
                Salary currentSalary = new Salary(sID, eID, date, deduction, bonus, overtime, basic, total);

                // adding Brand object to list
                salary.add(currentSalary);

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return salary;
    }

    /**
     * This Function will return a particular user by id
     *
     * @return User
     */
    @Override
    public Salary getSalaryById(Integer sId) {
        // TODO Auto-generated method stub
        Salary salary = null;
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_SALARY_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, sId);
            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                Integer sID = myRs.getInt("sID");
                String eID = myRs.getString("eID");
                String date = myRs.getString("date");
                double deduction = myRs.getDouble("deduction");
                double bonus = myRs.getDouble("bonus");
                double overtime = myRs.getDouble("overtime");
                double basic = myRs.getDouble("basic");
                double total = myRs.getDouble("total");



                // create product object
                salary = new Salary(sID, eID, date, deduction, bonus, overtime, basic, total);

            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return salary;
    }






    @Override
    public boolean addSalary(Salary salary) {
        // TODO Auto-generated method stub
        boolean success = false;


        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_SALARY));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, salary.getsID());
            preparedStatement.setString(Constants.COLUMN_INDEX_TWO, salary.geteID());
            preparedStatement.setDouble(Constants.COLUMN_INDEX_THREE, salary.getBonus());
            preparedStatement.setDouble(Constants.COLUMN_INDEX_FOUR, salary.getOvertime());
            preparedStatement.setDouble(Constants.COLUMN_INDEX_FIVE, salary.getBasic());
            preparedStatement.setDouble(Constants.COLUMN_INDEX_SIX, salary.getDeduction());
            preparedStatement.setString(Constants.COLUMN_INDEX_SEVEN, salary.getDate());
            preparedStatement.setDouble(Constants.COLUMN_INDEX_EIGHT, salary.getTotal());
            preparedStatement.execute();

        } catch (SQLException | SAXException | IOException | ParserConfigurationException
                | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
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


        return success;
    }




    @Override
    public void removeSalary(Integer sid) {


        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_REMOVE_SALARY));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, sid);
            preparedStatement.execute();

        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
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

}

