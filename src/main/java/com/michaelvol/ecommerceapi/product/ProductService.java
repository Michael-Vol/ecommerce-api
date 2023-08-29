package com.michaelvol.ecommerceapi.product;

import java.util.Optional;

public interface ProductService {
    Product save(Product product);

    Product create(Product product);

    Optional<Product> findById(Long id);

    Iterable<Product> findAll();

    void deleteById(Long id);

    Product update(Product product);
}
