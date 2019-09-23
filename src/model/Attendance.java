package model;

public class Attendance {

    private int employeeId;
    private String name;
    private String month;
    private int year;
    private int noOfAttendance;




    public Attendance(int employeeId, String name, String month, int year, int noOfAttendance){

        this.employeeId = employeeId;
        this.name = name;
        this.month = month;
        this.year = year;
        this.noOfAttendance = noOfAttendance;

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public int getNoOfAttendance() { return noOfAttendance; }

    public void setNOofAttendance(int NoOfAttendance) {
        this.noOfAttendance = noOfAttendance;
    }


    // public boolean getStatus() {
    // return getStatus();
//
    // }

    public void  getId() {
    }
}
