package model;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.*;

public class ShoppingCart {

    private ArrayList<ShoppingCartItem> items = null;
    private double subTotal ;
    private double discount;
    private double netTotal;

    public ShoppingCart() {
        this.subTotal = 0.0;
        this.discount = 0.0;
        this.netTotal = 0.0;
        items = new ArrayList<>();

    }

    public void addItem(ShoppingCartItem item) {

        if (item.getProduct()!=null) {


            subTotal += (item.getProduct().getPrice()) * item.getQuantity();

            double itemPrice = item.getProduct().getPrice();
            double itemDiscount = item.getDiscount();

            discount += ((itemPrice * itemDiscount) / 100) * item.getQuantity();
        }

        items.add(item);
    }

    public double getSubTotal() {

        return subTotal;
    }

    public double getDiscount() {

        return discount;
    }

    public double getNetTotal(){

        return subTotal-discount;

    }

    public ArrayList<ShoppingCartItem> getItems(){

        return items;
    }

    public void clear(){

        this.subTotal = 0.0;
        this.discount = 0.0;
        this.netTotal = 0.0;
        items.clear();

    }

}
