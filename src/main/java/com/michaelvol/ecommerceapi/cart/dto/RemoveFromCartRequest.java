package com.michaelvol.ecommerceapi.cart.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoveFromCartRequest {
    @NotNull(message = "Cart Id is required")
    @PositiveOrZero(message = "Cart Id must be greater than or equal to 0")
    Long productId;
}
