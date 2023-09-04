package com.michaelvol.ecommerceapi.cart.impl;

import com.michaelvol.ecommerceapi.cart.Cart;
import com.michaelvol.ecommerceapi.cart.CartRepository;
import com.michaelvol.ecommerceapi.cart.CartService;
import com.michaelvol.ecommerceapi.cart.cartitem.CartItemRepository;
import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Data
@Builder
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Long id) throws BadRequestException {
        return cartRepository.findById(id).orElseThrow(
                () -> new BadRequestException("Cart with id " + id + " not found")
        );
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElseThrow(
                () -> new BadRequestException("Cart of user with id " + userId + " not found"));
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
