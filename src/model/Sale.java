package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Sale {

    private int id;
    private double subTotal;
    private double discount;
    private double netTotal;
    private double payment;
    private Timestamp timeStamp;




    public Sale(int id, double subTotal, double discount, double netTotal, double payment, Timestamp timeStamp) {
        this.id = id;
        this.subTotal = subTotal;
        this.discount = discount;
        this.netTotal = netTotal;
        this.payment = payment;
        this.timeStamp = timeStamp;
    }
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }
}
