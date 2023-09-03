package com.michaelvol.ecommerceapi.product;

import com.michaelvol.ecommerceapi.product.dto.CreateProductRequest;
import com.michaelvol.ecommerceapi.product.dto.PageableProductQuery;
import com.michaelvol.ecommerceapi.product.dto.ProductSearchQuery;
import com.michaelvol.ecommerceapi.product.dto.UpdateProductRequest;

import java.util.Optional;

public interface ProductService {
    Product save(Product product);

    Product create(CreateProductRequest request);

    Optional<Product> findById(Long id);

    Iterable<Product> findAll();

    Iterable<Product> findAll(PageableProductQuery query);

    void deleteById(Long id);

    Product update(UpdateProductRequest request, Product currentProduct);

    Iterable<Product> search(ProductSearchQuery query);
}
