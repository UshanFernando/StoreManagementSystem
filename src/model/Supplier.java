package model;

public class Supplier {
    public  int sId;
    private  String vendor;
    private Brand brand;
    private  Product product;
    private  String address;
    private  Contact contact;


    public Supplier(int sId, String vendor, Product product, String address, Contact contact, Brand brand) {
        this.sId = sId;
        this.vendor = vendor;
        this.product = product;
        this.brand = brand;
        this.address = address;
        this.contact = contact;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
