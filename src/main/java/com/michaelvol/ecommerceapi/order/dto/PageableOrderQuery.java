package com.michaelvol.ecommerceapi.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class PageableOrderQuery {
    @Builder.Default
    private Integer page = 0;
    @Builder.Default
    private Integer size = 10;
    @Builder.Default
    private String sortBy = "dateCreated";
    @Builder.Default
    private Sort.Direction direction = Sort.Direction.ASC;
}
