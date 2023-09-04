package com.michaelvol.ecommerceapi.cart.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddToCartRequest {
    @NotNull(message = "Product Id is required")
    private Long productId;

    @Builder.Default
    private Integer quantity = 1;
}
