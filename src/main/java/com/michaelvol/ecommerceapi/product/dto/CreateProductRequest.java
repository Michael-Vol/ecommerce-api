package com.michaelvol.ecommerceapi.product.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CreateProductRequest {
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    private Integer quantity;
    private Boolean isAvailable;
}
