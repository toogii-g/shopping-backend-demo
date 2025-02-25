package shop.managementapplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.managementapplication.domain.products.Inventory;
import shop.managementapplication.dto.InventoryResponse;
import shop.managementapplication.dto.StockUpdateRequest;
import shop.managementapplication.errorhandling.InsufficientStockException;
import shop.managementapplication.service.InventoryService;

//@RestController
//@RequestMapping("/api/inventory")
//@RequiredArgsConstructor
//@Tag(name = "Inventory Management", description = "Manage product inventory and stock levels")
public class InventoryController {
   // private final InventoryService inventoryService;
/*
    @Operation(summary = "Get inventory by product ID")
    @ApiResponse(responseCode = "200", description = "Inventory found")
    @ApiResponse(responseCode = "404", description = "Inventory not found")
    @GetMapping("/products/{productId}")
    public ResponseEntity<InventoryResponse> getInventoryByProductId(
            @PathVariable Long productId) {
        Inventory inventory = inventoryService.getInventoryForProduct(productId);
        return ResponseEntity.ok(mapToInventoryResponse(inventory));
    }

    @Operation(summary = "Update stock quantities")
    @ApiResponse(responseCode = "200", description = "Stock updated")
    @ApiResponse(responseCode = "400", description = "Invalid stock values")
    @PutMapping("/products/{productId}")
    public ResponseEntity<InventoryResponse> updateInventory (
            @PathVariable Long productId,
            @Valid @RequestBody StockUpdateRequest request) throws InsufficientStockException {
        Inventory updated = inventoryService.updateStock(productId, request);
        return ResponseEntity.ok(mapToInventoryResponse(updated));
    }

    @Operation(summary = "Reserve stock")
    @ApiResponse(responseCode = "200", description = "Stock reserved")
    @ApiResponse(responseCode = "400", description = "Insufficient stock")
    @PostMapping("/products/{productId}/reserve")
    public ResponseEntity<Void> reserveStock(
            @PathVariable Long productId,
            @RequestParam @Min(1) Integer quantity) throws InsufficientStockException {
        inventoryService.reserveStock(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Release reserved stock")
    @ApiResponse(responseCode = "200", description = "Stock released")
    @ApiResponse(responseCode = "400", description = "Invalid release quantity")
    @PostMapping("/products/{productId}/release")
    public ResponseEntity<Void> releaseStock(
            @PathVariable Long productId,
            @RequestParam @Min(1) Integer quantity) throws InsufficientStockException {
        inventoryService.releaseStock(productId, quantity);
        return ResponseEntity.ok().build();
    }

    private InventoryResponse mapToInventoryResponse(Inventory inventory) {
        return new InventoryResponse(
                inventory.getProduct().getProductNr(),
                inventory.getAvailableQuantity(),
                inventory.getReservedQuantity(),
                inventory.getMinimumStockLevel()
        );
    }*/
}
