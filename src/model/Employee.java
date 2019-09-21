package model;

public class Employee {


        private  int empId;
        private  String name;
        private  String department;
        private  int level;
        private  int contact;
        private  String gender;
        private  String Address;
        private  String recdate;



        public Employee(int empId,String name,String department, int level, int contact, String gender, String Address,String recdate) {
            this.empId = empId;
            this.name = name;
            this.department = department;
            this.level = level;
            this.contact = contact;
            this.gender = gender;
            this.Address = Address;
            this.recdate = recdate;


        }

    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public int getContact() {
        return contact;
    }
    public void setContact(int contact) {
        this.contact = contact;
    }
    public String getRecdate() {
        return recdate;
    }
    public void setRecdate(String recdate) {
        this.recdate = recdate;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    }
