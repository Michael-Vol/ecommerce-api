package com.michaelvol.ecommerceapi.cart.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddToCartResponse {
    private Long cartId;
    private String message;
}
