package com.michaelvol.ecommerceapi.cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
