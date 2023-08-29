package com.michaelvol.ecommerceapi.product;

import com.michaelvol.ecommerceapi.product.dto.CreateProductRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductService {
    Product save(Product product);

    Product create(CreateProductRequest request);

    Optional<Product> findById(Long id);

    Iterable<Product> findAll();

    void deleteById(Long id);

    Product update(Product product);
}
