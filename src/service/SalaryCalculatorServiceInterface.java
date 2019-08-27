package service;

import javafx.collections.ObservableList;
import model.Salary;
import util.Constants;
import util.DBConnection;
import util.QueryUtil;

/**

 * @see QueryUtil
 * @see Constants
 */

public interface SalaryCalculatorServiceInterface {

    public ObservableList<Salary> getSalaryList();

    public Salary getSalaryById(Integer sId);



    public boolean addSalary(Salary salary);

    public void removeSalary(Integer sid);
}
