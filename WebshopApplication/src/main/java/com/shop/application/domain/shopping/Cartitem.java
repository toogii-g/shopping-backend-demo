package com.shop.application.domain.shopping;

import com.shop.application.domain.products.products.Product;


public class Cartitem {
    private int quantity;
    private Product product;
    
    public Cartitem() {
    }
 
    public Cartitem(Product product, int quantity) {
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
        return "Cartitem{" +
                "quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
