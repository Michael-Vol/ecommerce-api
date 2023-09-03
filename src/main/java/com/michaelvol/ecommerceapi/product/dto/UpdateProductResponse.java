package com.michaelvol.ecommerceapi.product.dto;

import com.michaelvol.ecommerceapi.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class UpdateProductResponse {
    Product product;
    String message;
}
