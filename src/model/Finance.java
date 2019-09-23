package model;

public class Finance {
    private int id;
    private String status;
    private double amount;
    private String date;

    private int id2;
    private String status2;
    private double amount2;
    private String date2;

    public Finance() {
        this.id = 0;
        this.status = "";
        this.amount = 0;
        this.date = "";

    }


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







    public int getId2() {
        return id2;
    }

    public void setId2(int id) {
        this.id2 = id;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    public double getAmount2() {
        return amount2;
    }

    public void setAmount2(double amount2) {
        this.amount2 = amount2;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }
}
