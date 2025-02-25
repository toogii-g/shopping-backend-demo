package shop.managementapplication.dto;

public record InventoryResponse(
        Long productId,
        Integer available,
        Integer reserved,
        Integer minimumStockLevel
) {}
