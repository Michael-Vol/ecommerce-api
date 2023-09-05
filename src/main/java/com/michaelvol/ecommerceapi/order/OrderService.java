package com.michaelvol.ecommerceapi.order;

import com.michaelvol.ecommerceapi.order.dto.PageableOrderQuery;
import com.michaelvol.ecommerceapi.order.dto.UpdateOrderRequest;

public interface OrderService {
    Order save(Order order);

    Order findById(Long id);

    Order create(Order order);

    Iterable<Order> findAll();

    Iterable<Order> findAll(PageableOrderQuery query);

    Order setOrderStatus(Long id, OrderStatus status);

    Order setOrderStatus(Order order, OrderStatus status);

    void deleteById(Long id);

    Order update(UpdateOrderRequest request, Order order);

    Order getLatestOrderByUserId(Long userId);
}
