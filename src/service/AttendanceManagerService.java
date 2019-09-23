package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Attendance;
import org.xml.sax.SAXException;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;

import static javafx.collections.FXCollections.*;


public class AttendanceManagerService implements AttendanceManagerServiceInterface {

    private static Connection connection;
    private ResultSet myRs = null;
    private static Statement myStmt = null;
    private static PreparedStatement preparedStatement;

    /**
     * default constructor
     */
    public AttendanceManagerService() {

    }

    static {
        //This will call createTable() when class loads.
        createTable();
    }

    /**
     * This static method will generate attendance table
     */
    public static void createTable() {

        try {
            connection = DBConnection.getDBConnection();
            myStmt = connection.createStatement();

            // Create new products table as per SQL query available in
            myStmt.executeUpdate(QueryUtil.queryByID(Constants.QUERY_ID_CREATE_ATTENDANCE_TABLE));

        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
            System.out.println("No need to create table");

        }
    }


    @Override
    public ObservableList<Attendance> getAttendanceList() {
        ObservableList<Attendance> attendances = FXCollections.observableArrayList();
        // TODO Auto-generated method stub
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_ATTENDANCE));


            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                // GET data from db
                int empid = myRs.getInt("EmployeeID");

                String name = myRs.getString("Name");
                String month = myRs.getString("Month");
                int year = myRs.getInt("Year");
                int No = myRs.getInt("NoOfAttendance");
                //int test = myRs.getInt("EmployeeID");
                // create Brand object
                Attendance currentAttendance = new Attendance(empid, name, month, year, No);

                currentAttendance.getEmployeeId();

                // adding Brand object to list
                attendances.add(currentAttendance);

            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return attendances;
    }

    /**
     * This Function will return a particular user by id
     *
     * @return User
     */
    @Override
    public Attendance getAttendanceById(int userId) {
        // TODO Auto-generated method stub
        Attendance attendance = null;
        try {

            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_GET_ATTENDANCE_BY_ID));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, userId);
            myRs = preparedStatement.executeQuery();

            while (myRs.next()) {
                int employeeId = myRs.getInt("EmployeeID");
                String name = myRs.getString("Name");
                String month = myRs.getString("Month");
                int year = myRs.getInt("Year");
                int noOfAttendance = myRs.getInt("NoOfAttendance");
                // create product object
                attendance = new Attendance(employeeId, name, month, year, noOfAttendance);

            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return attendance;
    }






    @Override
    public void updateAttendance(Attendance attendance){
//        // TODO Auto-generated method stub

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_ATTENDANCE));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, attendance.getEmployeeId());
            preparedStatement.setString(Constants.COLUMN_INDEX_TWO, attendance.getName());
            preparedStatement.setString(Constants.COLUMN_INDEX_THREE, attendance.getMonth());
            preparedStatement.setInt(Constants.COLUMN_INDEX_FOUR, attendance.getYear());
            preparedStatement.setInt(Constants.COLUMN_INDEX_FIVE, attendance.getNoOfAttendance());
            preparedStatement.setInt(Constants.COLUMN_INDEX_SIX, attendance.getEmployeeId());




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


    private void makeAttendanceQuery (Attendance attendance) throws
            ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException {
        connection = DBConnection.getDBConnection();
        preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_UPDATE_ATTENDANCE));
        preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, attendance.getEmployeeId());
        preparedStatement.setString(Constants.COLUMN_INDEX_TWO, attendance.getName());
        preparedStatement.setString(Constants.COLUMN_INDEX_THREE, attendance.getMonth());
        preparedStatement.setInt(Constants.COLUMN_INDEX_FOUR, attendance.getYear());
        preparedStatement.setInt(Constants.COLUMN_INDEX_FIVE, attendance.getNoOfAttendance());


    }

    @Override
    public boolean addAttendance (Attendance attendance){
        // TODO Auto-generated method stub
        boolean success = false;
        //if (!emailCheck(brand.getEmail())) {
        // this code will only run if entered email is not already in DB
        //	success = true;

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_ADD_ATTENDANCE));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, attendance.getEmployeeId());
            preparedStatement.setString(Constants.COLUMN_INDEX_TWO, attendance.getName());
            preparedStatement.setString(Constants.COLUMN_INDEX_THREE, attendance.getMonth());
            preparedStatement.setInt(Constants.COLUMN_INDEX_FOUR, attendance.getYear());
            preparedStatement.setInt(Constants.COLUMN_INDEX_FIVE, attendance.getNoOfAttendance());

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
    public void removeAttendance ( int fid){
        // TODO Auto-generated method stub

        try {
            connection = DBConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(QueryUtil.queryByID(Constants.QUERY_ID_REMOVE_ATTENDANCE));
            preparedStatement.setInt(Constants.COLUMN_INDEX_ONE, fid);
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



