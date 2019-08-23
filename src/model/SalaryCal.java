package model;

import java.util.Date;

public class SalaryCal {

    private char empId;
    private Date date;
    private float basic;
    private float deduction;
    private int hours;
    private float overtime;
    private float bonus;


    public SalaryCal(char empId, Date date, float basic, float deduction, int hours, float overtime, float bonus) {
        this.empId = empId;
        this.date = date;
        this.basic = basic;
        this.deduction = deduction;
        this.hours = hours;
        this.overtime = overtime;
        this.bonus = bonus;
    }

    public char getEmpId() {
        return empId;
    }

    public void setEmpId(char empId) {
        this.empId = empId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getBasic() {
        return basic;
    }

    public void setBasic(float basic) {
        this.basic = basic;
    }

    public float getDeduction() {
        return deduction;
    }

    public void setDeduction(float deduction) {
        this.deduction = deduction;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public float getOvertime() {
        return overtime;
    }

    public void setOvertime(float overtime) {
        this.overtime = overtime;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }



}
