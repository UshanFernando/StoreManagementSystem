package util;


import model.Product;
import model.Finance;
import model.ShoppingCartItem;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PrintMonthlyReport extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public void Generate() throws JRException, FileNotFoundException {


        /* User home directory location */
        String userHomeDirectory = System.getProperty("user.home");
        System.out.println(userHomeDirectory);

        /* List to hold Items */
        ArrayList<Finance> listItems = new ArrayList<>();

        /* Create Items */
        Finance item = new Finance();

        listItems.add(item);

//        ArrayList<Product> listItems = new ArrayList<Product>();
//        Product item1 = new Product();
//        item1.setName("Name");
//        item1.setPrice(232323.0);
//        item1.setQty(1111);
//
//        Product item2 = new Product();
//        item2.setName("Name");
//        item2.setPrice(232323.0);
//        item2.setQty(1111);
//
//        listItems.add(item1);
//        listItems.add(item2);





        /* Convert List to JRBeanCollectionDataSource */
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ItemDataSource", itemsJRBean);
        parameters.put("subTotal", 100.0);
        parameters.put("discount", 111.1);
        parameters.put("total", 222.2);

        /* Using compiled version(.jasper) of Jasper report to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(userHomeDirectory + "\\IdeaProjects\\StoreManagementSystem\\src\\docs\\monthly_report_A4_2.jasper", parameters, new JREmptyDataSource());


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

