package model;

public class Salary {

    private Integer sID;
    private String eID;
    private String date;
    private double bonus;
    private double basic;
    private double overtime;
    private double deduction;
    private double total;

    public Salary(Integer sID, String eID, String date, double bonus, double basic, double overtime, double deduction, double total) {
        this.sID = sID;
        this.eID = eID;
        this.date = date;
        this.bonus = bonus;
        this.basic = basic;
        this.overtime = overtime;
        this.deduction = deduction;
        this.total = total;
    }

    public Integer getsID() {
        return sID;
    }

    public void setsID(Integer sID) {
        this.sID = sID;
    }

    public String geteID() {
        return eID;
    }

    public void seteID(String eID) {
        this.eID = eID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getBasic() {
        return basic;
    }

    public void setBasic(double basic) {
        this.basic = basic;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
