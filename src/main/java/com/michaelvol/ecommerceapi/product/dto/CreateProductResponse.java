package com.michaelvol.ecommerceapi.product.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CreateProductResponse {
    private Long id;
    private String title;
    private String message;
}
