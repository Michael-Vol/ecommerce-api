package com.michaelvol.ecommerceapi.product.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.michaelvol.ecommerceapi.product.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.domain.Sort;

@Value
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductSearchQuery {
    @NotBlank(message = "Keyword is required")
    String keyword;

    ProductCategory category;

    @Positive(message = "Max price must be greater than 0")
    Double minPrice;

    @Positive(message = "Max price must be greater than 0")
    Double maxPrice;

    @Builder.Default
    Sort.Direction sortDirection = Sort.Direction.ASC;

    @Builder.Default
    String sortBy = "title";

    @Builder.Default
    Integer page = 0;

    @Builder.Default
    Integer pageSize = 10;
}
