package com.michaelvol.ecommerceapi.product.impl;

import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.product.Product;
import com.michaelvol.ecommerceapi.product.ProductRepository;
import com.michaelvol.ecommerceapi.product.ProductService;
import com.michaelvol.ecommerceapi.product.QProduct;
import com.michaelvol.ecommerceapi.product.dto.CreateProductRequest;
import com.michaelvol.ecommerceapi.product.dto.ProductSearchQuery;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
        return productRepository.findById(id);
    }

    public Product create(CreateProductRequest request) throws BadRequestException {
        //Check if the product already exists
        Boolean productExists = productRepository.findProductByTitle(request.getTitle()).isPresent();

        if (productExists)
            throw new BadRequestException("Product with title " + request.getTitle() + " already exists");

        Product product = Product
                .builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .price(request.getPrice())
                .quantity(request.getQuantity() == null ? 1 : request.getQuantity())
                .isAvailable(request.getIsAvailable() == null ? true : request.getIsAvailable())
                .build();

        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    public Iterable<Product> search(ProductSearchQuery query) {
        String keyword = query.getKeyword();
        //Pagination
        String sortBy = query.getSortBy();
        Sort.Direction sortDirection = query.getSortDirection();
        Integer page = query.getPage();
        Integer pageSize = query.getPageSize();
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortDirection, sortBy));

        QProduct product = QProduct.product;

        BooleanExpression queryPredicate = product.title.contains(keyword)
                .or(product.description.contains(query.getKeyword()));

        if (query.getCategory() != null) {
            queryPredicate = queryPredicate.and(product.category.stringValue()
                    .equalsIgnoreCase(query.getCategory().name()));
        }
        if (query.getMinPrice() != null) {
            queryPredicate = queryPredicate.and(product.price.gt(query.getMinPrice()));
        }
        if (query.getMaxPrice() != null) {
            queryPredicate = queryPredicate.and(product.price.lt(query.getMaxPrice()));
        }

        Page<Product> productsPage = productRepository.findAll(queryPredicate, pageable);

        return productsPage;
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        return null;
    }

}
