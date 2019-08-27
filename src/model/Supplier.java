package model;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
    public  int id;
    private  String vendor;
    private  String category;
    private  String address;
    private List email;
    private List phone;


    public Supplier(int sId, String vendor, String category, String address) {
        this.id = sId;
        this.vendor = vendor;
        this.category = category;
        this.address = address;
//        this.email = new ArrayList<String>();
//        this.phone = new ArrayList<String>();
    }

    public int getId() {
        return id;
    }

    public void setId(int sId) {
        this.id = sId;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List getEmail() {
        return email;
    }

    public void setEmail(ArrayList email) {
        this.email = email;
    }

    public List getPhone() {
        return phone;
    }

    public void setPhone(ArrayList phone) {
        this.phone = phone;
    }
}
