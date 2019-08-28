package service;

import javafx.collections.ObservableList;
import model.Product;
import model.Employee;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;



public interface EmployeeManagerServiceInterface {

    public ObservableList<Employee> getEmployeesList();

    public Employee getEmployeeById(int empId);

    //public void updateEmployee(Employee user);

    public boolean addEmployee(Employee user);

    public void removeEmployee(int empId);
}
