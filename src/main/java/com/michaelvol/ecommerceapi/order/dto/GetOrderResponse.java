package com.michaelvol.ecommerceapi.order.dto;

import com.michaelvol.ecommerceapi.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetOrderResponse {
    private Order order;
}
