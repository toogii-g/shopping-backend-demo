package com.shop.application.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank String name,
        String description,
        @Positive @Digits(integer = 10, fraction = 2) BigDecimal price,
        @Min(0) Integer stock,
        String category
) {}