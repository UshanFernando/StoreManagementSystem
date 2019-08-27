package model;

public class Requests {
    private int id;
    private String status;
    private double amount;
    private String date;

    public Requests(int id, String status, double amount, String date) {

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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }



    public boolean isActive(){

        return status.equals("Available");
    }

}
