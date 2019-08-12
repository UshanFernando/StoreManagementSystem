package model;

public class Product {

    private int pid ;
    private String name;
    private Double price;
    private String brand;
    private String Category;
    private int qty;

    public Product(){

    }

    public Product(int pid, String name, Double price, String brand, String category, int qty) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.brand = brand;
        Category = category;
        this.qty = qty;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
