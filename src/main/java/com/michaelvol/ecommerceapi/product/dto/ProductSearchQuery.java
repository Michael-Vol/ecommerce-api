package com.michaelvol.ecommerceapi.product.dto;

import com.michaelvol.ecommerceapi.product.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.domain.Sort;

@Data
@Jacksonized
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductSearchQuery {
    @NotBlank(message = "Keyword is required")
    String keyword;

    @Builder.Default
    ProductCategory category = ProductCategory.OTHER;

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
    Integer pageSize = 30;

}
