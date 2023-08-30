package com.michaelvol.ecommerceapi.product;

import com.michaelvol.ecommerceapi.product.dto.CreateProductRequest;

import java.util.Optional;

public interface ProductService {
    Product save(Product product);

    Product create(CreateProductRequest request);

    Optional<Product> findById(Long id);

    Iterable<Product> findAll();

    void deleteById(Long id);

    Product update(Product product);

    Iterable<Product> search(String query);
}
