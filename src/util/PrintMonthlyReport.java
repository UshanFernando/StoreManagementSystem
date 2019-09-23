package util;


import javafx.collections.ObservableList;
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


    public void Generate(ObservableList<Finance> income, ObservableList<Finance> expense) throws JRException, FileNotFoundException {


        /* User home directory location */
        String userHomeDirectory = System.getProperty("user.home");
        System.out.println(userHomeDirectory);

        /* List to hold Items */
        ArrayList<Finance> listItems = new ArrayList<>();

        /* Create Items */

        double totalExpense = 0;
        double totalIncome =0;
        for (Finance fitem : income){
            System.out.println("FOR LOOP" + fitem.getId());
            Finance item = new Finance();
            item.setId(fitem.getId());
            item.setAmount(fitem.getAmount());
            item.setDate(fitem.getDate());
            totalIncome += fitem.getAmount();
            listItems.add(item);
        }
        ArrayList<Finance> listItems2 = new ArrayList<>();
            for (Finance fitem : expense){
            System.out.println("FOR LOOP EXPENSES" + fitem.getId());
            Finance item = new Finance();
            item.setId2(fitem.getId());
            item.setAmount2(fitem.getAmount());
            item.setDate2(fitem.getDate());
            totalExpense += fitem.getAmount();
            listItems2.add(item);
        }





        /* Convert List to JRBeanCollectionDataSource */
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);
        JRBeanCollectionDataSource itemsJRBean2 = new JRBeanCollectionDataSource(listItems2);
        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ItemDataSource", itemsJRBean);
        parameters.put("ItemDataSource2", itemsJRBean2);
        parameters.put("subTotal", totalIncome);
        parameters.put("discount", totalExpense);
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

