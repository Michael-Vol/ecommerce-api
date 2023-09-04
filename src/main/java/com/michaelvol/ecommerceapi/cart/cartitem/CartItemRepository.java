package com.michaelvol.ecommerceapi.cart.cartitem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Long cartId, Long productId);

    void deleteByCartAndProduct(Long cartId, Long productId);
}
