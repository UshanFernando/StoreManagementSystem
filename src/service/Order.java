package service;

import java.math.BigDecimal;



import java.math.BigDecimal;

    public class Order {

        private String item;
        private BigDecimal unitPrice;
        private int quantity;

        public Order(String item, BigDecimal unitPrice, int quantity) {
            this.item = item;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

