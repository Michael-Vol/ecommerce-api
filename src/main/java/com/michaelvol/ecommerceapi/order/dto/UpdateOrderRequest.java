package com.michaelvol.ecommerceapi.order.dto;

import com.michaelvol.ecommerceapi.order.OrderStatus;
import com.michaelvol.ecommerceapi.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
@Builder

public class UpdateOrderRequest {
    private OrderStatus orderStatus;


    private String shippingAddress;

    private Set<Product> products;
}
