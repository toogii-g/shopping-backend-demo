package shop.managementapplication.dto;

import shop.managementapplication.domain.products.Product;
import shop.managementapplication.dto.ProductRequest;
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