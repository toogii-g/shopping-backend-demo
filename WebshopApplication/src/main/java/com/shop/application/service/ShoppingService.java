package com.shop.application.service;


import com.shop.application.domain.products.products.Product;
import com.shop.application.domain.shopping.ShoppingCart;
import com.shop.application.dao.ShoppingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingService {
    @Autowired
    ShoppingDAO shoppingDAO;

    public ShoppingCart getCart(int cartId){
        return shoppingDAO.find(cartId);
    }

    public void createCart(int cartId){
        ShoppingCart cart = new ShoppingCart(cartId);
        shoppingDAO.save(cart);
    }

    public void addToCart(int cartId, Product product, int quantity){
        ShoppingCart cart = getCart(cartId);
        cart.addToCart(product, quantity);
        shoppingDAO.save(cart);
    }



    public void removeFromCart(int cartId, int productNumber, int quantity){
        ShoppingCart cart = getCart(cartId);
        cart.removeFromCart(productNumber, quantity);
        shoppingDAO.save(cart);
    }

    public void changeQuantity(int cartId, int productNumber, int quantity){
        ShoppingCart cart = getCart(cartId);
        cart.changeQuantity(productNumber, quantity);
        shoppingDAO.save(cart);
    }



}
