package com.shop.application.dto;

import com.shop.application.domain.products.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product productRequestToProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        return product;
    }

    public void updateProductFromRequest(ProductRequest request, Product product) {
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
    }
}