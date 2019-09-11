package model;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
    public  int id;
    private  String vendor;
    private  Category category;
    private  String address;



    public Supplier(int sId, String vendor, Category category, String address) {
        this.id = sId;
        this.vendor = vendor;
        this.category = category;
        this.address = address;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
