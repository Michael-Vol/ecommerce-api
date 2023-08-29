package com.michaelvol.ecommerceapi.product.impl;

import com.michaelvol.ecommerceapi.product.Product;
import com.michaelvol.ecommerceapi.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@AllArgsConstructor
@Data
@Builder
public class ProductServiceImpl implements ProductService {
    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Product> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Product update(Product product) {
        return null;
    }
}
