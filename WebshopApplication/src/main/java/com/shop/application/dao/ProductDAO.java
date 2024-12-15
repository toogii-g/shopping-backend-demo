package com.shop.application.dao;

import com.shop.application.domain.products.products.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductDAO {
    private Map<Integer, Product> products = new HashMap<Integer, Product>();

    public void save(Product product){
        products.put(product.getProductNumber(), product);
    }

    public Product find(int productNumber){
        return products.get(productNumber);
    }

    public void remove(int productNumber){
        products.remove(productNumber);
    }
}
