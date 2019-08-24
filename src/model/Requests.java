package model;

import java.util.Date;

public class Requests {
    private int id;
    private String status;
    private double amount;
    private Date date;

    public Requests(int id, String status, double amount, Date date) {

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

    public String getAmount() {
        return status;
    }

    public void setAmount(String status) {
        this.status = status;
    }

    public boolean isActive(){

        return status.equals("Available");
    }

}
