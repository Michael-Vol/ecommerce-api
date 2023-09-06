package com.michaelvol.ecommerceapi.cart.dto;

import com.michaelvol.ecommerceapi.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCartResponse {
    private Cart cart;
}
