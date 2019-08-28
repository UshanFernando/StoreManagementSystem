package model;

public class Attendance {

    private int empid;
    private String name;
    private String month;
    private int year;
    private int No;




    public Attendance(int empid, String name, String month, int year, int No){

        this.empid = empid;
        this.name = name;
        this.month = month;
        this.year = year;
        this.No = No;

    }

    public int getEmpId() {
        return empid;
    }

    public void setEmpId(int id) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    public void setStatus(String month) {
        this.month = month;
    }

    public int getYear() { return year; }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNOofAttendance() { return No; }

    public void setNOofAttendance(int No) {
        this.No = No;
    }


}
