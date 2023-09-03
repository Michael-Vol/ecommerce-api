package com.michaelvol.ecommerceapi.product.dto;

import com.michaelvol.ecommerceapi.product.Product;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
public class UpdateProductResponse {
    Product product;
    String message;
}
