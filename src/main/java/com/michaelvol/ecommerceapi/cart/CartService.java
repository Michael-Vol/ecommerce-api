package com.michaelvol.ecommerceapi.cart;

import com.michaelvol.ecommerceapi.cart.dto.CartToOrderRequest;
import com.michaelvol.ecommerceapi.order.Order;

public interface CartService {
    Cart save(Cart cart);

    Cart getCartById(Long id);

    Cart getCartByUserId(Long userId);

    Cart addItemToCart(Long cartId, Long productId, Integer quantity);

    Cart removeItemFromCart(Long cartId, Long productId);

    Cart updateItemQuantity(Long cartId, Long productId, Integer quantity);

    Cart clearCart(Long cartId);

    void deleteCart(Long cartId);

    Order checkout(CartToOrderRequest request);
}
