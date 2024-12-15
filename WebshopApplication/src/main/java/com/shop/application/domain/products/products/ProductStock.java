package com.shop.application.domain.products.products;

public class ProductStock {

    private Product product;

    private int quantity;

    public ProductStock(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void deductQuantity(int quantity) {
        this.quantity = this.quantity - quantity;
    }

}
