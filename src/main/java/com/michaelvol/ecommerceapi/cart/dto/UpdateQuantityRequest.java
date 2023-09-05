package com.michaelvol.ecommerceapi.cart.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateQuantityRequest {
    @NotNull(message = "Product Id is required")
    @Positive(message = "Product Id must be greater than 0")
    Long productId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    Integer quantity;
}
