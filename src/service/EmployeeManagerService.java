package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;


public class EmployeeManagerService implements EmployeeManagerServiceInterface {

    private static Connection connection;
    private ResultSet myRs = null;
    private static Statement myStmt = null;
    private static PreparedStatement preparedStatement;

    /**
     * default constructor
     */
    public EmployeeManagerService() {

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
            myStmt.executeUpdate(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_EMPLOYEE_TABLE));
            System.out.println("Employee Table Created");
        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
            System.out.println("Employee Table Not Created");

        }
    }


    @Override
    public ObservableList<Employee> getEmployeesList() {
        ObservableList<Employee>  employees = FXCollections.observableArrayList();
        // TODO Auto-generated method stub
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_EMPLOYEE));


            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                int empId = myRs.getInt("empId");
                String name = myRs.getString("name");
                String department = myRs.getString("department"); ;
                int level = myRs.getInt("level");;
                int contact = myRs.getInt("contact");;
                String gender = myRs.getString("gender");;
                String Address = myRs.getString("Address");;
                String recdate = myRs.getString("recdate");;


                // create eMPLOYEE object
                Employee currentBrand = new Employee(empId, name, department,level,contact,gender,Address,recdate);

                // adding EMPLOYEE object to list
                employees.add(currentBrand);

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return employees;
    }

    /**
     * This Function will return a particular user by id
     *
     * @return User
     */
    @Override
    public Employee getEmployeeById(int userId) {
        // TODO Auto-generated method stub
        Employee employee = null;
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_EMPLOYEE_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, userId);
            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                int empId = myRs.getInt("empId");
                String name = myRs.getString("name");
                String department = myRs.getString("department"); ;
                int level = myRs.getInt("level");;
                int contact = myRs.getInt("contact");;
                String gender = myRs.getString("gender");;
                String Address = myRs.getString("Address");;
                String recdate = myRs.getString("recdate");;


                // create product object
                employee = new Employee(empId, name, department,level,contact,gender,Address,recdate);

            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return employee;
    }



    @Override
    public void updateEmployee(Employee employee) {
        // TODO Auto-generated method stub

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_EMPLOYEE));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, employee.getEmpId());
            preparedStatement.setString(Constants.COLUMN_INDEX_TWO, employee.getName());
            preparedStatement.setString(Constants.COLUMN_INDEX_THREE, employee.getDepartment());
            preparedStatement.setInt(Constants.COLUMN_INDEX_FOUR, employee.getLevel());
            preparedStatement.setInt(Constants.COLUMN_INDEX_FIVE, employee.getContact());
            preparedStatement.setString(Constants.COLUMN_INDEX_SIX, employee.getGender());
            preparedStatement.setString(Constants.COLUMN_INDEX_SEVEN, employee.getAddress());
            preparedStatement.setString(Constants.COLUMN_INDEX_EIGHT, employee.getRecdate());
            preparedStatement.setInt(9, employee.getEmpId());
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();

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


    @Override
    public boolean addEmployee(Employee employee) {
        // TODO Auto-generated method stub
        boolean success = false;


        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_EMPLOYEE));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, employee.getEmpId());
            preparedStatement.setString(Constants.COLUMN_INDEX_TWO, employee.getName());
            preparedStatement.setString(Constants.COLUMN_INDEX_THREE, employee.getDepartment());
            preparedStatement.setInt(Constants.COLUMN_INDEX_FOUR, employee.getLevel());
            preparedStatement.setInt(Constants.COLUMN_INDEX_FIVE, employee.getContact());
            preparedStatement.setString(Constants.COLUMN_INDEX_SIX, employee.getGender());
            preparedStatement.setString(Constants.COLUMN_INDEX_SEVEN, employee.getAddress());
            preparedStatement.setString(Constants.COLUMN_INDEX_EIGHT, employee.getRecdate());


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
    public void removeEmployee(int empId) {
        // TODO Auto-generated method stub

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_REMOVE_EMPLOYEE));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE,empId);


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
