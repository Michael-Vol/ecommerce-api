package com.michaelvol.ecommerceapi.product.impl;

import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.product.Product;
import com.michaelvol.ecommerceapi.product.ProductRepository;
import com.michaelvol.ecommerceapi.product.ProductService;
import com.michaelvol.ecommerceapi.product.dto.CreateProductRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@AllArgsConstructor
@Data
@Builder
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    public Product create(CreateProductRequest request)  throws BadRequestException{
        //Check if the product already exists
        Boolean productExists = productRepository.findProductByTitle(request.getTitle()).isPresent();

        if(productExists)
            throw new BadRequestException("Product with title " + request.getTitle() + " already exists");

        Product product = Product
                .builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .isAvailable(request.getIsAvailable())
                .build();

        Product savedProduct = productRepository.save(product);
        return savedProduct;
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
