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

        items.add(item);

    }

    public double getSubTotal() {

        for (ShoppingCartItem item : items) {

            subTotal =+ item.getProduct().getPrice() * item.getQuantity();

        }
        return subTotal;
    }

    public double getDiscount() {

        if (!items.isEmpty()) {
            for (ShoppingCartItem item : items) {

                double itemPrice = item.getProduct().getPrice();
                double itemDiscount = item.getDiscount();

                discount = +((itemPrice * itemDiscount) / 100)*item.getQuantity();

            }
        }
        return discount;
    }

    public double getNetTotal(){

        return subTotal-discount;

    }

    public ArrayList<ShoppingCartItem> getItems(){

        return items;
    }


}
