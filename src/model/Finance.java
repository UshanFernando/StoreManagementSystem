package model;

import java.sql.Date;

public class Finance {
    private int id;
    private String status;
    private double amount;
    private String date;

    public Finance(int id, String status, double amount, String date) {

        this.id = id;
        this.status = status;
        this.amount = amount;
        this.date = date;


    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
