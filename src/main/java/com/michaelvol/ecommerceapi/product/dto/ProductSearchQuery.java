package com.michaelvol.ecommerceapi.product.dto;

import com.michaelvol.ecommerceapi.product.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class ProductSearchQuery {
    @NotBlank(message = "Keyword is required")
    private String keyword;

    @Builder.Default
    private Integer page = 0;

    @Builder.Default
    private Integer size = 10;

    private ProductCategory category;

    @Positive(message = "Max price must be greater than 0")
    private Double minPrice;

    @Positive(message = "Max price must be greater than 0")
    private Double maxPrice;
}
