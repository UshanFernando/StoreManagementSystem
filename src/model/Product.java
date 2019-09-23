package model;

public class Product {

    private int pid;
    private String name;
    private Double price;
    private Brand brand;
    private Category category;
    private int qty;
    private String status;

    public Product() {

    }

    public Product(int pid, String name, Double price, Brand brand, Category category, int qty) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.qty = qty;
    }

    public Product(String name, String status){
        this.name = name;
        this.status = status;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public  Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
