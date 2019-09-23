package model;

public class Leave {

    private String fullname;
    private int employeeId;
    private String ltype;
    private String email;
    private String date;


    public Leave(String fullname, int employeeId, String ltype, String email, String date){

        this.employeeId = employeeId;
        this.fullname = fullname;
        this.ltype = ltype;
        this.email = email;
        this.date = date;


    }





    public int getEmpId() {
        return employeeId;
    }

    public void setEmpId(int id) {
        this.employeeId = employeeId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) { this.fullname = fullname;    }

    public String getLtype() { return ltype;}

    public void setLtype (String ltype) {this.ltype = ltype;}

    public String getEmail() { return email;}

    public void setEmail(String email){ this.email = email;}

    public String getDate(){return date;}

    public void setDate(String date){this.date = date;}



}



