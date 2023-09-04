package com.michaelvol.ecommerceapi.cart.impl;

import com.michaelvol.ecommerceapi.cart.Cart;
import com.michaelvol.ecommerceapi.cart.CartService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Data
@Builder
public class CartServiceImpl implements CartService {
    @Override
    public Cart save(Cart cart) {
        return null;
    }

    @Override
    public Cart getCartById(Long id) {
        return null;
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return null;
    }

    @Override
    public Cart addItemToCart(Long cartId, Long productId, Integer quantity) {
        return null;
    }

    @Override
    public Cart removeItemFromCart(Long cartId, Long productId) {
        return null;
    }

    @Override
    public Cart updateItemQuantity(Long cartId, Long productId, Integer quantity) {
        return null;
    }

    @Override
    public Cart clearCart(Long cartId) {
        return null;
    }

    @Override
    public Cart deleteCart(Long cartId) {
        return null;
    }
}
