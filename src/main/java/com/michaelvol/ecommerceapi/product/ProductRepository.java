package com.michaelvol.ecommerceapi.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {
    Optional<Product> findProductByTitle(String title);

    Iterable<Product> findProductsByTitleContaining(String query);
}
