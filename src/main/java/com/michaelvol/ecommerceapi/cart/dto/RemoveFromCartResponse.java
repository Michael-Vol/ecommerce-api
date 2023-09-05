package com.michaelvol.ecommerceapi.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RemoveFromCartResponse {
    Long productId;

    String message;
}
