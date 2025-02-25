package shop.managementapplication.service;

import shop.managementapplication.dao.ProductRepository;
import shop.managementapplication.errorhandling.ProductAlreadyExistsException;
import shop.managementapplication.errorhandling.ResourceNotFoundException;
import shop.managementapplication.domain.products.Product;
import shop.managementapplication.dto.ProductMapper;
import shop.managementapplication.dto.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findByProductNr(Long productNr) {
        return productRepository.findByProductNr(productNr)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with product_nr: " + productNr));
    }

    public Product createProduct(ProductRequest productRequest) {
        validateProductUniqueName(productRequest.name());

        Product product = productMapper.productRequestToProduct(productRequest);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductRequest productRequest) {
        Product existingProduct = findByProductNr(id);

        if (!existingProduct.getName().equals(productRequest.name())) {
            validateProductUniqueName(productRequest.name());
        }

        productMapper.updateProductFromRequest(productRequest, existingProduct);
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    private void validateProductUniqueName(String name) {
        if (productRepository.existsByName(name)) {
            throw new ProductAlreadyExistsException("Product with name '" + name + "' already exists");
        }
    }
}
