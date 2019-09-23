package model;

public class Product {

    private int pid;
    private String name;
    private Double price;
    private Brand brand;
    private Category category;
    private int qty;
    private int criticalQty;

    public Product() {

    }

    public int getCriticalQty() {
        return criticalQty;
    }

    public void setCriticalQty(int criticalQty) {
        this.criticalQty = criticalQty;
    }


    public Product(int pid, String name, Double price, Brand brand, Category category, int qty, int criticalQty) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.qty = qty;
        this.criticalQty = criticalQty;
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

    @Override
    public String toString() {
        return name;
    }
}
