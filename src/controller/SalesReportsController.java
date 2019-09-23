package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Product;
import model.SalesReport;
import model.ShoppingCart;
import model.ShoppingCartItem;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import service.SaleManagerService;
import util.SceneManager;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SalesReportsController extends JFrame  implements Initializable {


    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField noSalesTf;

    @FXML
    private TextField totalSalesTf;

    @FXML
    private TextField discountTf;

    @FXML
    private TextField revenueTf;

    @FXML
    private LineChart totalSalesChart;

    @FXML
    private CategoryAxis salesCategoryAxis;

    @FXML
    private NumberAxis salesNumberAxis;


    private SaleManagerService saleManagerService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        saleManagerService = new SaleManagerService();

        datePicker.setValue(LocalDate.now());

        getReport(LocalDate.now().toString());

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

               getReport(newValue.toString());
            }
        });


        totalSalesChart.setTitle(String.valueOf(LocalDateTime.now().getYear()));
        salesCategoryAxis.setLabel("Month");
        salesNumberAxis.setLabel("Total Sales");

        String[] months = new DateFormatSymbols().getMonths();

        XYChart.Series series = new XYChart.Series();

        for (int x = 0 ; x<12; x++) {
            SalesReport reportMonthly = saleManagerService.getSalesReportMonthly(String.valueOf((x+1))
                    , String.valueOf(LocalDateTime.now().getYear()));
            series.setName(months[x]);
            //populating the series with data
            series.getData().addAll(new XYChart.Data(months[x], reportMonthly.getTotalSales()));
            int value = (int) reportMonthly.getTotalSales();

        }

        totalSalesChart.getData().addAll(series);


    }

    @FXML
    public void openHomeScene() throws IOException {

            SceneManager.manage().openSalesScene();

    }


    public void generateDetailedReport() throws JRException {


        if (datePicker.getValue() != null) {
            ShoppingCart cart = saleManagerService.getProductSalesDaily(datePicker.getValue().toString());


            /* User home directory location */
            String userHomeDirectory = System.getProperty("user.home");

            /* List to hold Items */
            ArrayList<Product> listItems = new ArrayList<Product>();

            for (ShoppingCartItem item : cart.getItems()){

                /* Create Items */
                Product product = new Product();
                product.setName(item.getProductName());
                product.setQty(item.getQuantity());

                listItems.add(product);
            }

            SalesReport salesReport = saleManagerService.getSalesReport(datePicker.getValue().toString());

            /* Convert List to JRBeanCollectionDataSource */
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();

            parameters.put("ItemDataSource", itemsJRBean);
            parameters.put("date", datePicker.getValue().toString());
            parameters.put("totalDiscount", salesReport.getTotalDiscount());
            parameters.put("totalRevenue",salesReport.getTotalSales()-salesReport.getTotalDiscount());
            parameters.put("noOfSales",salesReport.getNoOfSales());
            parameters.put("totalSalesValue",salesReport.getTotalSales());


            /* Using compiled version(.jasper) of Jasper report to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport(userHomeDirectory + "\\IdeaProjects\\StoreManagementSystem\\src\\docs\\sales_report_A4.jasper", parameters, new JREmptyDataSource());


            System.out.println("File Generated");


            JRViewer viewer = new JRViewer(jasperPrint);
            viewer.setOpaque(true);
            viewer.setVisible(true);
            this.add(viewer);
            this.setSize(700, 500);
            this.setVisible(true);
            System.out.print("Done!");
        }

    }


    private void getReport(String date){


        SalesReport salesReport = saleManagerService.getSalesReport(date);
        if (salesReport != null) {

            noSalesTf.setText(String.valueOf(salesReport.getNoOfSales()));
            totalSalesTf.setText(String.valueOf(salesReport.getTotalSales()));
            discountTf.setText(String.valueOf(salesReport.getTotalDiscount()));
            revenueTf.setText(String.valueOf(salesReport.getTotalRevenue()));
        }

    }


}
