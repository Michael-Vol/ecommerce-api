package com.michaelvol.ecommerceapi.cart.dto;

import com.michaelvol.ecommerceapi.order.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CartToOrderRequest {
    @NotNull(message = "Cart Id is required")
    private Long cartId;

    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;

    @NotBlank(message = "Billing address is required")
    private String billingAddress;

    @NotBlank(message = "Payment method is required")
    private PaymentMethod paymentMethod;
}
