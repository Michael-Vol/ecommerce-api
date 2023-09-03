package com.michaelvol.ecommerceapi.product.dto;

import com.michaelvol.ecommerceapi.product.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
public class UpdateProductRequest {
    @NotNull
    Product product;
}
