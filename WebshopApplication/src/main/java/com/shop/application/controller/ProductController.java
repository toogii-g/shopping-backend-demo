package com.shop.application.controller;

import com.shop.application.domain.products.Product;
import com.shop.application.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Management", description = "Endpoints for managing products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get all products", description = "Retrieve paginated list of products with HATEOAS links")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved products")
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Product>>> getAllProducts(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection,
            PagedResourcesAssembler<Product> pagedResourcesAssembler) {

        PageRequest pageable = PageRequest.of(page, size, sortDirection, sortBy);
        Page<Product> productPage = productService.getAllProducts(pageable);

        PagedModel<EntityModel<Product>> pagedModel = pagedResourcesAssembler
                .toModel(productPage, EntityModel::of);

        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Get product by product Nr", description = "Retrieve a single product by its product Nr")
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @GetMapping("/{productNr}")
    public ResponseEntity<Product> getProductBypProductNr(@PathVariable Long productNr) {
        return ResponseEntity.ok(productService.findByProductNr(productNr));
    }
}
