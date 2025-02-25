package shop.managementapplication.dto;

import jakarta.validation.constraints.Min;

public record StockUpdateRequest(
        @Min(0) Integer availableQuantity,
        @Min(0) Integer reservedQuantity
) {}
