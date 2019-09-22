package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Finance;
import net.sf.jasperreports.engine.JRException;
import service.FinanceManagerService;
import util.PrintMonthlyReport;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MonthlyReportController {

    private Finance data;


    @FXML
    public void print() throws JRException, FileNotFoundException {
        PrintMonthlyReport printer =  new PrintMonthlyReport();
        printer.Generate();
    }
}
