package com.michaelvol.ecommerceapi.order;

import com.michaelvol.ecommerceapi.order.dto.GetOrderResponse;
import com.michaelvol.ecommerceapi.order.dto.GetOrderStatusResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.michaelvol.ecommerceapi.utils.AppConstants.API_BASE_URL;

@RestController
@RequestMapping(API_BASE_URL + "/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable Long id) {
        Order order = orderService.findById(id);
        GetOrderResponse response = GetOrderResponse
                .builder()
                .order(order)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<GetOrderStatusResponse> getOrderStatus(@PathVariable Long id) {
        Order order = orderService.findById(id);
        GetOrderStatusResponse response = GetOrderStatusResponse
                .builder()
                .orderStatus(order.getStatus())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<GetOrderStatusResponse> updateOrderStatus(@PathVariable Long id,
                                                                    @RequestBody OrderStatus status) {
        Order order = orderService.setOrderStatus(id, status);
        GetOrderStatusResponse response = GetOrderStatusResponse
                .builder()
                .orderStatus(order.getStatus())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
