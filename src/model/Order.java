package model;

import java.util.Date;

public class Order {
    private int oid;
    private String  product;
    private String qty;
    private String supplier ;
    private String orderDate;
    private String deliveryDate;
    private String eta;
    private String requests;

    public Order(int oid, String product, String qty, String supplier, String orderDate, String deliveryDate, String requests) {
        this.oid = oid;
        this.product = product;
        this.qty = qty;
        this.supplier = supplier;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.requests = requests;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getRequests() {
        return requests;
    }

    public void setRequests(String requests) {
        this.requests = requests;
    }
}
