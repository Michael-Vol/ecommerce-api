package com.michaelvol.ecommerceapi.cart.dto;

import com.michaelvol.ecommerceapi.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CartCheckoutResponse {
    private String message;
    private Order order;
}
