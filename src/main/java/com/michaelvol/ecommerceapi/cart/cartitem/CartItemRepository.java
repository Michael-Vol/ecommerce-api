package com.michaelvol.ecommerceapi.cart.cartitem;

import com.michaelvol.ecommerceapi.cart.Cart;
import com.michaelvol.ecommerceapi.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    void deleteByCartAndProduct(Cart cart, Product product);
}
