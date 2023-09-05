package com.michaelvol.ecommerceapi.product.impl;

import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.product.*;
import com.michaelvol.ecommerceapi.product.dto.CreateProductRequest;
import com.michaelvol.ecommerceapi.product.dto.PageableProductQuery;
import com.michaelvol.ecommerceapi.product.dto.ProductSearchQuery;
import com.michaelvol.ecommerceapi.product.dto.UpdateProductRequest;
import com.michaelvol.ecommerceapi.product.utils.UpdateProductMapper;
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
    private final UpdateProductMapper mapper;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) throws BadRequestException {
        return productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Product with id " + id + " not found"));
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
                .category(request.getCategory() == null ? ProductCategory.OTHER : request.getCategory())
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

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Iterable<Product> findAll(PageableProductQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), Sort.by(query.getDirection(),
                query.getSortBy()));
        return productRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        //Check for product existence
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent())
            throw new BadRequestException("Product with id " + id + " not found");
        productRepository.deleteById(id);
    }

    @Override
    public Product update(UpdateProductRequest request, Product currentProduct) {
        mapper.updateProductFromDto(request, currentProduct);
        return productRepository.save(currentProduct);
    }

}
