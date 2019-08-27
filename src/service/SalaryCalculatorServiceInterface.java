package service;

import javafx.collections.ObservableList;
import model.Salary;
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

public interface SalaryCalculatorServiceInterface {

    public ObservableList<Salary> getSalaryList();

    public Salary getSalaryById(Integer sId);

//    public void updateBrand(Salary sal);

    public boolean addSalary(Salary salary);

//    public void removeSalary(Integer sid);
}
