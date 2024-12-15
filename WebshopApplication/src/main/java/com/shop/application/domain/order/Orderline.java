package com.shop.application.domain.order;

import com.shop.application.domain.products.products.Product;


public class Orderline {
    private int quantity;
    private Product product;

    
    public Orderline() {
    }
 
    public Orderline(Product product, int quantity) {
       this.product=product;
       this.quantity=quantity;
    }
   
    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Orderline{" +
                "quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
