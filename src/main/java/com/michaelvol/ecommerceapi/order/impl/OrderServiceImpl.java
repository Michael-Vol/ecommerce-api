package com.michaelvol.ecommerceapi.order.impl;

import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.order.Order;
import com.michaelvol.ecommerceapi.order.OrderRepository;
import com.michaelvol.ecommerceapi.order.OrderService;
import com.michaelvol.ecommerceapi.order.OrderStatus;
import com.michaelvol.ecommerceapi.order.dto.PageableOrderQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
@Builder
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Iterable<Order> findAll(PageableOrderQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), Sort.by(query.getDirection(),
                query.getSortBy()));
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order setOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Order with id " + id + " not found"));
        return setOrderStatus(order, status);
    }

    @Override
    public Order setOrderStatus(Order order, OrderStatus status) {
        order.setStatus(status);
        order.setDateUpdated(ZonedDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        //Check for order existence
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent())
            throw new BadRequestException("Order with id " + id + " not found");
        orderRepository.deleteById(id);
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public Order getLatestOrderByUserId(Long userId) {
        return null;
    }
}
