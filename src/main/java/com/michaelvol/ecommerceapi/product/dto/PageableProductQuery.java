package com.michaelvol.ecommerceapi.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class PageableProductQuery {
    @Builder.Default
    private Integer page = 0;
    @Builder.Default
    private Integer size = 50;
    @Builder.Default
    private String sortBy = "id";
    @Builder.Default
    private Sort.Direction direction = Sort.Direction.ASC;
}
