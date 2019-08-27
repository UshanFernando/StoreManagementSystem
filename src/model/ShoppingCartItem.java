package model;

public class ShoppingCartItem {

    private Product product;
    private int quantity;
    private String productName;
    private double price;
    private Double discount;


    public ShoppingCartItem(Product product, int quantity, Double discount) {
        this.product = product;
        this.quantity = quantity;
        this.discount = discount;
        this.productName = product.getName();
        this.price = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
