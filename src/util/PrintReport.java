package util;


import model.Product;
import model.ShoppingCart;
import model.ShoppingCartItem;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrintReport extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public void Generate(ShoppingCart cart) throws JRException, FileNotFoundException {


        /* User home directory location */
        String userHomeDirectory = System.getProperty("user.home");
        System.out.println(userHomeDirectory);

        /* List to hold Items */
        ArrayList<Product> listItems = new ArrayList<Product>();

        for (ShoppingCartItem item : cart.getItems()){

            /* Create Items */
            Product product = new Product();
            product.setName(item.getProductName());
            product.setPrice(item.getProduct().getPrice());
            product.setQty(item.getQuantity());

            listItems.add(product);
        }



        /* Convert List to JRBeanCollectionDataSource */
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ItemDataSource", itemsJRBean);
        parameters.put("subTotal", cart.getSubTotal());
        parameters.put("discount", cart.getDiscount());
        parameters.put("total",cart.getNetTotal());

        /* Using compiled version(.jasper) of Jasper report to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(userHomeDirectory + "\\IdeaProjects\\StoreManagementSystem\\src\\docs\\invoice_A4.jasper", parameters, new JREmptyDataSource());


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

