package com.michaelvol.ecommerceapi.order.dto;

import com.michaelvol.ecommerceapi.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetOrderStatusResponse {
    private final OrderStatus orderStatus;
}
