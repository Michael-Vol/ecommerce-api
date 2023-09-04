package com.michaelvol.ecommerceapi.order;

import com.michaelvol.ecommerceapi.order.dto.PageableOrderQuery;
import com.michaelvol.ecommerceapi.order.dto.UpdateOrderRequest;

import java.util.Optional;

public interface OrderService {
    Order save(Order order);

    Optional<Order> findById(Long id);

    Order create(Order order);

    Iterable<Order> findAll();

    Iterable<Order> findAll(PageableOrderQuery query);

    Order setOrderStatus(Long id, OrderStatus status);

    Order setOrderStatus(Order order, OrderStatus status);

    void deleteById(Long id);

    Order update(UpdateOrderRequest request, Order order);

    Order getLatestOrderByUserId(Long userId);
}
