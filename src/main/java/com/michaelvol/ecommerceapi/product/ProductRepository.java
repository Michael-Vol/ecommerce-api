package com.michaelvol.ecommerceapi.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByTitle(String title);

    Iterable<Product> findProductsByTitleContaining(String query);
}
